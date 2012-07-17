package net.budsbox.flatdb.main;

import java.io.DataInputStream;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;

import net.budsbox.flatdb.manager.ConfigManager;
import net.budsbox.flatdb.manager.PmManager;
import net.budsbox.flatdb.tsp.RowDefinition;

public class FlatDbParser {

	//Runtime Configuration
	private PmManager performance = new PmManager();
	private ConfigManager configuration;

	public static void main(String[] args) {
		
		FlatDbParser flatdb = new FlatDbParser();
		flatdb.configuration = new ConfigManager(args);
		
		if (flatdb.configuration.isConfigInit()){
			
			flatdb.printRunSettings(flatdb);
			flatdb.startFlatDbParse();
			flatdb.performance.setEndTime(System.currentTimeMillis());
			flatdb.performance.printStatistics();
			
		} else {
			
			flatdb.configuration.printHelp();
			
		}
	}
	
	public void startFlatDbParse(){
		
		Yaml yamlAttributes = new Yaml();
		InputStream rowDefintion = FlatDbParser.class.getClass().getResourceAsStream("/definitions/" + configuration.getTspClass() + ".yml");
		DataInputStream io = new DataInputStream(rowDefintion);
		RowDefinition rowTemplate = (RowDefinition)yamlAttributes.load(io);
		FlatDbHelper.printToFile(configuration.getInputDir(), configuration.getOutputDir(), configuration.getTspClass(), 
				rowTemplate.getAttributes(), performance);

	}
	
	public void printRunSettings(FlatDbParser flatdb) {
		
		Yaml config = new Yaml();
		System.out.println("Running With Following configuration");
		System.out.println("------------------------------------");
		System.out.println(config.dumpAsMap(flatdb));
		System.out.println("------------------------------------");
		System.out.println("");
		
	}

	public PmManager getPerformance() {
		return performance;
	}

	public void setPerformance(PmManager performance) {
		this.performance = performance;
	}

	public ConfigManager getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigManager configuration) {
		this.configuration = configuration;
	}
	
}
/* TODO
	Number of files progress bar.
	Turn JSON to YAML object
	Create Configuration Class and load as YAML resource
*/
