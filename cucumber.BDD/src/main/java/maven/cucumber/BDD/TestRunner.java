package maven.cucumber.BDD;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
	features={"feature_files"},      //give feature file folder name
	glue={"testCase_Login"},  //stepdefinition file name
	plugin={"pretty","html:target/Report"},
	monochrome=true     //console report in proper manner
)


public class TestRunner {

}
