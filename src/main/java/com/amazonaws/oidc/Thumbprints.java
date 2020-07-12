package com.amazonaws.oidc;

import java.util.ArrayList;
import java.util.List;

public class Thumbprints implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6445040606809602191L;
	private List<Thumbprint> thumbprints = new ArrayList<Thumbprint>();
	private Boolean succeeded = true;
	private String message = null;
	
	public Thumbprints(String sError) {
		this.setMessage(sError);
		this.setSucceeded(false);
	}
	
	public Thumbprints() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the thumbprints
	 */
	public List<Thumbprint> getThumbprints() {
		return thumbprints;
	}
	/**
	 * @param thumbprints the thumbprints to set
	 */
	public void setThumbprints(List<Thumbprint> thumbprints) {
		this.thumbprints = thumbprints;
	}
	/**
	 * @return the last
	 */
	public Thumbprint getLast() {
		if(this.thumbprints.size() > 0) {
			return this.thumbprints.get(thumbprints.size() - 1);
		} else {
			return null;
		}
	}
	
	/**
	 * @return the first
	 */
	public Thumbprint getFirst() {
		if(this.thumbprints.size() > 0) {
			return this.thumbprints.get(0);
		} else {
			return null;
		}
	}

	public int getCount() {
		return this.thumbprints.size();
	}
	
	public void Add(String md5, String sha1, String sha256, String issuer, String subject) {
		this.thumbprints.add(new Thumbprint(sha1, sha256, md5, issuer, subject));
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the succeeded
	 */
	public Boolean getSucceeded() {
		return succeeded;
	}

	/**
	 * @param succeeded the succeeded to set
	 */
	public void setSucceeded(Boolean succeeded) {
		this.succeeded = succeeded;
	}
	
}
