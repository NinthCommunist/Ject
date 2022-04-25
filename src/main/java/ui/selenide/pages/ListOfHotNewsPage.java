package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;

public class ListOfHotNewsPage extends BasePage{

    private final static ElementsCollection listOfNews = $$(".media-list");

    public ListOfHotNewsPage checkNewsCount(){
        Assert.assertEquals(listOfNews.size(), 15);
        return this;
    }

    @Step(value = "Открытие случайной новости")
    public ArticlePage openRandomNews(){
        listOfNews.get(new Random().nextInt(16))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.exist)
                .click();
        logger.debug("Open page - " + Selenide.title());
        return new ArticlePage();
    }
}
