package com.myspace.spreadsheet_decisiontable;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class ClientObject implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private int id;
	private java.lang.String descr;

	private boolean pass=true;

	public ClientObject() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getDescr() {
		return this.descr;
	}

	public void setDescr(java.lang.String descr) {
		this.descr = descr;
	}

	public boolean isPass() {
		return this.pass;
	}

	public void setPass(boolean isPass) {
		this.pass = isPass;
	}

	public ClientObject(int id, java.lang.String descr, boolean isPass) {
		this.id = id;
		this.descr = descr;
		this.pass = isPass;
	}

}
