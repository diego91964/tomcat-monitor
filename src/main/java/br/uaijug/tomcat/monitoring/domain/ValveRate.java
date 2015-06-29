package br.uaijug.tomcat.monitoring.domain;


/**
 * As the analysis of the tomcat are based on request, the options are:
 * Request: All requests are analyzed.
 * Random Request: The function nextBoolean decide if code can be executed 
 * 
 * @author Diego Silva
 *
 */
public enum ValveRate {

	REQUEST, RANDOM_REQUEST  ;

}
