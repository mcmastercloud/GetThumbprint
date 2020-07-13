package com.mcmaster.aws.lambda.thumbprint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.utility.net.SSL;
import com.mcmaster.utility.net.objects.Thumbprints;

/**
 * This is the AWS Lambda entry point.  To use, specify <code>com.mcmaster.aws.lambda.thumbprint.GetThumbprintHandler::handleRequest</code> as the Lambda Handler.
 * @author Stephen McMaster
 */
public final class GetThumbprintHandler implements RequestHandler<ThumbprintPayload, String> {

    /**
     * Standard Lambda Handler Function.  Retrieves thumbprints from the given URL, and Serialises the results to a JSON string.
     */
    public String handleRequest(ThumbprintPayload input, Context context) {
        context.getLogger().log("Input: " + input);

        try {
        Thumbprints thumbprints = SSL.getTumbprints(input.getUrl());
        
        ObjectMapper oMapper = new ObjectMapper();
        
        return oMapper.writeValueAsString(thumbprints);
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return errorToJson("Error mapping response to JSON");
        }
    }
    
    
    /**
     * If there is an error using the JSON Mapper, we would be unable to return a JSON Object for Lambda.  Therefore, this function is used to construct JSON on the fly.  This way, message and succeeded are always returned when the Lambda function is executed.
     * @param Error message to encapsulate.
     * @return JSON String represents the Thumbprints class with succeeded = false, and message set to an error message.
     */
    private String errorToJson(String sError) {
    	return String.format("{succeeded: false, message: \"%s\"}", sError);
    }

}
