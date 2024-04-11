package app.restfulbooker.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
public class CustomTestData {

    public final String baseUrl;
    private final String adminUsername;
    private final String adminPassword;

    public CustomTestData(Properties properties) {
        baseUrl = properties.getProperty("base.url");
        adminUsername = properties.getProperty("admin.username");
        adminPassword = properties.getProperty("admin.password");
    }
}