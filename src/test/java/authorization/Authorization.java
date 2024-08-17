package authorization;

import com.codeborne.selenide.WebDriverRunner;
import data.DataTest;
import io.qameta.allure.Step;
import models.AuthorizationBodyModel;
import models.AuthorizationResponseModel;

import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoqaBookShopSpecs.*;


public class Authorization extends TestBase {


    @Step("данные для авторизации")
    public static AuthorizationResponseModel getResponse() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        DataTest acc = new DataTest();
        authData.setUserName(acc.login);
        authData.setPassword(acc.password);


        return given(requestSpecification)
                .body(authData)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(responseSpec200)
                .extract().as(AuthorizationResponseModel.class);

    }

    @Step("авторизация")
    public static void setCookies(AuthorizationResponseModel response) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        open("");
    }

    public static String extactValueFromCookieString(String value) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(value));
        return cookieValue.substring(cookieValue.indexOf("=") + 1, cookieValue.indexOf(";"));
    }


}