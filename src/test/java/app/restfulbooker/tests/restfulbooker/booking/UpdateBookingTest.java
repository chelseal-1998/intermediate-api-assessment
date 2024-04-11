package app.restfulbooker.tests.restfulbooker.booking;

import app.restfulbooker.tests.restfulbooker.BookingBase;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static app.restfulbooker.common.TestData.*;
import static org.hamcrest.CoreMatchers.is;

public class UpdateBookingTest extends BookingBase {

    int bookingId;
    LocalDate localDate = LocalDate.now();

    @BeforeMethod(alwaysRun = true)
    public void createBooking() {
        bookingId = createNewBooking(localDate.toString(), localDate.plusDays(7).toString(), FIRST_NAME,
                LAST_NAME, TOTAL_PRICE, ADDITIONAL_NEEDS).
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("bookingid");
    }

    @Test
    @Description("As an admin user, I should be able to update a booking.")
    public void updateABooking() {
        updateBooking(localDate.plusDays(5).toString(), localDate.plusDays(20).toString(), bookingId,
                UPDATED_FIRST_NAME, UPDATED_LAST_NAME, UPDATED_TOTAL_PRICE, UPDATED_ADDITIONAL_NEEDS).
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                body("firstname", is(UPDATED_FIRST_NAME)).
                body("lastname", is(UPDATED_LAST_NAME)).
                body("additionalneeds", is(UPDATED_ADDITIONAL_NEEDS)).
                body("totalprice", is(UPDATED_TOTAL_PRICE));
    }
}