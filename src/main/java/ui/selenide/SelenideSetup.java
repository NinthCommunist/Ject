package ui.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;

import static config.Constants.*;

public class SelenideSetup {

    public static void webDriverSetUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";
        Configuration.timeout=10000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

}
