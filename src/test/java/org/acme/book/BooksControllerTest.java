package org.acme.book;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BooksControllerTest {

    @TestHTTPResource("/api/v1/books")
    private URL base;

    @Inject
    BookServiceImpl bookService;

    @Test
    void getAllBook() {
        given()
                .when().get("/api/v1/books")
                .then()
                .statusCode(200);
    }

    @Test
    void getOne() {
        Book testBook = new Book(
                null
                ,"Harry potter"
                , "J.K"
                , LocalDate.now()
                , "123131231");

        bookService.CreateOne(testBook);

        given()
                .when().get(base+"/"+"1")
                .then()
                .statusCode(200)
                .body("title", Matchers.equalTo(testBook.getTitle()));
    }

    @Test
    void createOneBook() {
        Book testBook = new Book(
                null
                ,"Harry potter"
                , "J.K"
                , LocalDate.now()
                , "123131231");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(testBook)
                .when().post(base)
                .then().statusCode(201);
    }

    @Test
    void updateOneBoo() {
        Book testBook = new Book(
                null
                ,"Harry potter"
                , "J.K"
                , LocalDate.now()
                , "123131231");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(testBook)
                .when().put(base+"/"+"1")
                .then().statusCode(201);

    }

    @Test
    void deleteOneBook() {

        given()
                .when().delete(base+"/"+"1")
                .then().statusCode(409);

    }
}