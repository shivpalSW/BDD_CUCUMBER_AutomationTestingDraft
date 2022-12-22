package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Helper;

public class SwiggyRestaurants {
	// Declarations
	private Helper helper;
	private Logger logger;

	/**
	 * Constructor for initializing the web elements and assigning logger, helper instances for method interactions
	 * @param driver
	 * @param logger
	 * @param helper
	 */
	public SwiggyRestaurants(WebDriver driver, Logger logger, Helper helper) {
		PageFactory.initElements(driver, this);
		this.logger = logger;
		this.helper = helper;
	}

	@FindBy(xpath = "//a[@title='Swiggy']")
	private WebElement swiggyTitle;
	
	@FindBy(xpath = "//span[text()='Search']")
	private WebElement search;

	@FindBy(xpath = "//input[@placeholder='Search for restaurants or dishes']")
	private WebElement searchForRestaurantsOrDishes;

	@FindBy(xpath = "//span[text()='ESC']")
	private WebElement escape;

	@FindBy(xpath = "//div[@class='_3XX_A']//div[@title]/parent::div/div[1]")
	private List<WebElement> allRestaurants;

	@FindBy(xpath = "//div[div[contains(text(),'Choose') and contains(text(),'Outlet')]]/following::div/a//div[@class]")
	private List<WebElement> outlets;
	
	/**
	 * Below method is for verifying the title
	 */
	public void waitForTitle() {
		helper.waitForElementVisibility(this.swiggyTitle, 8);
		
		logger.info("=> Swiggy Icon Title is loaded...");
		helper.logScreenshot();
	}

	/**
	 * Below method is for clicking on the search
	 */
	public void clickOnSearch() {
		helper.isDisplayed(this.search);

		this.search.click();
		logger.info("=> Clicked on the Search in Restaurants page...");
		helper.logScreenshot();
	}

	/**
	 * Below method is for searching Restaurants Or Dishes
	 * @param searchText
	 */
	public void searchForRestaurants(String searchText) {
		helper.waitForElementVisibility(this.searchForRestaurantsOrDishes, 3);
		helper.isDisplayed(this.searchForRestaurantsOrDishes);

		this.searchForRestaurantsOrDishes.clear();
		helper.validatePlaceholder(this.searchForRestaurantsOrDishes, "Search for restaurants or dishes");
		this.searchForRestaurantsOrDishes.sendKeys(searchText + Keys.ENTER);
		helper.validateValue(this.searchForRestaurantsOrDishes, searchText);

		logger.info("=> Searched '"+searchText+"' on the Search For Restaurants Or Dishes search field...");
		helper.logScreenshot();
	}

	/**
	 * Below method is for selecting the restaurant from all restaurant results 
	 * @param restaurant
	 */
	public void selectRestaurant(String restaurant) {
		boolean selected = false;
		helper.waitForElementVisibility(this.allRestaurants, 5);
		for(WebElement rest: this.allRestaurants) {
			helper.isDisplayed(rest);
			if(rest.getText().trim().equalsIgnoreCase(restaurant)) {
				rest.click();
				selected = !selected;
				helper.logScreenshot();
				break;
			}
		}
		if(selected)
			logger.info("=> '"+restaurant+"' is selected from the all the restaurant search results...");
		else
			logger.error("=> '"+restaurant+"' is NOT selected/ NOT present from the all the restaurant search results...");
		helper.logScreenshot();
	}

	/**
	 * Below method is for selecting the outlet if present
	 * @param outlet
	 */
	public void selectOutletIfExists(String outlet) {
		boolean outletPresent = false;
		for(WebElement ol: outlets) {
			helper.isDisplayed(ol);
			if(ol.getText().trim().contains(outlet)) {
				ol.click();
				outletPresent = !outletPresent;
				helper.logScreenshot();
				break;
			}
		}
		if(outletPresent)
			logger.info("=> '"+outlet+"' is selected from the all the outlets...");
		else
			logger.warn("=> '"+outlet+"' is NOT selected/ NOT present from the all the outlets...");
		helper.logScreenshot();
	}
}
