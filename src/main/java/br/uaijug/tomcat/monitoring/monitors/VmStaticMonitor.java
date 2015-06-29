package br.uaijug.tomcat.monitoring.monitors;

import java.lang.management.ManagementFactory;

import br.uaijug.tomcat.monitoring.domain.VmStaticProperties;


/**
 * Responsible for seeking the values of {@link VmStaticProperties}
 * 
 * @author Diego Silva
 *
 */
public class VmStaticMonitor {

	public static String getOsRelease() {
		return System.getProperty("java.version");
	}

	public static String getVmName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public static String getVmVendor() {
		return ManagementFactory.getRuntimeMXBean().getVmVendor();
	}

	public static String getVmVersion() {
		return ManagementFactory.getRuntimeMXBean().getVmVersion();
	}
	
	public static VmStaticProperties getFullObject (){
		return new VmStaticProperties(getOsRelease(), getVmName(), getVmVendor(), getVmVersion());
	}

}
