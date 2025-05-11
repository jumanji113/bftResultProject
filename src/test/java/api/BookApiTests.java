package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import listeners.CustomTpl;
import models.AuthAdmin;
import models.Book;
import models.Bookingdates;
import org.junit.jupiter.api.*;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Tag("API")
public class BookApiTests {

    private static Random random;

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withCustomTemplates());
        random = new Random();
    }

    @Test
    @Tag("positiveApi")
    @DisplayName("проверка создания книги и получения статуса 200")
    public void positiveCreateBookCheckStatus() {

        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");

        Book book = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
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

        Assertions.assertNotNull(bookId);
    }

    @Test
    @Tag("NegativeApi")
    @DisplayName("проверка негативного сценария создания книги и получения статуса 500")
    public void negativeCreateBookCheckStatus() {

        Book book = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
                .totalprice(400)
                .build();

        given().contentType(ContentType.JSON)
                .body(book)
                .post("/booking")
                .then()
                .body(equalTo("Internal Server Error"))
                .statusCode(500);
    }

    @Test
    @Tag("NegativeApi")
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
    @Tag("positiveApi")
    @DisplayName("Позитивный тест на получение книги")
    public void positiveGetBook() {

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

        Book bookActual = given().get("/booking/" + bookId)
                .then()
                .statusCode(200)
                .extract()
                .as(Book.class);

        Assertions.assertEquals(book.getAdditionalneeds(), bookActual.getAdditionalneeds());
        Assertions.assertEquals(book.getTotalprice(), bookActual.getTotalprice());
    }

    @Test
    @Tag("positiveApi")
    @DisplayName("Позитивный тест на удаление книги")
    public void positiveDeleteBook() {

        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        AuthAdmin authAdmin =
                new AuthAdmin("admin", "password123");

        Book book = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
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

        String token = given()
                .contentType(ContentType.JSON)
                .body(authAdmin)
                .post("/auth").jsonPath().getString("token");

        given()
                .header("cookie", "token=" + token)
                .delete("/booking/" + bookId)
                .then()
                .statusCode(201)
                .body(equalTo("Created"));
    }

    @Test
    @Tag("NegativeApi")
    @DisplayName("Негативный тест на удаление книги")
    public void negativeDeleteBook() {

        given()
                .auth()
                .oauth2("какой то токен")
                .delete("/booking/" + random.nextInt(1000))
                .then()
                .statusCode(403)
                .body(equalTo("Forbidden"));
    }

    @Test
    @Tag("positiveApi")
    @DisplayName("Позитивный тест на изменения данных книги")
    public void positiveUpdateBook() {

        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        AuthAdmin authAdmin =
                new AuthAdmin("admin", "password123");

        Book book = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
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

        String token = given()
                .contentType(ContentType.JSON)
                .body(authAdmin)
                .post("/auth").jsonPath().getString("token");

        Book updatedBook = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
                .totalprice(400)
                .bookingdates(bookingdates)
                .depositpaid(true)
                .build();

        Book actualResultUpdatedBook = given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(updatedBook)
                .put("/booking/" + bookId)
                .then()
                .extract()
                .as(Book.class);

        Assertions.assertEquals(updatedBook, actualResultUpdatedBook);
    }

    @Test
    @Tag("NegativeApi")
    @DisplayName("Негативный тест на удаление книги")
    public void negativeUpdateBook(){
        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        AuthAdmin authAdmin =
                new AuthAdmin("admin", "password123");

        Book book = Book.builder()
                .additionalneeds("BreakFast" + random.nextInt(1000))
                .firstname("Alex" + random.nextInt(1000))
                .lastname("Yudin" + random.nextInt(1000))
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

        String token = given()
                .contentType(ContentType.JSON)
                .body(authAdmin)
                .post("/auth").jsonPath().getString("token");

        given()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .put("/booking/" + bookId)
                .then()
                .body(equalTo("Bad Request"));
    }
}
