package io.doraemon.util;

import java.lang.management.ManagementFactory;

public class VmUtil {

	public static long getStartTime() {
        return ManagementFactory.getRuntimeMXBean().getStartTime();
	}
	
	public static long getUpTime() {
        return ManagementFactory.getRuntimeMXBean().getUptime();
	}
}
