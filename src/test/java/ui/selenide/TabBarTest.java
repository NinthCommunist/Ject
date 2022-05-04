package ui.selenide;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TabBarTest extends BaseTest {

    @Test(description = "Проверка переходам по страницам таб бара")
    public void choicesTabBars(){
        mainPage.tabBarOpenTvPage()
                .tabBarOpenPhotosPage()
                .tabBarOpenAdvPage()
                .tabBarOpenVotingPage()
                .tabBarOpenMainPage()
                .checkMainPageIsOpen();
    }

    @Test(description = "Специально упавший тест для проверки показания скриншота")
    public void failedTest(){
        mainPage.failedCheckMainPageIsOpen();
    }
}
