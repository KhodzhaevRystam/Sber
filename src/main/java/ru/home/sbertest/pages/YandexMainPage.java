package ru.home.sbertest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.sbtqa.tag.pagefactory.annotations.RedirectsTo;

@PageEntry(title = "Яндекс Маркет")
public class YandexMainPage extends Page{

    @ElementTitle("Маркет")
    @FindBy(xpath = ".//div/a[text() = 'Маркет']")
    @RedirectsTo(page = YandexMarketPage.class)
    public WebElement market;

    public YandexMainPage() {
        PageFactory.initElements(PageFactory.getDriver(), this);
        int a = 2;
    }

}
