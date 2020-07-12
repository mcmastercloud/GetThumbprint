package com.mcmaster.aws.lambda.thumbprint;

/**
 * @author Stephen McMaster
 * This class is the Payload Object for Lambda
 */
public final class ThumbprintPayload {

	private String url;

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
	
	
}
