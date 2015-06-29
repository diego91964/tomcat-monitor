package br.uaijug.tomcat.monitoring.domain;


/**
 * Operational System analyzed properties.
 * 
 * @author Diego Silva
 *
 */

public class OsDynamicsProperties implements SerieConvertable{

	private Long cpuTime;
	private Integer fileDescriptorOpenCount;
	private Integer fileDescriptorMaxCount;
	private Long memory;
	private Long maxMemory;

	public static final String[] fieldToColumns = new String[] {
		"cpuTime", "fileDescriptorOpenCount","fileDescriptorMaxCount",
		"memory" , "maxMemory"};
	
	public OsDynamicsProperties(Long cpuTime,
			Integer fileDescriptorOpenCount, Integer fileDescriptorMaxCount,
			Long memory, Long maxMemory) {
		super();
		this.cpuTime = cpuTime;
		this.fileDescriptorOpenCount = fileDescriptorOpenCount;
		this.fileDescriptorMaxCount = fileDescriptorMaxCount;
		this.memory = memory;
		this.maxMemory = maxMemory;
	}
	
	public Long getCpuTime() {
		return cpuTime;
	}
	public void setCpuTime(Long cpuTime) {
		this.cpuTime = cpuTime;
	}
	public Integer getFileDescriptorOpenCount() {
		return fileDescriptorOpenCount;
	}
	public void setFileDescriptorOpenCount(Integer fileDescriptorOpenCount) {
		this.fileDescriptorOpenCount = fileDescriptorOpenCount;
	}
	public Integer getFileDescriptorMaxCount() {
		return fileDescriptorMaxCount;
	}
	public void setFileDescriptorMaxCount(Integer fileDescriptorMaxCount) {
		this.fileDescriptorMaxCount = fileDescriptorMaxCount;
	}
	public Long getMemory() {
		return memory;
	}
	public void setMemory(Long memory) {
		this.memory = memory;
	}
	public Long getMaxMemory() {
		return maxMemory;
	}
	public void setMaxMemory(Long maxMemory) {
		this.maxMemory = maxMemory;
	}

	@Override
	public String[] columns() {
		return fieldToColumns;
	}

	@Override
	public Object[] values() {
		return new Object [] {
				cpuTime, fileDescriptorOpenCount,fileDescriptorMaxCount,
				memory , maxMemory};
	}

	@Override
	public String toString() {
		return "OsDynamicsProperties [cpuTime=" + cpuTime
				+ ", fileDescriptorOpenCount=" + fileDescriptorOpenCount
				+ ", fileDescriptorMaxCount=" + fileDescriptorMaxCount
				+ ", memory=" + memory + ", maxMemory=" + maxMemory + "]";
	}

}
