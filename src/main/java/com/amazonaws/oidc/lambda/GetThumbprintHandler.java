package com.amazonaws.oidc.lambda;

import com.amazonaws.oidc.ThumbprintPayload;
import com.amazonaws.oidc.Thumbprints;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.utility.net.GetThumbprint;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetThumbprintHandler implements RequestHandler<ThumbprintPayload, String> {

    public String handleRequest(ThumbprintPayload input, Context context) {
        context.getLogger().log("Input: " + input);

        try {
        Thumbprints thumbprints = GetThumbprint.getTumbprints(input.getUrl());
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
