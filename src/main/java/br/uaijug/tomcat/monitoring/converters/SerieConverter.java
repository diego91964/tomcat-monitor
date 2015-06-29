package br.uaijug.tomcat.monitoring.converters;

import org.influxdb.dto.Serie;

/**
 * Interface used to convert Objects in Series
 * 
 * @author Diego Silva
 *
 * @param <T>
 */
public interface SerieConverter <T> {
	
	public Serie convert (T classToConvert , String alias);

}
