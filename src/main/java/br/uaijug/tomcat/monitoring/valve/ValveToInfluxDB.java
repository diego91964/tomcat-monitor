package br.uaijug.tomcat.monitoring.valve;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.catalina.valves.ValveBase;

import br.uaijug.tomcat.monitoring.domain.ValveRate;

/**
 * Responsible enable Valve to monitoring generic performance properties.
 * @see {@link DataSourceValve}, {@link OsValve}, {@link VmDynamicValve}, {@link VmValve}
 * @author Diego Silva
 *
 */
public abstract class ValveToInfluxDB extends ValveBase{

	
		
	    /**
	     * The connection user name to use when trying to connect to the database.
	     */
	    protected String username = null;

	    /**
	     * The connection password to use when trying to connect to the database.
	     */
	    protected String password = null;

	    /**
	     * The connection URL to use when trying to connect to the database.
	     */
	    protected String url = null;
	    
	    /** The port to connect in InfluxDb**/
	    
	    protected String port = null;
	    
	    
		/**
	     * The series name where the logs will be stored
	     */
	    protected String alias = null;
	    
	    /**
	     * The database name where the logs will be stored
	     */
	    protected String databaseName = null;
	    
	    /** The rate in millis that the valve need to sample enviroment **/
	    
	    protected String valveRate = null;
	    
	    /**
	     * active Debug Mode  
	     */
	    protected Boolean debug;
	    

		Logger log = Logger.getLogger(VmValve.class.getName());
	    

	    
	    protected Boolean needExecute (String valveRate){
			
			String valveRateNormalized = valveRate == null ? "REQUEST" : valveRate.toUpperCase();
			
			ValveRate enumValveRate =  ValveRate.valueOf(valveRateNormalized);
			
			if (enumValveRate.equals(ValveRate.REQUEST)){
				return true;
			}else if (enumValveRate.equals(ValveRate.RANDOM_REQUEST)){
				 return new Random().nextBoolean();
			} else {
				
				if (debug != null && debug){
					log.log(Level.INFO, "Getting default valve rate (REQUEST), because null value");
				}
					
				return true;
			}
			
			
		}


	    public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}
		
	
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}


		public String getDatabaseName() {
			return databaseName;
		}

		public void setDatabaseName(String databaseName) {
			this.databaseName = databaseName;
		}

		
		public Boolean getDebug() {
			return debug;
		}


		public void setDebug(Boolean debug) {
			this.debug = debug;
		}


		public String getValveRate() {
			return valveRate;
		}


		public void setValveRate(String valveRate) {
			this.valveRate = valveRate;
		}


		public String getAlias() {
			return alias;
		}


		public void setAlias(String alias) {
			this.alias = alias;
		}

}
