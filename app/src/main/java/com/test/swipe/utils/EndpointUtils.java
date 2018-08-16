package com.test.swipe.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class EndpointUtils {

    private static final String ENDPOINT_URL = "https://restcountries.eu/rest/v2/";

    public static String getUrl(String route) {
        return getUrl(route, null);
    }

    public static String getUrl(String route, HashMap<String, Object> params) {
        StringBuilder urlBuilder = new StringBuilder(ENDPOINT_URL);
        urlBuilder.append(route);

        if (params != null) {
            urlBuilder.append("?");
            for (String key : params.keySet()) {
                urlBuilder.append(key);
                urlBuilder.append("=");
                urlBuilder.append(params.get(key));
                urlBuilder.append("&");
            }
        }

        try {
            return convertUrl(urlBuilder.toString());
        } catch (MalformedURLException ignore) {
            return "";
        }
    }

    private static String convertUrl(String url) throws MalformedURLException {
        return new URL(url).toString();
    }

}
