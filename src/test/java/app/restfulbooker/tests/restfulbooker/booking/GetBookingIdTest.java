package app.restfulbooker.tests.restfulbooker.booking;

import app.restfulbooker.tests.restfulbooker.BookingBase;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class GetBookingIdTest extends BookingBase {

    @Test
    @Description("As an admin user, I should be able to view all booking ids.")
    public void viewBookingIds() {
        getBookingIds().
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                body("bookingid", everyItem(notNullValue()));
    }
}