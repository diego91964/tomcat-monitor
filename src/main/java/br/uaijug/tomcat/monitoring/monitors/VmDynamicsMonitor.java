package br.uaijug.tomcat.monitoring.monitors;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import br.uaijug.tomcat.monitoring.domain.VmDynamicsProperties;


/**
 * Responsible for seeking the values of {@link VmDynamicsProperties}
 * 
 * @author Diego Silva
 *
 */
public class VmDynamicsMonitor {

	public static Long getGcCount() {
		List<GarbageCollectorMXBean> gcMXList = ManagementFactory
				.getGarbageCollectorMXBeans();//

		Long gcCount = 0L;
		for (GarbageCollectorMXBean gcMX : gcMXList) {
			gcCount += gcMX.getCollectionCount();
		}

		return gcCount;
	}

	public static Long getGcTime() {

		List<GarbageCollectorMXBean> gcMXList = ManagementFactory
				.getGarbageCollectorMXBeans();//

		Long gcTime = 0L;
		for (GarbageCollectorMXBean gcMX : gcMXList) {
			gcTime += gcMX.getCollectionTime();
		}

		return gcTime;
	}

	public static Long getCompilationTime() {

		CompilationMXBean compilationMX = ManagementFactory
				.getCompilationMXBean();
		Long compilationTime = 0L;
		if (compilationMX.isCompilationTimeMonitoringSupported()) {
			compilationTime = compilationMX.getTotalCompilationTime();
		}

		return compilationTime;
	}

	public static Long getHeapMemoryInit() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		return heapMemory.getInit();

	}

	public static Long getHeapMemoryUsed() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		return heapMemory.getUsed();
	}

	public static Long getHeapMemoryCommitted() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		return heapMemory.getCommitted();
	}

	public static Long getHeapMemoryMax() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemory = memoryMX.getHeapMemoryUsage();
		return heapMemory.getMax();
	}

	public static Long getNonHeapMemoryInit() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();
		return nonHeapMemory.getInit();
	}

	public static Long getNonHeapMemoryUsed() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();
		return nonHeapMemory.getUsed();
	}

	public static Long getNonHeapMemoryCommitted() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();
		return nonHeapMemory.getCommitted();
	}

	public static Long getNonHeapMemoryMax() {
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		MemoryUsage nonHeapMemory = memoryMX.getNonHeapMemoryUsage();
		return nonHeapMemory.getMax();
	}

	public static Integer getMaxThreads() {

		MBeanServer mbserver = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname;
		Integer maxThreads = 0;

		try {
			oname = ObjectName.getInstance("Catalina:type=ThreadPool,*");

			Set<ObjectInstance> mbeans = mbserver.queryMBeans(oname, null);

			int maxCurrentThreadCount = 0;
			for (ObjectInstance oi : mbeans) {
				oname = oi.getObjectName();
				maxThreads = (Integer) mbserver.getAttribute(oname,	"maxThreads");
				Integer currentThreadCount = (Integer) mbserver.getAttribute(oname, "currentThreadCount");
				if (currentThreadCount != null	&& currentThreadCount.intValue() > maxCurrentThreadCount) {
					maxCurrentThreadCount = currentThreadCount.intValue();
				}
			}

		} catch (Exception e) {
			;
		}

		return maxThreads;
	}

	public static Integer getDaemonThreadCount() {
		
		ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
		return threadMX.getDaemonThreadCount();
	}
	
	public static Integer getThreadCount (){
		ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
		return threadMX.getThreadCount();
	}
	
	public static VmDynamicsProperties getFullObject (){
		
		return new VmDynamicsProperties(
				getGcCount().intValue(),
				getGcTime().intValue(), 
				getCompilationTime().intValue(),
				getHeapMemoryInit(),
				getHeapMemoryUsed(),
				getHeapMemoryCommitted(),
				getHeapMemoryMax(),
				getNonHeapMemoryInit(),
				getNonHeapMemoryUsed(),
				getNonHeapMemoryCommitted(),
				getNonHeapMemoryMax(),
				getMaxThreads(), 
				getThreadCount());
	}

}
