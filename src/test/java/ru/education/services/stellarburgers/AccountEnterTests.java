package ru.education.services.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.education.services.stellarburgers.pageobjects.LoginPage;
import ru.education.services.stellarburgers.pageobjects.MainPage;
import ru.education.services.stellarburgers.pageobjects.PasswordResetPage;
import ru.education.services.stellarburgers.pageobjects.RegisterPage;

import static org.junit.Assert.assertTrue;

public class AccountEnterTests extends BaseTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
        registerUserViaAPI();
    }

    @Test
    @DisplayName("Check account enter via button 'Account enter'")
    public void checkAccountEnterViaEnterAccountButton() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByAccountEnterButton();
        mainPage= loginPage.enterAccount(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Check account enter via button 'Personal account'")
    public void checkAccountEnterViaPersonalAccount() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByPersonalAccountButton();
        mainPage= loginPage.enterAccount(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Check account enter via button 'Enter' in Registration form")
    public void checkAccountEnterViaRegistrationForm() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByAccountEnterButton();
        RegisterPage registerPage = loginPage.goToRegisterPage();
        loginPage = registerPage.goToLoginPageFromRegisterPage();
        mainPage= loginPage.enterAccount(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Check account enter via button 'Enter' in Reset password form")
    public void checkAccountEnterViaResetPasswordForm() {
        WebDriver driver = getDriver();
        MainPage mainPage = startUp(driver);

        LoginPage loginPage = mainPage.goToAccountByAccountEnterButton();
        PasswordResetPage passwordResetPage = loginPage.goToResetPasswordPage();
        loginPage = passwordResetPage.goToLoginPageFromPasswordResetPage();
        mainPage= loginPage.enterAccount(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageDisplayed());
    }
}
