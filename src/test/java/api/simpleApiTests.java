package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.Book;
import models.Bookingdates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class simpleApiTests {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void positiveCreateBook(){
        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        Book book = Book.builder()
                .additionalneeds("BreakFast")
                .firstname("Alex")
                .lastname("Yudin")
                .totalprice(400)
                .bookingdates(bookingdates)
                .depositpaid(true)
                .build();
        given().contentType(ContentType.JSON)
                .body(book)
                .post("/booking")
                .then()
                .statusCode(200)
                .log();
    }

}
