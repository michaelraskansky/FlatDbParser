package net.budsbox.pstool.adapters;


import java.io.File;
import java.util.ArrayList;

public class FileAdapter {
	
	private File directoryName;
	private ArrayList<String> files = new ArrayList<String>();
	
	public FileAdapter(String directoryInput){
		
		this.directoryName = new File(directoryInput);
		
		for (File fileEntry : directoryName.listFiles()) {
			
			if (!fileEntry.isDirectory()) {
				
				this.files.add(fileEntry.getAbsolutePath());
				
			}
			
		}
		
	}
	
	public String getOneFile(){
		
		String file = null;
		
		if (!files.isEmpty()){
			
			file = files.get(0);
			files.remove(0);
			
		}
		
		return file;
		
	}
	
	public File getDirectoryName() {
		return directoryName;
	}

	public ArrayList<String> getFiles() {
		return files;
	}
	
}