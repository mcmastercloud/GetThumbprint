package com.amazonaws.oidc;

public class Thumbprint implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078491230745004863L;
	private String sha1;
	private String sha256;
	private String md5;
	private String issuerDN;
	private String subjectDN;
	
	public Thumbprint(String sha1, String sha256, String md5, String issuer, String subject) {
		this.setSha1(sha1);
		this.setSha256(sha256);
		this.setMd5(md5);
		this.setIssuerDN(issuer);
		this.setSubjectDN(subject);
	}
	
	/**
	 * @return the sha1
	 */
	public String getSha1() {
		return sha1;
	}
	/**
	 * @param sha1 the sha1 to set
	 */
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}
	/**
	 * @return the sha256
	 */
	public String getSha256() {
		return sha256;
	}
	/**
	 * @param sha256 the sha256 to set
	 */
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}
	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}
	/**
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the issueDN
	 */
	public String getIssuerDN() {
		return issuerDN;
	}

	/**
	 * @param issueDN the issueDN to set
	 */
	public void setIssuerDN(String issueDN) {
		this.issuerDN = issueDN;
	}

	/**
	 * @return the subjectDN
	 */
	public String getSubjectDN() {
		return subjectDN;
	}

	/**
	 * @param subjectDN the subjectDN to set
	 */
	public void setSubjectDN(String subjectDN) {
		this.subjectDN = subjectDN;
	}
	
}
