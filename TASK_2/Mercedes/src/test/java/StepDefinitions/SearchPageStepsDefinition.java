package StepDefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import Pages.SearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchPageStepsDefinition {

    private WebDriver driver;
    private SearchPage searchPage;
    
    @Before
    public void Setup() {
    	driver = new ChromeDriver();
    	//driver = new EdgeDriver();
    }
    @After
    public void Teardown() {
        if(driver!=null)
            driver.quit();
    }
	
	@Given("I am on the search page")
	public void i_am_on_the_search_page() {
		
		driver.manage().window().maximize();
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       
        searchPage = new SearchPage(driver);
        searchPage.accept_cookies();    
	}

	@And("I have chosen my location")
	public void i_have_chosen_my_location(io.cucumber.datatable.DataTable dataTable) {
        searchPage.select_state(dataTable.cell(1,0));
        searchPage.enter_postal_code(dataTable.cell(1,1));
        searchPage.select_purpose(dataTable.cell(1,2));
        searchPage.enter_location();
	}

	@When("I filter for Pre-Owned cars with colour {string}")
	public void i_filter_for_pre_owned_cars_with_colour(String color) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		searchPage.filter_cars(color);
		searchPage.sort_list();
	}

	@And("I enquire about the most expensive one with invalid data")
	public void i_enquire_about_the_most_expensive_one_with_invalid_data() {
		searchPage.enquire_most_expensive();
	}

	@Then("I get a proper error indicating the reason")
	public void i_get_a_proper_error_indicating_the_reason() {
		searchPage.validate_error();
	}

	
	
	
	
	
}
