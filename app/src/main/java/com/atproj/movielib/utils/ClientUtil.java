package com.atproj.movielib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class ClientUtil {

    private static String clientUrl;
    private static String apiKey;

    public static String getClientUrl() {
        return clientUrl != null ? clientUrl : "";
    }

    public static void setClientUrl(String buildConfig, String clientUrlsMap) {
        Map<String, String> configMap = getConfigMap(clientUrlsMap);
        ClientUtil.clientUrl = configMap.get(buildConfig);
    }

    public static String getApiKey() {
        return apiKey != null ? apiKey : "";
    }

    public static void setApiKey(String buildConfig, String apiKeysMap) {
        Map<String, String> configMap = getConfigMap(apiKeysMap);
        ClientUtil.apiKey = configMap.get(buildConfig);
    }

    private static Map<String, String> getConfigMap(String configString) {
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        return new Gson().fromJson(configString, mapType);
    }

}
