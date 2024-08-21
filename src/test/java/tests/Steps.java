package tests;

import authorization.Authorization;

import data.DataTest;
import io.qameta.allure.Step;

import models.AddBookRequestBodyModel;

import static io.restassured.RestAssured.given;
import static specs.DemoqaBookShopSpecs.*;


public class Steps {

    DataTest data = new DataTest();
    String token = Authorization.extactValueFromCookieString("token");
    AddBookRequestBodyModel bookData = new AddBookRequestBodyModel();
    String userID = Authorization.extactValueFromCookieString("userID");

    @Step("Добавление книги")
    public void addBookAPI(String isbn) {
        bookData.userId = userID;

        given(requestSpecification)
                .header("authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(responseSpec201);
    }


    @Step("чистка корзины")
    public void deleteAllBookAPI() {
        bookData.userId = userID;
        bookData.setIsbn(data.isbn);
        given(requestSpecification)
                .header("authorization", "Bearer " + token)
                .queryParams("UserId", userID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);
    }




}
