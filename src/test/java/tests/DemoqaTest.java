package tests;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DemoqaTest extends TestBase {

    @DisplayName("тесты для Book Store")
    @Tag("demoQaTest")
    @Test
    @WithLogin
    void addedDeletedItemTest() {
        Steps step = new Steps();
        step.deleteAllBook();
        step.addBook();
        step.checkNameAddedBook();
        step.deleteAllBook();
        step.checkProfileIsEmpty();
    }

}
