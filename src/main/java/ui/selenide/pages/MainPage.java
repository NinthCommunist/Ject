package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

  private final static SelenideElement mainNewsBtn = $x("//*[text()='Главная новость']");


  public ListOfMainNewsPage openListOfMainNews(){
     mainNewsBtn.shouldBe(Condition.visible)
             .shouldBe(Condition.exist)
             .click();
     return new ListOfMainNewsPage();
  }
}
