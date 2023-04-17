package com.wilkom.housingapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.wilkom.housingapi.model.HousingLocation;
import com.wilkom.housingapi.model.HousingLocationResponse;
import com.wilkom.housingapi.service.HousingLocationService;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final Gson gson = new Gson();
    private HousingLocationService housingLocationService;

    /**
     * Put all init instructions here
     * DB connection etc .
     */
    public App() {
        housingLocationService = new HousingLocationService();
    }

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(getCommonHeaders());

        try {
            final String pageContents = this.getPageContents();

            return response
                    .withStatusCode(200)
                    .withBody(pageContents);
        } catch (IOException e) {
            return response
                    .withBody("{No Data}")
                    .withStatusCode(500);
        }
    }

    public APIGatewayProxyResponseEvent handleRequestById(final APIGatewayProxyRequestEvent input,
            final Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(getCommonHeaders());
        String pathId = input.getPathParameters().get("id");

        if (StringUtils.isNotEmpty(pathId)) {
            HousingLocation housingLocation = housingLocationService.getHousingLocationById(Integer.parseInt(pathId));

            final String pageContents = gson.toJson(housingLocation, HousingLocation.class);
            return response
                    .withStatusCode(200)
                    .withBody(pageContents);

        } else {
            return response
                    .withBody("{No data}")
                    .withStatusCode(500);
        }
    }

    private String getPageContents() throws IOException {
        return gson.toJson(new HousingLocationResponse(housingLocationService.getAllHousingLocations()),
                HousingLocationResponse.class);
    }

    private Map<String, String> getCommonHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        headers.put("Access-Control-Allow-Headers", "Content-Type");
        headers.put("Access-Control-Allow-Origin", "*");// Allow from anywhere
        headers.put("Access-Control-Allow-Methods", "*");// Allow all requests
        return headers;
    }
}
