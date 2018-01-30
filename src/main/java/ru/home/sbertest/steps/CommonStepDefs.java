package ru.home.sbertest.steps;

import cucumber.api.java.ru.Когда;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;

public class CommonStepDefs {

    @Когда("^открывает страницу \"(.*?)\"$")
    public void openPage(String arg) throws PageInitializationException {
        PageFactory.getInstance().getPage(arg);
    }

    @Когда("^нажать кнопку \"(.*?)\"$")
    public void click(String arg) throws PageException {
        PageFactory.getInstance().getCurrentPage().clickElementByTitle(arg);
    }

    @Когда("^ждать \"(.*?)\" секунд$")
    public void wait(String arg){
        try{
            Thread.sleep(Integer.valueOf(arg)*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
