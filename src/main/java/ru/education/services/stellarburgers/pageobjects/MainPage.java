package ru.education.services.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка Войти в аккаунт
    private final By accountEnterButton = By.className("button_button_type_primary__1O7Bx");

    //кнопка Личный кабинет
    private final By personalAccountLink = By.xpath(".//p[text()='Личный Кабинет']/parent::a");

    //раздел булки
    public final static String BUNS_SECTION = ".//span[text()='Булки']";

    //раздел соусы
    public final static String SAUCE_SECTION  = ".//span[text()='Соусы']";

    //раздел начинки
    public final static String MAIN_SECTION  = ".//span[text()='Начинки']";

    //выбранный раздел булки
    public final static String BUNS_SELECTED  = ".//span[text()='Булки']/parent::div[contains(@class, 'current')]";

    //выбранный раздел соусы
    public final static String SAUCE_SELECTED  = ".//span[text()='Соусы']/parent::div[contains(@class, 'current')]";

    //выбранный раздел начинки
    public final static String MAIN_SELECTED  = ".//span[text()='Начинки']/parent::div[contains(@class, 'current')]";

    @Step("Open URL")
    public void openUrl() {
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Step("Go to 'login page' by clicking on 'Account enter' button on the main page")
    public LoginPage goToAccountByAccountEnterButton() {
        driver.findElement(accountEnterButton).click();
        return new LoginPage(driver);
    }

    @Step("Go to 'login page' by clicking on 'Personal account' button on the main page")
    public LoginPage goToAccountByPersonalAccountButton() {
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }

    @Step("Check is main page is displayed")
    public boolean isMainPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BUNS_SECTION)));
            return driver.findElement(By.xpath(BUNS_SECTION)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Select ingredients section")
    public void selectIngredientSection(String sectionName) {
        driver.findElement(By.xpath(sectionName)).click();
    }

    @Step("Check is section selected")
    public boolean isSectionSelected(String selectedSection) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedSection)));
        return driver.findElement(By.xpath(selectedSection)).isDisplayed();
    }
}
