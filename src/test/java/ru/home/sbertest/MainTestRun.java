package ru.home.sbertest;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import ru.sbtqa.tag.cucumber.TagCucumber;


@RunWith(TagCucumber.class)
@CucumberOptions(
        format = {"pretty"},
        glue = {"ru.home.sbertest.steps", "ru.sbtqa.tag.pagefactory.stepdefs"},
        features = {"src/test/resources/features/"},
        tags = {"@test"}
)
public class MainTestRun {
}
