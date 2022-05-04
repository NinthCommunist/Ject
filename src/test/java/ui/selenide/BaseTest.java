package ui.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.selenide.pages.MainPage;


public class BaseTest {

    protected MainPage mainPage = new MainPage();


    @BeforeTest
    public void setUp(){
        boolean selenoid = Boolean.parseBoolean("${selenoid}");
        if (selenoid) {
            SelenideSetup.selenoidSetUp();
        } else {
            SelenideSetup.webDriverSetUp();
        }

        mainPage.openMainPage();
    }

    @AfterTest
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
