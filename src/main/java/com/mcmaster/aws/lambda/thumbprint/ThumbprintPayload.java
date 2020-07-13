package com.mcmaster.aws.lambda.thumbprint;

/**
 * This class is the Payload Object for Lambda
 * @author Stephen McMaster
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
