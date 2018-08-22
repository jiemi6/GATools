package com.minkey.command;

import com.alibaba.fastjson.JSONObject;
import com.minkey.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SnmpUtil {
    private final static Logger logger = LoggerFactory.getLogger(SnmpUtil.class);
    static final String DEFAULT_PROTOCOL = "udp";

    public static final int DEFAULT_VERSION = SnmpConstants.version2c;
    public static final String DEFAULT_COMMUNITY = "public";
    public static final int DEFAULT_PORT = 161;
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;
    public static final int DEFAULT_RETRY = 3;

    /**
     * 创建对象communityTarget，用于返回target
     *
     * @return CommunityTarget
     */
    public static CommunityTarget createDefault(String ip) {
        return createTarget(ip,DEFAULT_PORT,DEFAULT_COMMUNITY,DEFAULT_VERSION,DEFAULT_RETRY,DEFAULT_TIMEOUT);
    }

    /**
     * 创建对象communityTarget，用于返回target
     * @param ip
     * @param port
     * @param community
     * @param version
     * @param retry
     * @param timeout
     * @return
     */
    public static CommunityTarget createTarget(String ip,int port, String community,int version,int retry,long timeout) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + port);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(version);
        target.setTimeout(timeout);
        target.setRetries(retry);
        return target;
    }

    /**
     * 根据OID，获取单条消息
     */
    public static JSONObject snmpGet(CommunityTarget target, String oid) {
//        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> snmpGet 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.info("snmpGet PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                throw new SystemException("snmpGet response is null,maybe request time out");
            } else {
                JSONObject data = new JSONObject(response.size());
                logger.info("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    logger.info(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());
                }
                logger.info("SNMP GET one OID value finished !");
                return data;
            }
        } catch (Exception e) {
            throw new SystemException("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
    }

    /**
     * 根据OID列表，一次获取多条OID数据，并且以List形式返回
     */
    public static JSONObject snmpGetList(CommunityTarget target, String[] oidList) {
//        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> snmpGetList 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.info("snmpGetList PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                throw new SystemException("snmpGetList response is null,maybe request time out");
            } else {
                JSONObject data = new JSONObject(response.size());
                logger.info("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    logger.info(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());
                }
                logger.info("snmpGetList GET one OID value finished !");
                return data;
            }
        } catch (Exception e) {
            throw new SystemException("snmpGetList Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
    }

    /**
     * 根据OID列表，采用异步方式一次获取多条OID数据，并且以List形式返回
     */
    public static void snmpAsynGetList(CommunityTarget target, List<String> oidList) {
//        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.info("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

	      /*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                @Override
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    logger.info("[request]:" + request);
                    if (response == null) {
                        logger.info("[ERROR]: response is null");
                    } else if (response.getErrorStatus() != 0) {
                        logger.info("[ERROR]: response status"
                                + response.getErrorStatus() + " Text:"
                                + response.getErrorStatusText());
                    } else {
                        logger.info("Received response Success!");
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            logger.info(vb.getOid() + " = " + vb.getVariable());
                        }
                        logger.info("SNMP Asyn GetList OID finished. ");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, target, null, listener);
            logger.info("asyn send pdu wait for response...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.info("latch.await =:" + wait);

            snmp.close();

            logger.info("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
    }

    /**
     * 根据targetOID，获取树形数据
     */
    public static JSONObject snmpWalk(CommunityTarget target, String targetOid) {
//        CommunityTarget target = createDefault(ip, community);
        TransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            boolean finished = false;
            JSONObject data = new JSONObject();
            logger.info("----> snmpWalk start <----");
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                PDU response = respEvent.getResponse();
                if (null == response) {
                    logger.info("snmpWalk responsePDU == null");
                    finished = true;
                    return null;
                } else {
                    vb = response.get(0);
                }
                // check finish
                finished = checkWalkFinished(targetOID, pdu, vb);

                if (!finished) {
                    logger.info(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());

                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    logger.info("SNMP walk OID has finished.");
                    return data;
                }
            }
            return null;
        } catch (Exception e) {
            throw new SystemException("SNMP walk Exception: " + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }

    }

    private static boolean checkWalkFinished(OID targetOID, PDU pdu, VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            logger.info("[true] responsePDU.getErrorStatus() != 0 ");
            logger.info(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            logger.info("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            logger.info("[true] vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            logger.info("[true] targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            System.out
                    .println("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            logger.info("[true] Variable received is not "
                    + "lexicographic successor of requested " + "one:");
            logger.info(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }

    /**
     * 根据targetOID，异步获取树形数据
     */
    public static void snmpAsynWalk(CommunityTarget target, String oid) {
//        final CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            logger.info("----> demo start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));

            ResponseListener listener = new ResponseListener() {
                @Override
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // logger.info("[request]:" + request);
                        if (response == null) {
                            logger.info("[ERROR]: response is null");
                        } else if (response.getErrorStatus() != 0) {
                            logger.info("[ERROR]: response status"
                                    + response.getErrorStatus() + " Text:"
                                    + response.getErrorStatusText());
                        } else {
                            logger.info("Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(targetOID,
                                    pdu, vb);
                            if (!finished) {
                                logger.info(vb.getOid() + " = "
                                        + vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target,
                                        null, this);
                            } else {
                                logger.info("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, target, null, listener);
            logger.info("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.info("latch.await =:" + wait);
            snmp.close();

            logger.info("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("SNMP Asyn Walk Exception:" + e);
        }
    }

    /**
     * 根据OID和指定string来设置设备的数据
     */
    public static void setPDU(CommunityTarget target, String oid, String val) throws IOException {
//        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid), new OctetString(val)));
        pdu.setType(PDU.SET);

        DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        logger.info("-------> 发送PDU <-------");
        snmp.send(pdu, target);
        snmp.close();
    }

}
