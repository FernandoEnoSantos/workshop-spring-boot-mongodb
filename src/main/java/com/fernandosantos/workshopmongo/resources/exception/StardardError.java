package com.fernandosantos.workshopmongo.resources.exception;

import java.io.Serializable;

public class StardardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long timstamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StardardError() {
	}

	public StardardError(Long timstamp, Integer status, String error, String message, String path) {
		this.timstamp = timstamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimstamp() {
		return timstamp;
	}

	public void setTimstamp(Long timstamp) {
		this.timstamp = timstamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
