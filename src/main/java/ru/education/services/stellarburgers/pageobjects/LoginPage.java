package ru.education.services.stellarburgers.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //заголовок Вход
    private final static By loginPageHeader = By.xpath(".//h2[text()='Вход']");

    //поле ввода email
    private final static By emailInput = By.name("name");

    //поле ввода пароля
    private final static By passwordInput = By.name("Пароль");

    //кнопка Войти
    private final static By enterButton = By.xpath(".//button[text()='Войти']");

    //ссылка Зарегистрироваться
    private final static By registerLink = By.linkText("Зарегистрироваться");

    //ссылка Восстановить пароль
    private final static By passwordResetLink = By.linkText("Восстановить пароль");

    public RegisterPage goToRegisterPage() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    public boolean isLoginPageIsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
            return driver.findElement(loginPageHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public MainPage enterAccount(String email, String password){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterButton).click();
        return new MainPage(driver);
    }

    public PasswordResetPage goToResetPasswordPage() {
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(passwordResetLink));
        driver.findElement(passwordResetLink).click();
        return new PasswordResetPage(driver);

    }

}
