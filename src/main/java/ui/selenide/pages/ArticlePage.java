package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ArticlePage extends BasePage {

    private static final SelenideElement rubricName = $(".rubric-marker");
    private static final String rubricNameStr = rubricName.getText();
    public ListOfCategoryNewsPage openListOfRubric(){

        rubricName.shouldBe(Condition.visible)
                .shouldBe(Condition.exist)
                .click();
        return new ListOfCategoryNewsPage(rubricNameStr);
    }

}
