package ru.education.services.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordResetPage {

    private WebDriver driver;

    public PasswordResetPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле ввода email
    private final static By emailInput = By.name("name");

    //кнопка Восстановить
    private final static By resetButton = By.xpath(".//button[text()='Восстановить']");

    //сслыка Войти
    private final static By enterLink = By.linkText("Войти");

    @Step("Go to 'login page' by clicking on 'Enter' link on 'password reset page'")
    public LoginPage goToLoginPageFromPasswordResetPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(enterLink));
        driver.findElement(enterLink).click();
        return new LoginPage(driver);
    }
}
