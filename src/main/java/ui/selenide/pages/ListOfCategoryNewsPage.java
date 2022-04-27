package ui.selenide.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Objects;

public class ListOfCategoryNewsPage extends BasePage{
    private final String categoryName;

    public ListOfCategoryNewsPage(String categoryName) {
        this.categoryName = categoryName;
    }

    @Step(value = "Проверка соответсвия категории")
    public ListOfCategoryNewsPage checkNameOfCategory(){
        Assert.assertTrue(Objects.requireNonNull(Selenide.title().toUpperCase()).contains(categoryName));
        return this;
    }
}
