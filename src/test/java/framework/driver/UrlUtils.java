package framework.driver;

import framework.utils.PropertiesUtils;

public final class UrlUtils {
    private static final String DEFAULT_URL = "https://cloud.google.com/";
    private static String baseUrl;

    static {
        initUrl();
    }

    private UrlUtils() {
        initUrl();
    }

    private static void initUrl() {
        baseUrl = PropertiesUtils.get().readBaseUrl();
        if (baseUrl.equals(PropertiesUtils.ERROR_READ_PROPERTY)) {
            baseUrl = DEFAULT_URL;
        }
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}