package ru.education.services.stellarburgers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import ru.education.services.stellarburgers.driver.FactoryDriver;
import ru.education.services.stellarburgers.models.UserModel;
import ru.education.services.stellarburgers.pageobjects.MainPage;

import static ru.education.services.stellarburgers.data.UserData.*;
import static ru.education.services.stellarburgers.steps.UserSteps.*;

public class BaseTest {
    protected UserModel user;
    protected String accessToken;
    protected Response createUserResponse;

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    protected WebDriver getDriver() {
        return factoryDriver.getDriver();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }

    protected void registerUserViaAPI() {
        user = new UserModel(EMAIL, PASSWORD, USER_FIRSTNAME);
        createUserResponse = createUserAPI(user);
        accessToken = getUserAccessTokenAPI(createUserResponse);
    }

    protected MainPage startUp(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();
        return mainPage;
    }

    @After
    public void cleanUp() {
        if (accessToken != null) deleteUserAPI(accessToken);
    }
}
