package app.restfulbooker.tests.restfulbooker.booking;

import app.restfulbooker.tests.restfulbooker.BookingBase;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static app.restfulbooker.common.TestData.*;

@Test
public class GetBookingTests extends BookingBase {

    int bookingId;

    @BeforeMethod(alwaysRun = true)
    public void createBooking() {
        LocalDate localDate = LocalDate.now();
        bookingId = createNewBooking(localDate.toString(), localDate.plusDays(7).toString(), FIRST_NAME, LAST_NAME,
                TOTAL_PRICE, ADDITIONAL_NEEDS).
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("bookingid");
    }

    @Description("As an admin user, I should be able to view a booking.")
    public void viewBooking() {
        getBooking(bookingId).
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                spec(getBookingExpectedResponse());
    }

    @Description("As an admin user, I shouldn't be able to view a booking that doesn't exist.")
    public void viewBookingThatDoesNotExist() {
        getBooking(INVALID_BOOKING_ID).
                then().
                assertThat().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}