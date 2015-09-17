package com.example.menu;

import java.io.Serializable;

/**
 * Clase de ejemplo para almacenar un valor y una etiqueta que lo identifica
 * 
 * @author Juan Carlos
 * 
 */
public class Record implements Serializable{
	/**
	 * 
	 */
	transient private static final long serialVersionUID = -319981228737406507L;
	transient private long id;
	private String tag;
	private long value;

	public Record() {
		this.tag = "";
		this.value = 0;

	}

	public Record(String key, int value) {
		this.tag = key;
		this.value = value;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return tag+"="+value;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}