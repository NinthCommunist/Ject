package ui.selenide;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.selenide.pages.MainPage;

public class MainTest extends BaseTest{


    @Test(description = "Открытие главной новости через список новостей")
    public void testOpenArticleFromListOfMainNews(){
        mainPage.openListOfMainNews()
                .checkNewsCount()
                .clickOnNews(0)
                .tabBarOpenMainPage();
    }
}
