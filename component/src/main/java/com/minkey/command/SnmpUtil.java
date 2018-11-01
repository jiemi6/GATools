package com.minkey.command;

import com.alibaba.fastjson.JSONObject;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class SnmpUtil {

    public final static String DEFAULT_PROTOCOL = "udp";
    public final static int DEFAULT_VERSION = SnmpConstants.version2c;
    public final static String DEFAULT_COMMUNITY = "public";
    public final static int DEFAULT_PORT = 161;
    public final static long DEFAULT_TIMEOUT = 3 * 1000L;
    public final static int DEFAULT_RETRY = 3;

    private CommunityTarget communityTarget;

    /**
     * 创建
     */
    public SnmpUtil(String ip) {
        this(ip,DEFAULT_PORT,DEFAULT_COMMUNITY,DEFAULT_VERSION,DEFAULT_RETRY,DEFAULT_TIMEOUT);
    }

    /**
     * 创建对象
     * @param ip
     * @param port
     * @param community
     * @param version
     * @param retry
     * @param timeout
     */
    public SnmpUtil(String ip,int port, String community,int version,int retry,long timeout) {
        super();
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + port);
        this.communityTarget = new CommunityTarget();
        communityTarget.setCommunity(new OctetString(community));
        communityTarget.setAddress(address);
        communityTarget.setVersion(version);
        communityTarget.setTimeout(timeout);
        communityTarget.setRetries(retry);

    }

    /**
     * 根据OID，获取单条消息
     */
    public JSONObject snmpGet(String oid) throws IOException {

        Snmp snmp = null;
        try {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            log.debug("-------> snmpGet 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, communityTarget);
            log.debug("snmpGet PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                throw new SystemException("snmpGet response is null,maybe request time out");
            } else {
                JSONObject data = new JSONObject(response.size());
                log.debug("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    log.debug(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());
                }
                log.debug("SNMP GET one OID value finished !");
                return data;
            }
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
    public JSONObject snmpGetList(String[] oidList) {

        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            log.debug("-------> snmpGetList 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, communityTarget);
            log.debug("snmpGetList PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                throw new SystemException("snmpGetList response is null,maybe request time out");
            } else {
                JSONObject data = new JSONObject(response.size());
                log.debug("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    log.debug(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());
                }
                log.debug("snmpGetList GET one OID value finished !");
                return data;
            }
        } catch (Exception e) {
            log.error("snmpGetList Get Exception:" , e);
            return null;
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
    public void snmpAsynGetList(List<String> oidList) {
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            log.debug("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, communityTarget);
            log.debug("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

	      /*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                @Override
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    log.debug("[request]:" + request);
                    if (response == null) {
                        log.debug("[ERROR]: response is null");
                    } else if (response.getErrorStatus() != 0) {
                        log.debug("[ERROR]: response status"
                                + response.getErrorStatus() + " Text:"
                                + response.getErrorStatusText());
                    } else {
                        log.debug("Received response Success!");
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            log.debug(vb.getOid() + " = " + vb.getVariable());
                        }
                        log.debug("SNMP Asyn GetList OID finished. ");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, communityTarget, null, listener);
            log.debug("asyn send pdu wait for response...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            log.debug("latch.await =:" + wait);

            snmp.close();

            log.debug("SNMP GET one OID value finished !");
        } catch (Exception e) {
            log.error("SNMP Get Exception:" , e);
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
     * 根据communityTargetOID，获取树形数据
     */
    public JSONObject snmpWalk(String communityTargetOid) {
        TransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID communityTargetOID = new OID(communityTargetOid);
            pdu.add(new VariableBinding(communityTargetOID));

            boolean finished = false;
            JSONObject data = new JSONObject();
            log.debug("----> snmpWalk start <----");
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, communityTarget);

                PDU response = respEvent.getResponse();
                if (null == response) {
                    log.debug("snmpWalk responsePDU == null");
                    finished = true;
                    return null;
                } else {
                    vb = response.get(0);
                }
                // check finish
                finished = checkWalkFinished(communityTargetOID, pdu, vb);

                if (!finished) {
                    log.debug(vb.getOid().toString() +"="+ vb.getVariable().toString());
                    data.put(vb.getOid().toString(), vb.getVariable().toString());

                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    log.debug("SNMP walk OID has finished.");
                    return data;
                }
            }
            return null;
        } catch (Exception e) {
            log.error("SNMP walk Exception: " , e);
            return null;
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

    private boolean checkWalkFinished(OID communityTargetOID, PDU pdu, VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            log.debug("[true] responsePDU.getErrorStatus() != 0 ");
            log.debug(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            log.debug("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < communityTargetOID.size()) {
            log.debug("[true] vb.getOid().size() < communityTargetOID.size()");
            finished = true;
        } else if (communityTargetOID.leftMostCompare(communityTargetOID.size(), vb.getOid()) != 0) {
            log.debug("[true] communityTargetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            log.debug("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(communityTargetOID) <= 0) {
            log.debug("[true] Variable received is not "
                    + "lexicographic successor of requested " + "one:");
            log.debug(vb.toString() + " <= " + communityTargetOID);
            finished = true;
        }
        return finished;

    }

    /**
     * 根据communityTargetOID，异步获取树形数据
     */
    public void snmpAsynWalk(String oid) {
        Snmp snmp = null;
        try {
            log.debug("----> demo start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID communityTargetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(communityTargetOID));

            ResponseListener listener = new ResponseListener() {
                @Override
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // log.debug("[request]:" + request);
                        if (response == null) {
                            log.debug("[ERROR]: response is null");
                        } else if (response.getErrorStatus() != 0) {
                            log.debug("[ERROR]: response status"
                                    + response.getErrorStatus() + " Text:"
                                    + response.getErrorStatusText());
                        } else {
                            log.debug("Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(communityTargetOID,
                                    pdu, vb);
                            if (!finished) {
                                log.debug(vb.getOid() + " = "
                                        + vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, communityTarget,
                                        null, this);
                            } else {
                                log.debug("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, communityTarget, null, listener);
            log.debug("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            log.debug("latch.await =:" + wait);
            snmp.close();

            log.debug("----> demo end <----");
        } catch (Exception e) {
            log.debug("SNMP Asyn Walk Exception:" ,e);
        }
    }

    /**
     * 根据OID和指定string来设置设备的数据
     */
    public void setPDU(String oid, String val) throws IOException {
        Snmp snmp = null;
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid), new OctetString(val)));
        pdu.setType(PDU.SET);

        DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        log.debug("-------> 发送PDU <-------");
        snmp.send(pdu, communityTarget);
        snmp.close();
    }

    public boolean testConnect() {
        return true;
    }
}
