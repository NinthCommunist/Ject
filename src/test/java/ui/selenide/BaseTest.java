package ui.selenide;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.selenide.pages.MainPage;

import static config.Constants.*;

public class BaseTest {

    protected MainPage mainPage = new MainPage();


    @BeforeTest
    public void setUp(){
        SelenideSetup.webDriverSetUp();
        Selenide.open(UI_URL);
    }

    @AfterTest
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
