package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import listeners.CustomTpl;
import models.Book;
import models.Bookingdates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class bookApiTests {
    private static Random random;
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withCustomTemplates());
        random = new Random();
    }

    @Test
    @DisplayName("проверка создания книги и получения статуса 200")
    public void positiveCreateBookCheckStatus() {
        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        Book book = Book.builder()
                .additionalneeds("BreakFast33")
                .firstname("Alex32")
                .lastname("Yudin")
                .totalprice(400)
                .bookingdates(bookingdates)
                .depositpaid(true)
                .build();
        int bookId = given().contentType(ContentType.JSON)
                .body(book)
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("bookingid");
        System.out.println(bookId);
    }

    @Test
    @DisplayName("Негативный тест на получение книги")
    public void negativeGetBook() {
        int randomNumber = Math.abs(random.nextInt(10000));

        given()
                .get("/booking/" + randomNumber)
                .then()
                .statusCode(404)
                .body(equalTo("Not Found"));
    }

    @Test
    @DisplayName("Позитивный тест на получение книги")
    public void positiveGetBook(){
        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        Book book = Book.builder()
                .additionalneeds("BreakFast33")
                .firstname("Alex32")
                .lastname("Yudin")
                .totalprice(400)
                .bookingdates(bookingdates)
                .depositpaid(true)
                .build();
        int bookId = given().contentType(ContentType.JSON)
                .body(book)
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("bookingid");

        given().get("/booking/" + bookId)
                .then()
                .statusCode(200);
    }

}
