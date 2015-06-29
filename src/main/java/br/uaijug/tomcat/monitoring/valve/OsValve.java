package br.uaijug.tomcat.monitoring.valve;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import br.uaijug.tomcat.monitoring.connectors.InfluxConnector;
import br.uaijug.tomcat.monitoring.converters.GenericConverter;
import br.uaijug.tomcat.monitoring.domain.OsDynamicsProperties;
import br.uaijug.tomcat.monitoring.monitors.OsDynamicsMonitor;


/**
 * Responsible enable Valve to {@link OsDynamicsProperties}.
 * This Valve is Based on {@link ValveBase}
 * 
 * @author Diego Silva
 *
 */
public class OsValve extends ValveToInfluxDB{

	Logger log = Logger.getLogger(OsValve.class.getName());
	
	@Override
	public void invoke(Request request, Response response) throws IOException,
			ServletException {
		
		getNext().invoke(request, response);
		
		if (needExecute(valveRate)){
			
			OsDynamicsProperties osDynamicsProperties = OsDynamicsMonitor.getFullObject();
			
			InfluxConnector influxConnector = new InfluxConnector(url, port,username, password);

			if (debug != null && debug){
				log.info("Saving os properties " + osDynamicsProperties);
			}
			
			influxConnector.write(GenericConverter.getInstance().convert(osDynamicsProperties , alias), databaseName);
		}
			
	}
	
}
