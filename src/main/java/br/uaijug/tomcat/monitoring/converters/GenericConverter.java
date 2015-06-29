package br.uaijug.tomcat.monitoring.converters;

import org.influxdb.dto.Serie;

import br.uaijug.tomcat.monitoring.domain.SerieConvertable;

/**
 * The most generic converter.  
 * 
 * @author Diego Silva
 *
 */
public class GenericConverter implements SerieConverter<SerieConvertable>{

	private  GenericConverter() {
		super();
	}
	
	public static GenericConverter getInstance (){
		return new GenericConverter();
	}
	@Override
	public Serie convert(SerieConvertable serieConvertable , String alias) {
		
		String seriesName = alias == null ?  serieConvertable.getClass().getSimpleName() : alias;
		
		return new Serie .Builder(seriesName)
          					  .columns(serieConvertable.columns())
          					  .values(serieConvertable.values())
          					  .build(); 
          	
	}

}
