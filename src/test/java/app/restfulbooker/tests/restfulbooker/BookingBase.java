package app.restfulbooker.tests.restfulbooker;

import app.restfulbooker.tests.BaseTest;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BookingBase extends BaseTest {

    public Response createNewBooking(String checkIn, String checkout, String firstName, String lastName, int totalPrice,
                                     String additionalNeeds) {
        return given().
                contentType(ContentType.JSON).
                when().
                body(manageBookingDetails(checkIn, checkout, firstName, lastName, totalPrice, additionalNeeds)).
                post("booking").
                then().
                extract().
                response();
    }

    public JSONObject manageBookingDetails(String checkIn, String checkout, String firstName, String lastName,
                                           int totalPrice, String additionalNeeds) {
        JSONObject bookingDetails = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDetails.put("firstname", firstName);
        bookingDetails.put("lastname", lastName);
        bookingDetails.put("totalprice", totalPrice);
        bookingDetails.put("depositpaid", true);
        bookingDetails.put("bookingdates", bookingDates);
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkout);
        bookingDetails.put("additionalneeds", additionalNeeds);
        return bookingDetails;
    }

    public Response getBooking(int bookingId) {
        return given().
                contentType(ContentType.JSON).
                when().
                get("booking/" + bookingId).
                then().
                log().all().
                extract().
                response();
    }

    public Response getBookingIds() {
        return given().
                contentType(ContentType.JSON).
                when().
                get("booking").
                then().
                log().all().
                extract().
                response();
    }

    public ResponseSpecification getBookingExpectedResponse() {
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(HttpStatus.SC_OK).
                expectBody("firstname", notNullValue()).
                expectBody("lastname", notNullValue()).
                expectBody("totalprice", notNullValue()).
                expectBody("depositpaid", notNullValue()).
                expectBody("additionalneeds", notNullValue()).
                rootPath("bookingdates").
                expectBody("checkin", notNullValue()).
                expectBody("checkout", notNullValue());
        return resBuilder.build();
    }

    public ResponseSpecification getCreatedBookingExpectedResponse() {
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(HttpStatus.SC_OK).
                expectBody("bookingid", notNullValue()).
                rootPath("booking").
                expectBody("firstname", notNullValue()).
                expectBody("lastname", notNullValue()).
                expectBody("totalprice", notNullValue()).
                expectBody("depositpaid", notNullValue()).
                expectBody("additionalneeds", notNullValue()).
                appendRootPath("bookingdates").
                expectBody("checkin", notNullValue()).
                expectBody("checkout", notNullValue());
        return resBuilder.build();
    }

    public Response updateBooking(String checkIn, String checkout, int bookingId, String updatedFirstName,
                                  String updatedLastName, int updatedTotalPrice, String updatedAdditionalNeeds) {
        return given().
                contentType(ContentType.JSON).
                header(getAuthHeader()).
                when().
                body(manageBookingDetails(checkIn, checkout, updatedFirstName, updatedLastName, updatedTotalPrice,
                        updatedAdditionalNeeds)).
                put("booking/" + bookingId).
                then().
                log().all().
                extract().
                response();
    }

    public Response deleteBooking(int bookingId) {
        return given().
                contentType(ContentType.JSON).
                header(getAuthHeader()).
                when().
                delete("booking/" + bookingId);
    }
}