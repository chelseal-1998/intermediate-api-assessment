package app.restfulbooker.tests.restfulbooker.booking;

import app.restfulbooker.tests.restfulbooker.BookingBase;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static app.restfulbooker.common.TestData.*;

public class CreateBookingTest extends BookingBase {

    @Test
    @Description("As an admin user, I should be able to create a booking.")
    public void createBooking() {
        LocalDate localDate = LocalDate.now();
        createNewBooking(localDate.toString(), localDate.plusDays(7).toString(), FIRST_NAME, LAST_NAME,
                TOTAL_PRICE, ADDITIONAL_NEEDS).
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                spec(getCreatedBookingExpectedResponse());
    }
}