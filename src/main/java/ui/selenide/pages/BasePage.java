package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final static SelenideElement mainBtn = $("#tabBarItem0");
    private final static SelenideElement tvBtn = $("#tabBarItem1");
    private final static SelenideElement photosBtn = $("#tabBarItem2");
    private final static SelenideElement advBtn = $("#tabBarItem3");
    private final static SelenideElement votingBtn = $("#tabBarItem4");

    @Step(value = "Переход на главный экран")
    public MainPage tabBarOpenMainPage() {
        mainBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return new MainPage();
    }

    @Step(value = "Переход на страницу ТВ")
    public BasePage tabBarOpenTvPage() {
        tvBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step(value = "Переход на страницу фото")
    public BasePage tabBarOpenPhotosPage() {
        photosBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step(value = "Переход на страницу рекламы")
    public BasePage tabBarOpenAdvPage() {
        advBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step(value = "Переход на страницу голосования")
    public BasePage tabBarOpenVotingPage() {
        votingBtn.shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }
}