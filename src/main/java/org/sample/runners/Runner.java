package org.sample.runners;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;

public class Runner
{
	private final static String AMOUNT_OPTION_NAME = "amount";
	
	public static void main(String args[])
	{
		// get env specific variables
		BoilerPlateConfig config = BoilerPlateConfig.createInstance();

		// Parsing cmdline options
		Options options = new Options();
		options.addOption( AMOUNT_OPTION_NAME, true, "amount you wanna charge" );
		GnuParser parser = new GnuParser();
		try
		{
			CommandLine cmd = parser.parse( options, args );
			System.out.println(config.getCardNumber());
			System.out.println(cmd.getOptionValue( AMOUNT_OPTION_NAME ) );
		}
		catch( Exception e )
		{
			throw new RuntimeException( "error parsing arguments", e );
		}
	}
}