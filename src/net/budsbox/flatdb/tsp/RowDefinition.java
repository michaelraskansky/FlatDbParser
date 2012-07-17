package net.budsbox.flatdb.tsp;

import java.util.ArrayList;

public class RowDefinition {

	private ArrayList<String> attributes = new ArrayList<String>();
	private String className = new String();

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public String getClassName() {
		return className;
	}

	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}