package br.uaijug.tomcat.monitoring.domain;


/**
 * Java Virtual Machine Dynamics analyzed properties.
 * Dynamic properties are those that have a large number of changes over time.
 * 
 * @author Diego Silva
 *
 */

public class VmDynamicsProperties implements SerieConvertable{

	
	private Integer gcCount;
	private Integer gcTime;
	private Integer compilationTime;
	private Long heapMemoryInit;
	private Long heapMemoryUsed;
	private Long heapMemoryCommitted;
	private Long heapMemoryMax;
	private Long nonHeapMemoryInit;
	private Long nonHeapMemoryUsed;
	private Long nonHeapMemoryCommitted;
	private Long nonHeapMemoryMax;
	private Integer maxThreads;
	private Integer threadCount;
	
	public static final String[] fieldToColumns = new String[]
			{
		   		"gcCount","gcTime","compilationTime","heapMemoryInit",
		   		"heapMemoryUsed","heapMemoryCommitted","heapMemoryMax","nonHeapMemoryInit",
		   		"nonHeapMemoryUsed","nonHeapMemoryCommitted","nonHeapMemoryMax","maxThreads", 
		   		"threadCount"
		    };
	
	public VmDynamicsProperties(Integer gcCount, Integer gcTime,
			Integer compilationTime, Long heapMemoryInit, Long heapMemoryUsed,
			Long heapMemoryCommitted, Long heapMemoryMax,
			Long nonHeapMemoryInit, Long nonHeapMemoryUsed,
			Long nonHeapMemoryCommitted, Long nonHeapMemoryMax,
			Integer maxThreads, Integer threadCount) {
		super();
		this.gcCount = gcCount;
		this.gcTime = gcTime;
		this.compilationTime = compilationTime;
		this.heapMemoryInit = heapMemoryInit;
		this.heapMemoryUsed = heapMemoryUsed;
		this.heapMemoryCommitted = heapMemoryCommitted;
		this.heapMemoryMax = heapMemoryMax;
		this.nonHeapMemoryInit = nonHeapMemoryInit;
		this.nonHeapMemoryUsed = nonHeapMemoryUsed;
		this.nonHeapMemoryCommitted = nonHeapMemoryCommitted;
		this.nonHeapMemoryMax = nonHeapMemoryMax;
		this.maxThreads = maxThreads;
		this.threadCount = threadCount;
	}
	
	
	public Integer getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}
	public Integer getGcCount() {
		return gcCount;
	}
	public void setGcCount(Integer gcCount) {
		this.gcCount = gcCount;
	}
	public Integer getGcTime() {
		return gcTime;
	}
	public void setGcTime(Integer gcTime) {
		this.gcTime = gcTime;
	}
	public Integer getCompilationTime() {
		return compilationTime;
	}
	public void setCompilationTime(Integer compilationTime) {
		this.compilationTime = compilationTime;
	}
	public Long getHeapMemoryInit() {
		return heapMemoryInit;
	}
	public void setHeapMemoryInit(Long heapMemoryInit) {
		this.heapMemoryInit = heapMemoryInit;
	}
	public Long getHeapMemoryUsed() {
		return heapMemoryUsed;
	}
	public void setHeapMemoryUsed(Long heapMemoryUsed) {
		this.heapMemoryUsed = heapMemoryUsed;
	}
	public Long getHeapMemoryCommitted() {
		return heapMemoryCommitted;
	}
	public void setHeapMemoryCommitted(Long heapMemoryCommitted) {
		this.heapMemoryCommitted = heapMemoryCommitted;
	}
	public Long getHeapMemoryMax() {
		return heapMemoryMax;
	}
	public void setHeapMemoryMax(Long heapMemoryMax) {
		this.heapMemoryMax = heapMemoryMax;
	}
	public Long getNonHeapMemoryInit() {
		return nonHeapMemoryInit;
	}
	public void setNonHeapMemoryInit(Long nonHeapMemoryInit) {
		this.nonHeapMemoryInit = nonHeapMemoryInit;
	}
	public Long getNonHeapMemoryUsed() {
		return nonHeapMemoryUsed;
	}
	public void setNonHeapMemoryUsed(Long nonHeapMemoryUsed) {
		this.nonHeapMemoryUsed = nonHeapMemoryUsed;
	}
	public Long getNonHeapMemoryCommitted() {
		return nonHeapMemoryCommitted;
	}
	public void setNonHeapMemoryCommitted(Long nonHeapMemoryCommitted) {
		this.nonHeapMemoryCommitted = nonHeapMemoryCommitted;
	}
	public Long getNonHeapMemoryMax() {
		return nonHeapMemoryMax;
	}
	public void setNonHeapMemoryMax(Long nonHeapMemoryMax) {
		this.nonHeapMemoryMax = nonHeapMemoryMax;
	}
	public Integer getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}


	@Override
	public String[] columns() {
		return fieldToColumns;
	}


	@Override
	public Object[] values() {
		return new Object[]
				{
					gcCount,gcTime,compilationTime,heapMemoryInit,
			   		heapMemoryUsed,heapMemoryCommitted,heapMemoryMax,nonHeapMemoryInit,
			   		nonHeapMemoryUsed,nonHeapMemoryCommitted,nonHeapMemoryMax,maxThreads, 
			   		threadCount
			    };
	}


	@Override
	public String toString() {
		return "VmDynamicsProperties [gcCount=" + gcCount + ", gcTime="
				+ gcTime + ", compilationTime=" + compilationTime
				+ ", heapMemoryInit=" + heapMemoryInit + ", heapMemoryUsed="
				+ heapMemoryUsed + ", heapMemoryCommitted="
				+ heapMemoryCommitted + ", heapMemoryMax=" + heapMemoryMax
				+ ", nonHeapMemoryInit=" + nonHeapMemoryInit
				+ ", nonHeapMemoryUsed=" + nonHeapMemoryUsed
				+ ", nonHeapMemoryCommitted=" + nonHeapMemoryCommitted
				+ ", nonHeapMemoryMax=" + nonHeapMemoryMax + ", maxThreads="
				+ maxThreads + ", threadCount=" + threadCount + "]";
	}
	
	
	
}
