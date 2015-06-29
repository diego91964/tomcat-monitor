package br.uaijug.tomcat.monitoring.monitors;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;

import br.uaijug.tomcat.monitoring.domain.OsDynamicsProperties;

/**
 * Responsible for seeking the values of {@link OsDynamicsProperties}
 * 
 * @author Diego Silva
 *
 */

public class OsDynamicsMonitor {

	@SuppressWarnings("restriction")
	public static Long getCpuTime() {

		HashMap<Long, Long> prevThreadCpuTime = null;
		Long cpuTime = 0L;
		Long totalThreadTime = 0L;

		ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
		OperatingSystemMXBean osMX = ManagementFactory
				.getOperatingSystemMXBean();

		String className = osMX.getClass().getName();
		if ("com.sun.management.OperatingSystem".equals(className) || "com.sun.management.UnixOperatingSystem".equals(className)) {
			cpuTime = ((com.sun.management.OperatingSystemMXBean) osMX).getProcessCpuTime();
			cpuTime /= 1000000L;
		} else {
			if (threadMX.isThreadCpuTimeEnabled()) {
				long[] ids = threadMX.getAllThreadIds();
				HashMap<Long, Long> threadCpuTime = new HashMap<Long, Long>();
				for (int t = 0; t < ids.length; t++) {
					long id = ids[t];
					if (id >= 0) {
						long threadtime = threadMX.getThreadCpuTime(id);
						if (threadtime >= 0) {
							threadCpuTime.put(id, threadtime);
							long prev = 0L;
							if (prevThreadCpuTime != null) {
								Long prevl = prevThreadCpuTime.get(id);
								if (prevl != null){
									prev = prevl.longValue();
								}
							}
							if (prev <= threadtime) {
								totalThreadTime += (threadtime - prev);
							} else {
								totalThreadTime += threadtime;
							}
						}
					}
				}
				cpuTime = (totalThreadTime / 1000000L)
						+ VmDynamicsMonitor.getGcTime()
						+ VmDynamicsMonitor.getCompilationTime();

			}
		}

		return cpuTime;
	}

	@SuppressWarnings("restriction")
	public static Integer getFileDescriptorOpenCount() {

		OperatingSystemMXBean osMX = ManagementFactory.getOperatingSystemMXBean();
		String className = osMX.getClass().getName();

		Long fileDescriptorOpenCount = 0L;
		if ("com.sun.management.UnixOperatingSystem".equals(className)) {
			fileDescriptorOpenCount = ((com.sun.management.UnixOperatingSystemMXBean) osMX).getOpenFileDescriptorCount();
		}

		return fileDescriptorOpenCount.intValue();
	}

	@SuppressWarnings("restriction")
	public static Integer getFileDescriptorMaxCount() {

		OperatingSystemMXBean osMX = ManagementFactory
				.getOperatingSystemMXBean();
		String className = osMX.getClass().getName();

		Long fileDescriptorMaxCount = 0L;
		if ("com.sun.management.UnixOperatingSystem".equals(className)) {
			fileDescriptorMaxCount = ((com.sun.management.UnixOperatingSystemMXBean) osMX).getMaxFileDescriptorCount();
		}

		return fileDescriptorMaxCount.intValue();
	}

	public static Long getMemory() {

		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();

		long memory = heapMemory.getCommitted() + nonHeapMemory.getCommitted();

		return memory;
	}

	public static Long getMaxMemory() {

		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();

		long maxMemory = heapMemory.getMax() + nonHeapMemory.getCommitted();

		return maxMemory;
	}
	
	
	
	public static OsDynamicsProperties getFullObject (){
		return new OsDynamicsProperties(getCpuTime(), getFileDescriptorOpenCount(), getFileDescriptorMaxCount(), getMemory(), getMaxMemory());
	}

}
