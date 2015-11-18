package org.sample.runners;

import java.io.InputStream;
import java.util.Properties;

public class BoilerPlateConfig
{
	private Properties _properties;

	protected BoilerPlateConfig( String propertiesName )
	{
		try
		{
			_properties = new Properties();
			InputStream configReader = readPropertiesFile( propertiesName );
			try
			{
				_properties.load( configReader );
			}
			finally
			{
				configReader.close();
			}

			InputStream overrideReader = readPropertiesFile( propertiesName + ".local" );
			if( overrideReader != null )
			{
				try
				{
					_properties.load( overrideReader );
				}
				finally
				{
					overrideReader.close();
				}
			}
		}
		catch( Exception e )
		{
			throw new RuntimeException( "Error loading config file " + propertiesName, e );
		}
	}
	
	public static BoilerPlateConfig createInstance()
	{
		return new BoilerPlateConfig( "boiler_plate.properties" );
	}
	
	private InputStream readPropertiesFile( String filename )
	{
		return getClass().getClassLoader().getResourceAsStream( filename );
	}

	public final String getConfigString( String key )
	{
		return _properties.getProperty( key );
	}
	
	public final String getCardNumber()
	{
		return _properties.getProperty( "card.number" );
	}
}
