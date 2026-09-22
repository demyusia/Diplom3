package ru.education.services.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.education.services.stellarburgers.pageobjects.MainPage;

import static org.junit.Assert.assertTrue;
import static ru.education.services.stellarburgers.pageobjects.MainPage.*;

@RunWith(Parameterized.class)
public class IngredientSectionNavigationTests extends BaseTest{

    @Parameterized.Parameter()
    public String sectionName;
    @Parameterized.Parameter(1)
    public String selectedSection;

    @Parameterized.Parameters(name="секция {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {SAUCE_SECTION, SAUCE_SELECTED},
                {MAIN_SECTION, MAIN_SELECTED},
                {BUNS_SECTION, BUNS_SELECTED},
        };
    }

    @Test
    @DisplayName("Check ingredients section navigation")
    public void checkIngredientSectionNavigation() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        try {
            mainPage.selectIngredientSection(sectionName);
        } catch (Exception e) {
            mainPage.selectIngredientSection(SAUCE_SECTION);
            mainPage.selectIngredientSection(sectionName);
        }
        assertTrue(mainPage.isSectionSelected(selectedSection));
    }
}
