package org.nobel.highriseapi;

public class HighriseClientConfig {

    private String baseUrl;
    private String token;
    private boolean useCache;

    public HighriseClientConfig(String baseUrl, String token, boolean useCache) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.useCache = useCache;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

}
