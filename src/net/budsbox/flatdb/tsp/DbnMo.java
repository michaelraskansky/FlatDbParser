package net.budsbox.flatdb.tsp;

import java.util.ArrayList;
import java.util.HashMap;

import net.budsbox.flatdb.main.FlatDbHelper;

public class DbnMo {

	private ArrayList<String> attributes = new ArrayList<String>();;
	public String className = new String();
	public HashMap<String, ArrayList<String>> attributeValuePair = new HashMap<String, ArrayList<String>>();

	public DbnMo() {
	}

	public DbnMo(DbnRow rp) {

		this.className = rp.getRowType();
		this.attributes = rp.getAttributes();

		for (String field : rp.getAttributes()) {
			this.attributeValuePair
					.put(field, FlatDbHelper.parseRow(rp, field));
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public HashMap<String, ArrayList<String>> getAttributeValuePair() {
		return attributeValuePair;
	}

	public ArrayList<String> getValue(int fieldNum) {
		return this.attributeValuePair.get(attributes.get(fieldNum));
	}

}