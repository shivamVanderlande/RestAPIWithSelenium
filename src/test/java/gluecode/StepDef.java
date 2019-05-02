package gluecode;

import utilities.ConfigFileReader;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

import utilities.Utils;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDef extends Utils {

	private Response response;
	WebDriver driver;

	@Given("^To initiate Rest service to get country details with code as \"([^\"]*)\"$")
	public void to_initiate_Rest_service_to_get_country_details_with_code_as(String arg1) throws Throwable {
		setBaseURI();

		Utils.path = arg1;

		// Get response
		response = getResponse();
		System.out.println("************The Response value as --" + response.prettyPrint());
	}

	@Then("^Respose status code should be \"([^\"]*)\"$")
	public void respose_status_code_should_be(Integer arg1) throws Throwable {
		// To verify the response status code
		assertEquals("Status Check Failed!", 200, response.getStatusCode());

	}

	@Then("^response should includes the following$")
	public void response_includes_the_following(DataTable arg1) throws Throwable {

		// This method to verify response body
		List<List<String>> data = arg1.raw();
		System.out.println("*************** Actua Data Table - data value as-- " + data);

		response.then().body("RestResponse.result.name", equalTo(data.get(1).get(0)));
		response.then().body("RestResponse.result.alpha2_code", equalTo(data.get(1).get(1)));
		response.then().body("RestResponse.result.alpha3_code", equalTo(data.get(1).get(2)));
	}

	@Given("^I am on login page$")
	public void i_am_on_login_page() throws Throwable {
		ConfigFileReader configFileReader = new ConfigFileReader();
		System.out.println(configFileReader.getDriverPath());
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		// System.setProperty("webdriver.chrome.driver","C:/Data/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.get(configFileReader.getApplicationUrl());

	}

	@When("^I enter \"([^\"]*)\" and \"([^\"]*)\" and click on log in$")
	public void i_enter_and_and_click_on_log_in(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("username")).sendKeys(arg1);
		driver.findElement(By.id("password")).sendKeys(arg2);
		driver.findElement(By.name("login")).click();
	}

	@Then("^I should login to site$")
	public void i_should_login_to_site() throws Throwable {

	}

}