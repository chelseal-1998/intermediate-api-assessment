package app.restfulbooker.tests;

import app.restfulbooker.utils.CustomTestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {

    protected static final Logger LOG = getLogger(lookup().lookupClass());
    private final String DATA_PROPERTIES = "data.properties";
    Properties properties = new Properties();
    CustomTestData customTestData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        setUpDataFile();
        customTestData = new CustomTestData(getProperties());
        setUpEnvironment();
    }

    public void setUpDataFile() {
        try {
            File file = new File(Paths.get(DATA_PROPERTIES).toAbsolutePath().toString());
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            LOG.error("Cannot find file" + e);
            throw new RuntimeException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setUpEnvironment() {
        RestAssured.baseURI = customTestData.getBaseUrl();
    }

    public String getAuthToken() {
        JSONObject authDetails = new JSONObject();
        authDetails.put("username", customTestData.getAdminUsername());
        authDetails.put("password", customTestData.getAdminPassword());

        return given().
                contentType(ContentType.JSON).
                when().
                body(authDetails).
                post("auth").
                then().
                extract().
                jsonPath().
                get("token");
    }

    public Header getAuthHeader() {
        return new Header("Cookie", "token=" + getAuthToken());
    }
}