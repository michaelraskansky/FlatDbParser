package net.budsbox.flatdb.manager;

import org.yaml.snakeyaml.Yaml;

public class PmManager {
	
	private int statNumOfRows;
	private int statNumOfObject;
	private int statNumOfLinesSkiped;
	private int statNumOfFiles;
	private int statNumOfFailedFiles;
	
	
	private float statExecTimeSec;
	
	private final long startTime = System.currentTimeMillis();
	@SuppressWarnings("unused")
	private long endTime;
	
	public void printStatistics(){
		Yaml stats = new Yaml();
		System.out.println("Runtime Statistics");
		System.out.println("------------------------------------");
		System.out.println(stats.dumpAsMap(this));
		System.out.println("------------------------------------");
	}
	
	public void statNumOfObject(){
		this.statNumOfObject++;
	}
	public void statNumOfLinesSkiped(){
		this.statNumOfLinesSkiped++;
	}
	public void statNumOfRows(){
		this.statNumOfRows++;
	}
	public void statNumOfFiles(){
		this.statNumOfFiles++;
	}
	public void statNumOfFailedFiles(){
		this.statNumOfFailedFiles++;
	}
	
	public int getStatNumOfRows() {
		return statNumOfRows;
	}
	public void setStatNumOfRows(int statNumOfRows) {
		this.statNumOfRows = statNumOfRows;
	}
	public void setEndTime(long endTime) {
		this.statExecTimeSec = (endTime - startTime)/1000f;
	}
	public float getStatExecTimeSec() {
		return statExecTimeSec;
	}
	public void setStatExecTimeSec(float executionTime) {
		this.statExecTimeSec = executionTime;
	}
	public int getStatNumOfObject() {
		return statNumOfObject;
	}
	public int getStatNumOfLinesSkiped() {
		return statNumOfLinesSkiped;
	}
	public void setStatNumOfObject(int statNumOfObject) {
		this.statNumOfObject = statNumOfObject;
	}
	public void setStatNumOfLinesSkiped(int statNumOfLinesSkiped) {
		this.statNumOfLinesSkiped = statNumOfLinesSkiped;
	}

	public int getStatNumOfFiles() {
		return statNumOfFiles;
	}

	public void setStatNumOfFiles(int statNumOfFiles) {
		this.statNumOfFiles = statNumOfFiles;
	}

	public int getStatNumOfFailedFiles() {
		return statNumOfFailedFiles;
	}

	public void setStatNumOfFailedFiles(int statNumOfFailedFiles) {
		this.statNumOfFailedFiles = statNumOfFailedFiles;
	}
	
}
