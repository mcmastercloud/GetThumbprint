package com.mcmaster.aws.lambda.thumbprint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;

/**
 * This is the AWS Lambda entry point.  To use, specify <code>com.mcmaster.aws.lambda.thumbprint.GetThumbprintHandler::handleRequest</code> as the Lambda Handler.
 * @author Stephen McMaster
 */
public final class GetThumbprintHandler implements RequestHandler<ThumbprintPayload, Thumbprints> {

    /**
     * Standard Lambda Handler Function.  Retrieves thumbprints from the given URL, and Serialises the results to a JSON string.
     */
    public Thumbprints handleRequest(ThumbprintPayload input, Context context) {
        context.getLogger().log("Input: " + input.getUrl());
        context.getLogger().log("Input: " + input.getProxyServer());
        context.getLogger().log("Input: " + input.getProxyPort());

        try {
        	return SSL.getTumbprints(input.getUrl(), input.getProxyServer(), input.getProxyPort());
        } catch (Exception ex) {
        	return new Thumbprints(ex.getMessage());
        }
    }

}
