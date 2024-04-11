package app.restfulbooker.tests.restfulbooker.booking;

import app.restfulbooker.tests.restfulbooker.BookingBase;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static app.restfulbooker.common.TestData.*;

public class DeleteBookingTest extends BookingBase {

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
    @Description("As an admin user, I should be able to delete a booking.")
    public void deleteABooking() {
        deleteBooking(bookingId).
                then().
                assertThat().
                statusCode(HttpStatus.SC_CREATED);

        getBooking(bookingId).
                then().
                assertThat().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}