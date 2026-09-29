package ru.education.services.stellarburgers.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class FactoryDriver extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("yandex".equalsIgnoreCase(System.getProperty("browser"))||"yandexbrowser".equalsIgnoreCase(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }
    }

    private void startYandex() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/Users/User/myWorkDir/WebDriver/bin/yandexdriver"))
                .build();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    private void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
