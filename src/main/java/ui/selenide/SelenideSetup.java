package ui.selenide;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

import static config.Constants.*;

public class SelenideSetup {

    public static void webDriverSetUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";
        Configuration.headless=false;
        Configuration.timeout=UI_TIMEOUT;
    }

}
