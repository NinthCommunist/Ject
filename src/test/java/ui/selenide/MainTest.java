package ui.selenide;


import org.testng.annotations.Test;

public class MainTest extends BaseTest{


    @Test(testName = "Открытие главной новости через список новостей")
    public void testOpenArticleFromListOfMainNews(){
        mainPage.openListOfMainNews()
                .checkNewsCount()
                .clickOnNews(0)
                .tabBarOpenMainPage()
                .checkMainPageIsOpen();
    }

    @Test(testName = "Открытие списка новостей категории")
    public void testOpenListOfCategoryNewsFromHotNews(){
        mainPage.openListOfHotNews()
                .checkNewsCount()
                .openRandomNews()
                .openListOfRubric()
                .checkNameOfCategory();
    }
}
