package br.uaijug.tomcat.monitoring.connectors;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Serie;

/**
 * This class is used to connect and writing in the database InfluxDB v(0.8.8)
 * 
 * @author Diego Silva
 *
 */
public class InfluxConnector {

	Logger log = Logger.getLogger(InfluxConnector.class.getName());
	
	/**
	 *  
	 */
	private static InfluxDB influxDB = null;
	
	/**
	 * Database URL
	 */
	private String url;
	
	/**
	 * Database to connect via commandline (Do not use http port) 
	 * By default the connect port of InfluxDB is 8086
	 */
	private String port;
	
	/**
	 * Databse username
	 */
	private String username;
	
	/**
	 * Databse password
	 */
	private String password;
	
	public InfluxConnector (String url, String port , String username, String password){
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
		
		open();
	}
	
	private void open() throws RuntimeException {

		if (influxDB != null) {
			return;
		}
		
		if (url == null) {
			throw new IllegalArgumentException("url can not be null");
		}
		
		
		if (url.toLowerCase().contains("http") || url.toLowerCase().contains("https")){
			influxDB = InfluxDBFactory.connect(url + ":" + port + "/",username, password);
			log.info("Connected to the influxdb");
		}else {
			throw new IllegalArgumentException("unknow url protocol ");
		}
	}

	protected void close() {
		influxDB = null;
	}

	protected Serie createSimpleSerie (Map<String,String> columnsValues , String serieName) {
		return new Serie.Builder (serieName)
		                 .columns((String[]) columnsValues.keySet().toArray())
		                 .values(columnsValues.values().toArray())
		                 .build();
	}
	
	public void write(Serie serie , String databaseName) {
		
		if (influxDB == null) open();
		influxDB.write(databaseName, TimeUnit.MILLISECONDS, serie);

	}
	

}
