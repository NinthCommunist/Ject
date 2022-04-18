package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.testng.Assert;


import static com.codeborne.selenide.Selenide.*;
import static config.Constants.UI_URL;

public class MainPage extends BasePage {

  private final static SelenideElement mainNewsBtn = $x("//*[text()='Главная новость']");
  private final static SelenideElement hotNewsBtn = $x("//*[text()='Горячие новости']");



  public MainPage openMainPage(){
      open(UI_URL);
      return this;
  }

  public ListOfMainNewsPage openListOfMainNews(){
     mainNewsBtn.shouldBe(Condition.visible)
             .shouldBe(Condition.exist)
             .click();
     return new ListOfMainNewsPage();
  }

  public MainPage checkMainPageIsOpen(){
      Assert.assertEquals(title(), "Алга (Вперед)");
      return this;
  }

  public ListOfHotNewsPage openListOfHotNews(){
      hotNewsBtn.shouldBe(Condition.visible)
              .shouldBe(Condition.exist)
              .click();
      return new ListOfHotNewsPage();
  }
}