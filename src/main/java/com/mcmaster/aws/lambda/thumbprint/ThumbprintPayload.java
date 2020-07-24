package com.mcmaster.aws.lambda.thumbprint;

/**
 * This class is the Payload Object for Lambda
 * @author Stephen McMaster
 */
public final class ThumbprintPayload {

	private String url;
	private String proxyServer = "";
	private int proxyPort = 0;

	/**
	 * @return The URL to Query
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url Set the URL to Query
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the proxyPort
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * @return the proxyServer
	 */
	public String getProxyServer() {
		return proxyServer;
	}

	/**
	 * @param proxyServer the proxyServer to set
	 */
	public void setProxyServer(String proxyServer) {
		this.proxyServer = proxyServer;
	}
	
	
}
