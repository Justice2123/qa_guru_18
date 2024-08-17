package tests;

import authorization.Authorization;
import com.codeborne.selenide.SelenideElement;
import data.DataTest;
import io.qameta.allure.Step;

import models.AddBookRequestBodyModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static specs.DemoqaBookShopSpecs.*;


public class Steps {

    DataTest data = new DataTest();
    SelenideElement emptyList = $(".rt-noData"),
            nameBookInProfile = $(".mr-2");
    String token = Authorization.extactValueFromCookieString("token");
    AddBookRequestBodyModel bookData = new AddBookRequestBodyModel();
    String userID = Authorization.extactValueFromCookieString("userID");

    @Step("Добавление книги")
    public void addBook() {
        bookData.userId = userID;
        bookData.setIsbn(data.isbn);

        given(requestSpecification)
                .header("authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(responseSpec201);
    }

    @Step("Проверка названия книги")
    public Steps checkNameAddedBook() {
        open("/profile");
        nameBookInProfile.shouldHave(text("Eloquent JavaScript, Second Edition"));
        return this;
    }


    @Step("Удаление книг из профайла")
    public void deleteAllBook() {
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


    @Step("проверка на удаление книг")
    public Steps checkProfileIsEmpty() {
        open("/profile");
        emptyList.shouldHave(text("No rows found"));
        return this;
    }

}
