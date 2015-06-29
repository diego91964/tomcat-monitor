package br.uaijug.tomcat.monitoring.valve;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import br.uaijug.tomcat.monitoring.connectors.InfluxConnector;
import br.uaijug.tomcat.monitoring.converters.GenericConverter;
import br.uaijug.tomcat.monitoring.domain.VmDynamicsProperties;
import br.uaijug.tomcat.monitoring.domain.VmStaticProperties;
import br.uaijug.tomcat.monitoring.monitors.VmDynamicsMonitor;
import br.uaijug.tomcat.monitoring.monitors.VmStaticMonitor;


/**
 * Responsible enable Valve to {@link VmDynamicsProperties}, {@link VmStaticProperties}.
 * This Valve is Based on {@link ValveBase}
 * 
 * @author Diego Silva
 *
 */
public class VmValve extends ValveToInfluxDB {

	Logger log = Logger.getLogger(VmValve.class.getName());
	
	@Override
	public void invoke(Request request, Response response) throws IOException,ServletException {

		getNext().invoke(request, response);
		
		if (needExecute(valveRate)){
			
			VmDynamicsProperties vmDynamicsProperties = VmDynamicsMonitor.getFullObject();

			VmStaticProperties vmStaticProperties = VmStaticMonitor.getFullObject();

			InfluxConnector influxConnector = new InfluxConnector(url, port,username, password);
			
			if (debug != null && debug){
				log.info("Saving vm properties " + vmDynamicsProperties + " - " + vmStaticProperties);
			}

			influxConnector.write(GenericConverter.getInstance().convert(vmStaticProperties , alias) , databaseName);

			influxConnector.write(GenericConverter.getInstance().convert(vmDynamicsProperties , alias) , databaseName);
		}
	}

}
