package net.budsbox.flatdb.manager;

import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class ConfigManager {
	private ArrayList<String> jsonFields = new ArrayList<String>();
	private ArrayList<String> supportedTypes = new ArrayList<String>();
	{
		supportedTypes.add("EPC_SubscriberPot");
		supportedTypes.add("EPC_UsageControlAccumulatedPot");
	}
	{
		jsonFields.add("usageControlAccum");
	}
	private String inputDir = new String();
	private String outputDir = new String();
	private String tspClass = new String();
	private boolean configInit;
	
	private Options options = new Options();
	{
		options.addOption("i", true, "Set input directory");
		options.addOption("o", true, "Set output directory");
		options.addOption("c", true, "Set class to parse");	
	}
	
	public ConfigManager(String[] args){
	
		CommandLineParser parser = new PosixParser();
		
		try {
			CommandLine cmd = parser.parse( options, args);
			if (cmd.hasOption("i") && cmd.hasOption("o") && cmd.hasOption("c")) {
				this.inputDir = cmd.getOptionValue("i");
				this.outputDir = cmd.getOptionValue("o");
				this.tspClass = cmd.getOptionValue("c");
				this.configInit=true;
			} else {
				this.configInit=false;
			}
		} catch (ParseException e) {
			this.configInit=false;
			System.out.println("Unknown option!");
			printHelp();
		} finally {
			if (!isClassSupported(tspClass)){
				this.tspClass="EPC_SubscriberPot";
			}
		}
	}
	
	/**
   	* Check to see if class is supported by FlatDbparser
   	* This returns a boolean values true if the class is
   	* supported false if it is not supported
   	*/
	public boolean isClassSupported(String type){
		boolean resault=false;
		for (String supported : supportedTypes) {
			if (supported.equals(type)) {
				resault = true;	
			}	
		}
		return resault;
	}
	
	public void printHelp(){
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "FlatDbParser -i -o -c", options );		
	}

	public ArrayList<String> getJsonFields() {
		return jsonFields;
	}

	public void setJsonFields(ArrayList<String> jsonFields) {
		this.jsonFields = jsonFields;
	}

	public ArrayList<String> getSupportedTypes() {
		return supportedTypes;
	}

	public void setSupportedTypes(ArrayList<String> supportedTypes) {
		this.supportedTypes = supportedTypes;
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getTspClass() {
		return tspClass;
	}

	public void setTspClass(String tspClass) {
		this.tspClass = tspClass;
	}

	public boolean isConfigInit() {
		return configInit;
	}

	public void setConfigInit(boolean configInit) {
		this.configInit = configInit;
	}

}