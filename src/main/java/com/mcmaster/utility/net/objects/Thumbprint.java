package com.mcmaster.utility.net.objects;

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
	
	
	/**
	 * @param sha1 SHA1 fingerprint
	 * @param sha256 SHA256 fingerprint
	 * @param md5 MD5 fingerprint
	 * @param issuer Issuer for the certificate
	 * @param subject Subject for the certificate
	 */
	public Thumbprint(String sha1, String sha256, String md5, String issuer, String subject) {
		this.setSha1(sha1);
		this.setSha256(sha256);
		this.setMd5(md5);
		this.setIssuerDN(issuer);
		this.setSubjectDN(subject);
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
	
}
