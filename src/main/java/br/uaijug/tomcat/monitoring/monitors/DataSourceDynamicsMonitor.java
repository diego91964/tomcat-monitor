package br.uaijug.tomcat.monitoring.monitors;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import br.uaijug.tomcat.monitoring.domain.DataSourceDynamicsProperties;

/**
 * Responsible for seeking the values of {@link DataSourceDynamicsProperties}
 * 
 * @author Diego Silva
 *
 */

public class DataSourceDynamicsMonitor {


	public static Integer getNumActive(String name , String context) {

		MBeanServer mbserver = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname;
		try {
			
			oname = ObjectName.getInstance("Catalina:type=DataSource,context="+context+",host=localhost,class=javax.sql.DataSource,name=\""+name+"\"");
			
			return (Integer) mbserver.getAttribute(oname, "numActive");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static Integer getNumIdle(String name, String context) {
		MBeanServer mbserver = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname;
		try {
			oname = ObjectName.getInstance("Catalina:type=DataSource,context="+context+",host=localhost,class=javax.sql.DataSource,name=\""+name+"\"");
			return (Integer) mbserver.getAttribute(oname, "numIdle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static DataSourceDynamicsProperties getFullObject (String name, String context){
		
		return new DataSourceDynamicsProperties(getNumActive(name, context), getNumIdle(name, context));
	}

}
