package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final static SelenideElement mainBtn = $("#tabBarItem0");
    private final static SelenideElement tvBtn = $("#tabBarItem1");
    private final static SelenideElement photosBtn = $("#tabBarItem2");
    private final static SelenideElement advBtn = $("#tabBarItem3");
    private final static SelenideElement votingBtn = $("#tabBarItem4");

    public BasePage tabBarOpenMainPage(){
        mainBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public BasePage tabBarOpenTvPage(){
        tvBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public BasePage tabBarOpenPhotosPage(){
        photosBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public BasePage tabBarOpenAdvPage(){
        advBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public BasePage tabBarOpenVotingPage(){
        votingBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }
}
