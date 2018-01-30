package ru.home.sbertest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

@PageEntry(title = "Яндекс Маркет")
public class YandexMarketPage extends Page {

    @ElementTitle("Компьютеры")
    @FindBy(xpath = ".//li/a[text() = 'Компьютеры']")
    private WebElement computers;

    @ElementTitle("Ноутбуки")
    @FindBy(xpath = ".//div[contains(@class, 'catalog')]/a[text() = 'Ноутбуки']")
    private WebElement notebooks;


    @ElementTitle("Планшеты")
    @FindBy(xpath = ".//div[contains(@class, 'catalog')]/a[text() = 'Планшеты']")
    private WebElement planetable;


    /*===================================ПОИСК===============================================*/
    @ElementTitle("Применить")
    @FindBy(xpath = ".//button[./span[text() = 'Применить']]")
    private WebElement accpet;

    @ElementTitle("Поиск")
    @FindBy(xpath = ".//*[@id=\"header-search\"]")
    private WebElement search;


    public YandexMarketPage() {
        PageFactory.initElements(PageFactory.getDriver(), this);
    }
}
