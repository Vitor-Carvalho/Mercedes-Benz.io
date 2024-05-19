package Pages;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;


public class SearchPage {

	private WebDriver driver;


	//Locators
	private By accept_cookies_banner = By.xpath("//*[@id=\"app\"]/div[1]/cmm-cookie-banner");

	private By accept_cookies_button = By.cssSelector("div > div > div.cmm-cookie-banner__content > cmm-buttons-wrapper > div > div > wb7-button.button.button--accept-all.wb-button.hydrated");
	
	private By state_box = By.xpath("//wb-select-control[@class='dcp-header-location-modal-dropdown hydrated']");
	
	private By postal_code = By.xpath("//input[@aria-labelledby='postal-code-hint']");

	private By purpose_radioB = By.xpath("//*[@id=\"app\"]/div[1]/header/div/div[4]/div[1]/div/div[1]/div/div/div/wb-radio-control[1]");
	
	private By purpose_radioP = By.xpath("//*[@id=\"app\"]/div[1]/header/div/div[4]/div[1]/div/div[1]/div/div/div/wb-radio-control[2]");
	
	private By continue_location_button = By.xpath("//*[@id=\"app\"]/div[1]/header/div/div[4]/div[1]/div/div[2]/button");

	private By filter = By.xpath("//span[@class='filter-toggle']");

	private By pre_owned_tab = By.xpath("//button[@class=\"wb-button wb-button--tertiary wb-button--medium\"]");

	private By color_filter = By.xpath("//*[text()='Colour']");
	
	private By color_box = By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div[1]/div[2]/div[1]/div/div/div[1]/div[4]/div[7]/div/div[2]/div/div/a/span[1]");

	private By sorting_box = By.xpath("/html/body/div/div[1]/main/div[2]/div[1]/div[2]/div[2]/div[2]/wb-select-control/wb-select/select");

	private By result = By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div[1]/div[2]/div[2]/div[3]/div[1]/div");

	private By enquire_button = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[1]/div[3]/div/div[2]/div/button");
	
	private By vin_text = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[1]/div[4]/div/div/div/div/div/ul/li[12]/span[2]");
	
	private By modelyear_text = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[1]/div[4]/div/div/div/div/div/ul/li[4]/span[2]");

	private By contact_first_name = By.xpath("//div[@data-test-id='rfq-contact__first-name']");

	private By contact_last_name = By.xpath("//div[@data-test-id='rfq-contact__last-name']");

	private By contact_email = By.xpath("//div[@data-test-id='rfq-contact__email']");

	private By contact_phone = By.xpath("//div[@data-test-id='rfq-contact__phone']");

	private By contact_postal_code = By.xpath("//div[@data-test-id='rfq-contact__postal-code']");

	private By contact_privacy = By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[1]/div[7]/div/div[1]/div/div[2]/div/div/div/div/div[1]/form/div/div[8]/div/div/wb-checkbox-control/label/wb-icon");

	private By contact_proceed_button = By.xpath("//*[text()=' Proceed ']");
	
	private By error_message = By.xpath("//ul[@class=\"dcp-error-message-error-list__item\"]/li");

	
	
    //Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void accept_cookies() {
    	WebElement shadowHost = driver.findElement(accept_cookies_banner);
    	SearchContext shadowRoot = shadowHost.getShadowRoot();
    	shadowRoot.findElement(accept_cookies_button).click();
   }

    public void select_state(String state) {
    	new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(state_box)).click();
        driver.findElement(By.xpath("//*[text()='"+state+"']")).click();
    }

    public void enter_postal_code(String code) {
    	new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(purpose_radioP));	
        driver.findElement(postal_code).sendKeys(code);
    }

    public void select_purpose(String purpose) {
    	if (purpose.trim() == "Private")
    		driver.findElement(purpose_radioP).click();
    	else
    		driver.findElement(purpose_radioB).click();
    }

    public void enter_location() {
        driver.findElement(continue_location_button).click();
    }

    public void filter_cars(String color)  {
    	new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(filter));
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	WebElement f = driver.findElement(filter);
        js.executeScript("arguments[0].click();", f);
        
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(pre_owned_tab));
    	WebElement pre = driver.findElement(pre_owned_tab);
        js.executeScript("arguments[0].click();", pre);
        
        WebElement cf = driver.findElement(color_filter);
        // Scrolling down the page till the element is found	
        js.executeScript("arguments[0].scrollIntoView();", cf);
        js.executeScript("arguments[0].click();", cf);
             
        WebElement cb = driver.findElement(color_box);
        // Scrolling down the page till the element is found	
        js.executeScript("arguments[0].scrollIntoView();", cb);
        js.executeScript("arguments[0].click();", cb);
        
        WebElement c = driver.findElement(By.xpath("//*[text()=\" "+color+" \"]"));
        js.executeScript("arguments[0].click();", c);
    }
    
    public void sort_list() {
        Select dropdown = new Select(driver.findElement(sorting_box));
        dropdown.selectByVisibleText(" Price (descending) "); 
        }
    
    public void enquire_most_expensive() {
    	new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(result));
    	
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	WebElement car;
    	
    	try {
    		car = driver.findElement(result);	
    		car.click();
    	}
    	catch(org.openqa.selenium.StaleElementReferenceException ex)
    	{
    	    car = driver.findElement(result);	
    	    car.click();
    	}
    	
    	new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(enquire_button));
    	write_data();
    	WebElement enquire = driver.findElement(enquire_button);
        js.executeScript("arguments[0].click();", enquire);
         	
    	//driver.findElement(contact_first_name).sendKeys("FIRST");
    	//driver.findElement(contact_last_name).sendKeys("LAST");
    	//driver.findElement(contact_email).sendKeys("me@com");
    	//driver.findElement(contact_phone).sendKeys("0441234567");
    	//driver.findElement(contact_postal_code).sendKeys("4400");
    	//driver.findElement(contact_privacy).click();
        
    	WebElement button;
    	try {
    		button = driver.findElement(contact_proceed_button);      
    	}
    	catch(org.openqa.selenium.StaleElementReferenceException ex)
    	{
    		button = driver.findElement(contact_proceed_button);
    	}

        // Scrolling down the page till the element is found	    
        js.executeScript("arguments[0].scrollIntoView();", button);
        js.executeScript("arguments[0].click();", button);
    }
    
    public void validate_error() {
    	String actualMsg=driver.findElement(error_message).getText().trim();
    	Assert.assertEquals(actualMsg,"Please check the data you entered.");
    }
    
    private void write_data() {
    	
        WebElement vin = driver.findElement(vin_text);
        WebElement modelYear = driver.findElement(modelyear_text);
       
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", vin);

        String vinNumber = vin.getText().trim();
        String modelYearNumber = modelYear.getText().trim();

        try (FileWriter writer = new FileWriter("car_details.txt")) {
            writer.write("VIN number: " + vinNumber + "\n");
            writer.write("Model Year: " + modelYearNumber + "\n");
            System.out.println("Car details saved to car_details.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
