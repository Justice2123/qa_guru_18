package tests;

import data.DataTest;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

public class DemoqaTest extends TestBase {

    @DisplayName("тесты для Book Store")
    @Tag("demoQaTest")
    @Test
    @WithLogin
    void addedDeletedItemTest() {
        Steps step = new Steps();
        ProfilePage page = new ProfilePage();
        DataTest data = new DataTest();


        step.deleteAllBookAPI();
        step.addBookAPI(data.isbn);
        page.openPageUI();
        page.checkUserNameUI();
        page.checkAddedBookUI();
        page.deleteBookUI();
        page.checkProfileIsEmptyUI();

    }

}
