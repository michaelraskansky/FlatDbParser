package net.budsbox.pstool.main;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import net.budsbox.pstool.tsp.DbnHelper;
import net.budsbox.pstool.tsp.RowDefinition;
import net.budsbox.pstool.tsp.RuntimeStats;

public class FlatDbParser {

	//Runtime Configuration
	private RuntimeStats includedStats = new RuntimeStats();
	private String inputDir = new String();
	private String outputDir = new String();
	private String tspClass = new String();
	public static final ArrayList<String> supportedTypes = new ArrayList<String>();
	static {
		supportedTypes.add("EPC_SubscriberPot");
		supportedTypes.add("EPC_UsageControlAccumulatedPot");
	}
	public static final ArrayList<String> jsonFields = new ArrayList<String>();
	static {
		jsonFields.add("usageControlAccum");
	}

	public static void main(String[] args) throws IOException {
		
		
		FlatDbParser flatdb = new FlatDbParser();
		flatdb.parseArguments(args);
		flatdb.printRunSettings(flatdb);
		flatdb.startFlatDbParse();
		flatdb.includedStats.setEndTime(System.currentTimeMillis());
		flatdb.includedStats.printStatistics();
		
	}
	public static boolean isClassSupported(String type){
		boolean resault=false;
		for (String supported : supportedTypes) {
			if (supported.equals(type)) {
				resault = true;	
			}	
		}
		return resault;
	}
	public void startFlatDbParse() throws IOException{
		
		Yaml yamlAttributes = new Yaml();
		InputStream rowDefintion = FlatDbParser.class.getClass().getResourceAsStream("/definitions/" + tspClass + ".yml");
		DataInputStream io = new DataInputStream(rowDefintion);
		RowDefinition rowTemplate = (RowDefinition)yamlAttributes.load(io);
		
		if(isClassSupported(tspClass)) {
			DbnHelper.printToFile(inputDir, outputDir, tspClass, rowTemplate.getAttributes(), this.includedStats);
		} else {
			System.err.println("This class is not supported");
		}

	}
	public void printRunSettings(FlatDbParser flatdb) {
		
		Yaml config = new Yaml();
		System.out.println("Running With Following configuration");
		System.out.println("------------------------------------");
		System.out.println(config.dumpAsMap(flatdb));
		System.out.println("------------------------------------");
		System.out.println("");
		
	}
	public void parseArguments(String[] inputArgs) {
		ArrayList<String> arguments = new ArrayList<String>();
		for (String argument : inputArgs){
			arguments.add(argument);
		}
		
		for (String argument : arguments) {
			
				if (argument.startsWith("-i=") || argument.startsWith("--input-directory=")) {
					inputDir = argument.split("=")[1];
				}
				if (argument.startsWith("-o=") || argument.startsWith("--output-directory=")) {
					outputDir = argument.split("=")[1];
				}
				if (argument.startsWith("-c=") || argument.startsWith("--class=")) {
					tspClass = argument.split("=")[1];
				}
				
		}
	}
	
	public RuntimeStats getIncludedStats() {
		return includedStats;
	}
	public void setIncludedStats(RuntimeStats stats) {
		this.includedStats = stats;
	}
	public String getInputDir() {
		return inputDir;
	}
	public String getOutputDir() {
		return outputDir;
	}
	public String getTspClass() {
		return tspClass;
	}
	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	public void setTspClass(String tspClass) {
		this.tspClass = tspClass;
	}
	
}
/* TODO
	Number of files progress bar.
	Turn JSON to YAML object
	Create Configuration Class and load as YAML resource
*/