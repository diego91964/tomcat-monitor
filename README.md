# tomcat-monitor

# Usage 

<pre>

<Valve className="br.uaijug.tomcat.monitoring.valve.VmDynamicValve" 
            url="http://localhost"
            port="8086" 
            username="root"
            password="root" 
         databaseName="tomcat-monitor-vm"
              valveRate="random_request"
              debug="false" />

<pre/>

<pre>

<Valve className="br.uaijug.tomcat.monitoring.valve.VmValve" 
            url="http://localhost"
            port="8086" 
            username="root"
            password="root" 
         databaseName="tomcat-monitor-vm"
              valveRate="request"
              debug="false" />

<pre/>

<pre>

<Valve className="br.uaijug.tomcat.monitoring.valve.OsValve" 
            url="http://localhost"
            port="8086" 
            username="root"
            password="root" 
         databaseName="tomcat-monitor-os"
              valveRate="request"
              debug="true" />

<pre/>

<pre>
  <Valve className="br.uaijug.tomcat.monitoring.valve.DataSourceValve" 
            url="http://localhost"
            port="8086" 
            username="root"
            password="root" 
         databaseName="tomcat-monitor-ds"
              valveRate="request" 
          datasourceName="jdbc/desenvolvimentoDB2DataSource" 
          context = "/"
          debug="false"/>

<pre/>
