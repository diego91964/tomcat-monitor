package br.uaijug.tomcat.monitoring.valve;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import br.uaijug.tomcat.monitoring.connectors.InfluxConnector;
import br.uaijug.tomcat.monitoring.converters.GenericConverter;
import br.uaijug.tomcat.monitoring.domain.DataSourceDynamicsProperties;
import br.uaijug.tomcat.monitoring.monitors.DataSourceDynamicsMonitor;

/**
 * Responsible enable Valve to {@link DataSourceDynamicsProperties}.
 * This Valve is Based on {@link ValveBase}
 * 
 * @author Diego Silva
 *
 */
public class DataSourceValve extends ValveToInfluxDB{

	private String datasourceName;
	private String context;
	
	Logger log = Logger.getLogger(DataSourceValve.class.getName());
	
	@Override
	public void invoke(Request request, Response response) throws IOException,
			ServletException {
		
		getNext().invoke(request, response);
		
		if (needExecute(valveRate)){
			
			DataSourceDynamicsProperties dataSourceDynamicsProperties = DataSourceDynamicsMonitor.getFullObject(datasourceName , context);
			
			InfluxConnector influxConnector = new InfluxConnector(url, port,username, password);
			
			if (debug != null && debug){
				log.info("Saving datasource properties " + dataSourceDynamicsProperties);
			}

			influxConnector.write(GenericConverter.getInstance().convert(dataSourceDynamicsProperties , alias), databaseName);
		}
	}
	
	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
