package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.testng.Assert;


import static com.codeborne.selenide.Selenide.*;
import static config.Constants.URL;

public class MainPage extends BasePage {

  private final static SelenideElement mainNewsBtn = $x("//*[text()='Главная новость']");
  private final static SelenideElement hotNewsBtn = $x("//*[text()='Горячие новости']");


  @Step(value = "Открытие главной страницы")
  public MainPage openMainPage(){
      open(URL);
      return this;
  }

  @Step(value = "Открытия списка главных новостей")
  public ListOfMainNewsPage openListOfMainNews(){
     mainNewsBtn.shouldBe(Condition.visible)
             .shouldBe(Condition.exist)
             .click();
     return new ListOfMainNewsPage();
  }

    @Step(value = "Проверка открытия главной страницы")
  public MainPage checkMainPageIsOpen(){
      Assert.assertEquals(title(), "Алга (Вперед)");
      return this;
  }

    @Step(value = "Открытие списка горячих новостей")
  public ListOfHotNewsPage openListOfHotNews(){
      hotNewsBtn.shouldBe(Condition.visible)
              .shouldBe(Condition.exist)
              .click();
      return new ListOfHotNewsPage();
  }
}