package app.restfulbooker.common;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    public static final String FIRST_NAME = "User" + RandomStringUtils.randomAlphanumeric(10);
    public static final String LAST_NAME = "Sur" + RandomStringUtils.randomAlphabetic(10);
    public static final String ADDITIONAL_NEEDS = "Breakfast";
    public static final String UPDATED_FIRST_NAME = "User" + RandomStringUtils.randomAlphanumeric(10);
    public static final String UPDATED_LAST_NAME = "Sur" + RandomStringUtils.randomAlphabetic(10);
    public static final String UPDATED_ADDITIONAL_NEEDS = "Bread";
    public static final int UPDATED_TOTAL_PRICE = 12;
    public static final int TOTAL_PRICE = 5;
    public static final int INVALID_BOOKING_ID = 0;
}