package com.minkey.util;

import com.minkey.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * 系统IP获取
 * @author minkey
 *
 */
public class OSUtil {
	static Logger logger = LoggerFactory.getLogger(OSUtil.class);
	/**
	 * 判断当前操作是否Windows.
	 * 
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机IP地址，并自动区分Windows还是Linux操作系统
	 * 
	 * @return String
	 */
	public static String getLocalIP() {
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				//所有的ip地址
				List<InetAddress> allA = new ArrayList<InetAddress>();
				
				try {
					//如果host不为空，则使用host放入最前面优先选择。
					InetAddress inetAddress =  InetAddress.getLocalHost();
					if(null != inetAddress){
						ip = inetAddress;
						allA.add(ip);
					}
				} catch (Exception e) {
					logger.error("Get Localhost exception",e);
				}	
				
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					//特定情况，可以考虑用ni.getName判断
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						allA.add(ips.nextElement());
					}
				}
				
				for(InetAddress inAddress : allA){
					// 判断是否为localhost，是否为回环地址，127.开头的都是lookback地址
					if (inAddress.isSiteLocalAddress() && !inAddress.isLoopbackAddress() && inAddress.getHostAddress().indexOf(":") == -1) {
						//找到第一个合法的ip
						ip = inAddress;
						break;
					}
				}

			}
		} catch (Exception e) {
			throw new SystemException("Get this computer's ip exception.",e);
		}
		
		if (null == ip) {
			throw new SystemException("Get this computer's ip is null.");
		}

		logger.debug("This computer ip is "+ip.getHostAddress());

		
		return ip.getHostAddress();
	}

}
