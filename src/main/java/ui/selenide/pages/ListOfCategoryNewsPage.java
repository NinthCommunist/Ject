package ui.selenide.pages;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;

import java.util.Objects;

public class ListOfCategoryNewsPage extends BasePage{
    private final String categoryName;

    public ListOfCategoryNewsPage(String categoryName) {
        this.categoryName = categoryName;
    }

    public ListOfCategoryNewsPage checkNameOfCategory(){
        logger.debug(categoryName);
        Assert.assertTrue(Objects.requireNonNull(Selenide.title().toUpperCase()).contains(categoryName));
        return this;
    }
}
