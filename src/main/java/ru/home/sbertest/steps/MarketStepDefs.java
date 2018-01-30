package ru.home.sbertest.steps;

import cucumber.api.DataTable;
import cucumber.api.java.ru.Когда;
import org.junit.Assert;
import org.openqa.selenium.*;
import ru.home.sbertest.pages.YandexMarketPage;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.qautils.errors.AutotestError;
import ru.yandex.qatools.allure.Allure;

import java.util.List;

public class MarketStepDefs {

    //FIXME можно реализовать кончено и чрезе PageObject но элементов на странице слишком много.
    @Когда("^выполнить действия на странице маркета$")
    public void action(DataTable dataTable) throws Exception {
        if (PageFactory.getInstance().getCurrentPage().getClass() != YandexMarketPage.class) {
            throw new Exception("Пользователь находится не на той странице!");
        }
        List<List<String>> lists = dataTable.asLists(String.class);
        String area = "";
        String action = "";
        String field;
        String value;
        for (List<String> list : lists) {
            String _0 = list.get(0);
            String _1 = list.get(1);
            value = list.get(2);
            if (_0.equalsIgnoreCase("area")) {
                area = _1;
                continue;
            } else {
                action = _0;
                field = _1;
            }
            WebElement e = PageFactory.getWebDriver().
                    findElement(By.xpath(".//*[text() = '" + area + "' or contains(text(), '"+area+"')]" +
                            "/ancestor::div[@data-bem][1]//*[text() = '" + field + "'  or @sign-title = '" + field + "']"));
            if (action.equalsIgnoreCase("write")) {
                e.findElement(By.xpath(".//input")).sendKeys(value);
            }
            if (action.equalsIgnoreCase("click")) {
                try {
                    e.findElement(By.xpath("./parent::button")).click();
                }catch (NoSuchElementException ignore){
                    e.click();
                }
            }
            if(action.equalsIgnoreCase("checked")){
                WebElement _e = e.findElement(By.xpath("./preceding::input[@type = 'checkbox'][1]"));
                ((JavascriptExecutor) PageFactory.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", _e);
                _e.sendKeys(Keys.SPACE);
                _e.isEnabled();
                if(!_e.isSelected()){
                    System.out.println(String.format("Чекбокс \"%s\" не выбран", field));
                }
            }
        }
    }

    @Когда("^проверить что найденно \"(.*?)\" элементов$")
    public void checkSerch(String arg){
        //xPath для поиска количества найденых элементов
        int count  = PageFactory.getWebDriver().findElements(By.xpath(".//div[contains(@class, 'search-results')]//div[@data-id]")).size();
        Assert.assertTrue(String.format("Элементов на странице < %s", arg), count >= Integer.valueOf(arg));


    }

    @Когда("^найти первый элемент в списке$")
     public void findFirstElement(){
        WebElement e = PageFactory.getWebDriver().findElements(By.xpath(".//div[contains(@class, 'search-results')]//div[@data-id]")).get(0);
        String value = e.findElement(By.xpath(".//div[contains(@class, 'title')]")).getText();
        Stash.put("save", value);


    }
    @Когда("^ввод в поисковую строку заполненного значения$")
    public void enterValue() throws PageException {
        String value = Stash.getValue("save");
        WebElement e = PageFactory.getInstance().getCurrentPage().getElementByTitle("Поиск");
        e.sendKeys(value);
        e.sendKeys(Keys.ENTER);
    }

    @Когда("^сравнение$")
    public void compareValue(){
        WebElement e = PageFactory.getWebDriver().findElement(By.xpath(".//div[contains(@class, 'n-title')]"));
        String text = e.getText();
        Assert.assertTrue("Запомненое значение совпадает с представленным!", text.equals(Stash.getValue("save")));
    }
}
