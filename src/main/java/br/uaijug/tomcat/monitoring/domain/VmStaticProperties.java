package br.uaijug.tomcat.monitoring.domain;


/**
 * Java Virtual Machine Static analyzed properties.
 * Static  properties are those that haven't changes over time. 
 * To change a static properties usually need restart application server.
 * 
 * @author Diego Silva
 *
 */

public class VmStaticProperties implements SerieConvertable{


	private String osRelease;
	private String vmName;
	private String vmVendor;
	private String vmVersion;
	
	public static final String[] fieldToColumns = new String[] {"osRelease","vmName","vmVendor","vmVersion"};  
	 
	public VmStaticProperties(String osRelease, String vmName, String vmVendor,
			String vmVersion) {
		super();
		this.osRelease = osRelease;
		this.vmName = vmName;
		this.vmVendor = vmVendor;
		this.vmVersion = vmVersion;
	}
	
	public String getOsRelease() {
		return osRelease;
	}
	public String getVmName() {
		return vmName;
	}
	public String getVmVendor() {
		return vmVendor;
	}
	public String getVmVersion() {
		return vmVersion;
	}

	@Override
	public String[] columns() {
		return fieldToColumns;
	}

	@Override
	public Object[] values() {
		return new String[] {osRelease,vmName,vmVendor,vmVersion};
	}
	 


	@Override
	public String toString() {
		return "VmStaticProperties [osRelease=" + osRelease + ", vmName="
				+ vmName + ", vmVendor=" + vmVendor + ", vmVersion="
				+ vmVersion + "]";
	}
}

