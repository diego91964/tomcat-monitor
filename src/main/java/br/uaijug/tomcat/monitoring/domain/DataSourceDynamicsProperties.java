package br.uaijug.tomcat.monitoring.domain;

/**
 * Datasource analyzed properties.
 * 
 * @author Diego Silva
 *
 */
public class DataSourceDynamicsProperties implements SerieConvertable{

	private Integer numActive, numIdle;
	
	public static final String[] fieldToColumns = new String[] {"numActive", "numIdle"};
	
	
	public DataSourceDynamicsProperties(Integer numActive, Integer numIdle) {
		super();
		this.numActive = numActive;
		this.numIdle = numIdle;
	}

	public Integer getNumActive() {
		return numActive;
	}

	public void setNumActive(Integer numActive) {
		this.numActive = numActive;
	}

	public Integer getNumIdle() {
		return numIdle;
	}

	public void setNumIdle(Integer numIdle) {
		this.numIdle = numIdle;
	}

	@Override
	public String[] columns() {
		return fieldToColumns;
	}

	@Override
	public Object[] values() {
		return new Object[] {numActive, numIdle};
	}

	@Override
	public String toString() {
		return "DataSourceDynamicsProperties [numActive=" + numActive
				+ ", numIdle=" + numIdle + "]";
	}
	
	
}
