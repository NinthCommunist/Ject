package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class ListOfMainNewsPage extends BasePage{

    private final static ElementsCollection listOfNews = $$x("//div[@class = 'media-list']");

    public ListOfMainNewsPage checkNewsCount(){
        Assert.assertTrue(listOfNews.size()==15);
        return this;
    }

    public ArticlePage clickOnNews(Integer number){
        listOfNews.get(number).shouldBe(Condition.visible)
                .shouldBe(Condition.exist)
                .click();
        return new ArticlePage();
    }
}
