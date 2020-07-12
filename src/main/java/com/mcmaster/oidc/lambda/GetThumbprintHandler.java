package com.mcmaster.oidc.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmaster.aws.oidc.ThumbprintPayload;
import com.mcmaster.aws.oidc.Thumbprints;
import com.mcmaster.utility.net.SSL;

public final class GetThumbprintHandler implements RequestHandler<ThumbprintPayload, String> {

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
    
    private String errorToJson(String sError) {
    	return String.format("{succeeded: false, message: \"%s\"}", sError);
    }

}
