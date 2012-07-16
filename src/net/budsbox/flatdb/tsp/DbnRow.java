package net.budsbox.flatdb.tsp;

import java.util.ArrayList;
import java.util.HashMap;

public class DbnRow {
	
	//Instance variables
	private String row = new String();
	private String rowType = new String();
	private ArrayList<Integer> attributeIndexArray = new ArrayList<Integer>();
	private ArrayList<String> attributes = new ArrayList<String>();
	private HashMap<Integer, String> hashAttributes = new HashMap<Integer, String>();
	
	//Constructor method
	public DbnRow(String parseRow, ArrayList<String> initFields, String type){
		
		int i = 0;
		this.row=parseRow;
		this.attributes=initFields;
		this.rowType=type;
		
		for (String field : attributes){
			if(!field.isEmpty()){
				this.attributeIndexArray.add(i, parseRow.indexOf(attributes.get(i)));
			}
			i++;
		}
	}
	
	//Instance methods
	public String getRow() {
		return row;
	}
	public ArrayList<Integer> getAttributeIndexArray() {
		return attributeIndexArray;
	}
	public ArrayList<String> getAttributes() {
		return attributes;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public HashMap<Integer, String> getHashAttributes() {
		return hashAttributes;
	}
	public void setHashAttributes(HashMap<Integer, String> hashAttributes) {
		this.hashAttributes = hashAttributes;
	}
	
}