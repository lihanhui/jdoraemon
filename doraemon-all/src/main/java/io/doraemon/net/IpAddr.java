package io.doraemon.net;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class IpAddr {
	private static Logger logger = LoggerFactory.getLogger(IpAddr.class);
	public static long ipToLong(String ipAddress) {
		long result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		//logger.info("{} {}",ipAddressInArray.length, ipAddress);
		int block = 3;
		for (int i = 0; i < ipAddressInArray.length && i <= 3; i++, block--) {
			long ip = Long.parseLong(ipAddressInArray[i]);
			//logger.info("{}",ipAddressInArray[i]);
			result |= ip << (block * 8);
		}
		return result;
	}

	public static String longToIp(long i) {
		return ((i >> 24) & 0xFF) + 
                   "." + ((i >> 16) & 0xFF) + 
                   "." + ((i >> 8) & 0xFF) + 
                   "." + (i & 0xFF);
	}
}
