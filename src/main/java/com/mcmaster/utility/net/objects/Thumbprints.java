package com.mcmaster.utility.net.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Stephen McMaster
 *
 */
public final class Thumbprints implements java.io.Serializable {

	/**
	 * Wrapper for the {@link com.mcmaster.utility.net.objects.Thumbprint} object.<br/><br/>
	 * As well as returning all Thumbprints, this class identifies the first (e.g. Server) and last (e.g. Root) Thumbprints.
	 */
	private static final long serialVersionUID = -6445040606809602191L;
	private List<Thumbprint> thumbprints = new ArrayList<Thumbprint>();
	private Boolean succeeded = true;
	private String message = "";
	
	/**
	 * This constructor is used to create an Empty Thumbprints object with a Message set and Succeeded = false.  The aim is to ensure that this object is always returned for Error handling purposes
	 * @param sError Error message
	 */
	public Thumbprints(String sError) {
		this.setMessage(sError);
		this.setSucceeded(false);
	}
	
	public Thumbprints() {
	}

	/**
	 * @return Returns all Thumbprints
	 */
	public List<Thumbprint> getThumbprints() {
		return Collections.unmodifiableList(this.thumbprints);
	}

	/**
	 * @return Returns the last Thumbprint (e.g. the Root CA Thumbprint)
	 */
	public Thumbprint getLast() {
		if(this.thumbprints.size() > 0) {
			return this.thumbprints.get(thumbprints.size() - 1);
		} else {
			return null;
		}
	}
	
	/**
	 * @return Returns the first Thumbprint (e.g. the Server Certificate Thumbprint)
	 */
	public Thumbprint getFirst() {
		if(this.thumbprints.size() > 0) {
			return this.thumbprints.get(0);
		} else {
			return null;
		}
	}

	
	/**
	 * @return The number of Thumbprints in this Class.
	 */
	public int getCount() {
		return this.thumbprints.size();
	}
	
	
	/**
	 * Helper function to construct a Thumbprints object, and to add it to this collection.
	 * @param md5 MD5 Thumbprint
	 * @param sha1 SHA1 Thumbprint
	 * @param sha256 SHA256 Thumbprint
	 * @param issuer Issuer DN
	 * @param subject Subject DN
	 * @param status The Status of the certificate
	 * @param validFrom The date the certificate is valid from
	 * @param validTo The date the certificate is valid to
	 */
	public void Add(String md5, String sha1, String sha256, String issuer, String subject, Thumbprint.CERTIFICATE_STATUS status, Date validFrom, Date validTo) {
		this.thumbprints.add(new Thumbprint(sha1, sha256, md5, issuer, subject, status, validFrom, validTo));
	}

	/**
	 * @return Error Message
	 */
	public String getMessage() {
		return message;
	}

	
	
	/**
	 * @return True when the retrieval of Thumbprints was successful, otherwise false.
	 */
	public Boolean getSucceeded() {
		return succeeded;
	}

	/**
	 * @param succeeded Set whether the Thumbprint Retrieval has succeeded or not.
	 */
	public void setSucceeded(Boolean succeeded) {
		this.succeeded = succeeded;
	}

	/**
	 * @param message Set the message for the Return object - e.g. when there has been an error.
	 */
	private void setMessage(String message) {
		this.message = message;
	}


}
