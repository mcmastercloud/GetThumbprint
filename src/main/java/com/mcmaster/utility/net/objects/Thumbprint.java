package com.mcmaster.utility.net.objects;

import java.util.Date;

public final class Thumbprint implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078491230745004863L;
	
	private String sha1;
	private String sha256;
	private String md5;
	private String issuerDN;
	private String subjectDN;
	private Date validFrom;
	private Date validTo;
	private CERTIFICATE_STATUS status;
	
	/**
	 * @author Stephen McMaster
	 * Provides the status of the certificate this Thumbprint relates to - e.g. whether it is yet to go live, is current, or has expired.
	 */
	public enum CERTIFICATE_STATUS {
		/**
		 * The certificate is not yet valid - validFrom is future-dated.
		 */
		NOT_YET_VALID,
		/**
		 * The certificate has expired - validTo is in the past.
		 */
		EXPIRED,
		/*
		 * The certificate is current - Today is between validFrom and validTo.
		 */
		CURRENT
	}
	/**
	 * @param sha1 SHA1 fingerprint
	 * @param sha256 SHA256 fingerprint
	 * @param md5 MD5 fingerprint
	 * @param issuer Issuer for the certificate
	 * @param subject Subject for the certificate
	 * @param status The status of the certificate this Thumbprint refers to
	 * @param validFrom The date the certificate is valid from
	 * @param validTo The date the certificate is valid to
	 */
	public Thumbprint(String sha1, String sha256, String md5, String issuer, String subject, CERTIFICATE_STATUS status, Date validFrom, Date validTo) {
		this.setSha1(sha1);
		this.setSha256(sha256);
		this.setMd5(md5);
		this.setIssuerDN(issuer);
		this.setSubjectDN(subject);
		this.setStatus(status);
		this.setValidFrom(validFrom);
		this.setValidTo(validTo);
	}
	
	/**
	 * @return Get SHA1 Fingerprint
	 */
	public String getSha1() {
		return sha1;
	}
	/**
	 * @param sha1 Set SHA1 Fingerprint
	 */
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}
	/**
	 * @return Get SHA256 Fingerprint
	 */
	public String getSha256() {
		return sha256;
	}
	/**
	 * @param sha256 Set SHA256 Fingerprint
	 */
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}
	/**
	 * @return Get MD5 Fingerprint
	 */
	public String getMd5() {
		return md5;
	}
	/**
	 * @param md5 Set MD5 Fingerprint
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return Get Issuer DN
	 */
	public String getIssuerDN() {
		return issuerDN;
	}

	/**
	 * @param issuerDN Set Issuer DN
	 */
	public void setIssuerDN(String issuerDN) {
		this.issuerDN = issuerDN;
	}

	/**
	 * @return Get Subject DN
	 */
	public String getSubjectDN() {
		return subjectDN;
	}

	/**
	 * @param subjectDN Set Subject DN
	 */
	public void setSubjectDN(String subjectDN) {
		this.subjectDN = subjectDN;
	}

	/**
	 * @return Returns the status of the certificate 
	 */
	public CERTIFICATE_STATUS getStatus() {
		return status;
	}
	
	
	/**
	 * @return Whether this Certificate is valid - e.g. Neither future-dated, nor expired
	 */
	public Boolean isValid() {
		return this.getStatus() == CERTIFICATE_STATUS.CURRENT ? true : false; 
	}

	/**
	 * @param status Sets the status of the certificate
	 */
	public void setStatus(CERTIFICATE_STATUS status) {
		this.status = status;
	}

	/**
	 * @return The date the Certificate this Thumbprint refers to is valid to
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom Sets the date the Certificate this Thumbprint refers to is valid from
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return The date the Certificate this Thumbprint refers to is valid to
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo Sets the date the Certificate this Thumbprint refers to is valid to
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
}
