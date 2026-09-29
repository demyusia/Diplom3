package ru.education.services.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.education.services.stellarburgers.models.UserModel;
import ru.education.services.stellarburgers.pageobjects.LoginPage;
import ru.education.services.stellarburgers.pageobjects.MainPage;
import ru.education.services.stellarburgers.pageobjects.RegisterPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.education.services.stellarburgers.data.UserData.*;
import static ru.education.services.stellarburgers.steps.UserSteps.getUserAccessTokenAPI;
import static ru.education.services.stellarburgers.steps.UserSteps.loginUserAPI;

public class RegistrationTests extends BaseTest{

    @Test
    @DisplayName("Check registration with correct data")
    public void checkRegistrationWithCorrectData() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByAccountEnterButton();
        RegisterPage registerPage = loginPage.goToRegisterPage();

        user = new UserModel(EMAIL, PASSWORD, USER_FIRSTNAME);
        loginPage = registerPage.registerUser(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(loginPage.isLoginPageIsDisplayed());

        Response loginUserResponse = loginUserAPI(user);
        accessToken = getUserAccessTokenAPI(loginUserResponse);
    }

    @Test
    @DisplayName("Check registration with incorrect password")
    public void checkRegistrationWithIncorrectPassword() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByAccountEnterButton();
        RegisterPage registerPage = loginPage.goToRegisterPage();

        user = new UserModel(EMAIL, "12345", USER_FIRSTNAME);
        loginPage = registerPage.registerUser(user.getName(), user.getEmail(), user.getPassword());

        assertFalse(loginPage.isLoginPageIsDisplayed());
        assertTrue(registerPage.isPasswordErrorIsDisplayed());
    }
}
