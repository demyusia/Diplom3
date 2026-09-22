package ru.education.services.stellarburgers.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле ввода Имени
    private final static By nameInput = By.xpath(".//label[text()='Имя']/parent::div/input");

    //поле ввода email
    private final static By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");

    //поле ввода пароля
    private final static By passwordInput = By.name("Пароль");

    //ошибка при вводе пароля
    private final static By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    //кнопка Зарегистрироваться
    private final static By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //ссылка Войти
    private final static By enterLink = By.xpath(".//a[text()='Войти']");

    public LoginPage registerUser(String name, String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registerButton).click();
        return new LoginPage(driver);
    }

    public boolean isPasswordErrorIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(passwordError));
        return driver.findElement(passwordError).isDisplayed();
    }

    public LoginPage goToLoginPageFromRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(enterLink));
        driver.findElement(enterLink).click();
        return new LoginPage(driver);
    }
}
