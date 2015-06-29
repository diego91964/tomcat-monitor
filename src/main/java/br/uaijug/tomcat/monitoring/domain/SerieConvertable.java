package br.uaijug.tomcat.monitoring.domain;

import org.influxdb.dto.Serie;

import br.uaijug.tomcat.monitoring.converters.GenericConverter;

/**
 * All classes that implements this interface can be converted in a {@link Serie} by {@link GenericConverter}
 * @author Diego Silva
 *
 */
public interface SerieConvertable {

	String [] columns ();
	Object [] values ();
}
