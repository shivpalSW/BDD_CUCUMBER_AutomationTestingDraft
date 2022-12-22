package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Helper;

public class SwiggyHome {
	// Declarations
	private Helper helper;
	private Logger logger;

	/**
	 * Constructor for initializing the web elements and assigning logger, helper instances for method interactions
	 * @param driver
	 * @param logger
	 * @param helper
	 */
	public SwiggyHome(WebDriver driver, Logger logger, Helper helper) {
		PageFactory.initElements(driver, this);
		this.logger = logger;
		this.helper = helper;
	}

	@FindBy(id = "location")
	private WebElement location;

	@FindBy(xpath = "//div[text()='Clear']")
	private WebElement clear;

	@FindBy(xpath = "//span[text()='FIND FOOD']")
	private WebElement findFood;

	@FindBy(xpath = "//div[input[@id='location']]/following::div/span[last()]")
	private List<WebElement> suggestions;

	/**
	 * Method for entering the search location
	 * @param location
	 */
	public void enterLocation(String location) {
		helper.isDisplayed(this.location);

		this.location.clear();
		helper.validatePlaceholder(this.location, "Enter your delivery location");
		this.location.sendKeys(location);
		helper.logScreenshot();
		helper.validateValue(this.location, location);
		logger.info("=> Entered '"+location+"' in the location search field...");
		helper.logScreenshot();
	}

	/**
	 * Method for clearing the search location
	 */
	public void clearLocation() {
		helper.isDisplayed(this.clear);

		this.clear.clear();
		helper.validatePlaceholder(this.clear, "Enter your delivery location");
		logger.info("=> Cleared the location search field...");
		helper.logScreenshot();
	}

	/**
	 * Method for clicking on the Find Food
	 */
	public void clickOnFindFood() {
		helper.isDisplayed(this.findFood);

		this.findFood.click();
		logger.info("=> Clicked on the find food...");
		helper.logScreenshot();
	}

	/**
	 * Method for selecting the auto suggestion option from the location search drop down
	 * @param option
	 */
	public void selectFromSuggestions(String option) {
		helper.waitForElementVisibility(this.suggestions, 5);

		boolean selected = false;
		for(WebElement suggestion: this.suggestions) {
			helper.isDisplayed(suggestion);
			if(suggestion.getText().trim().equalsIgnoreCase(option)) {
				suggestion.click();
				selected = !selected;
				helper.logScreenshot();
				break;
			}
		}
		if(selected)
			logger.info("=> '"+option+"' is selected from the auto location search suggestion drop down...");
		else
			logger.error("=> '"+option+"' is NOT selected/ NOT present from the auto location search suggestion drop down...");
		helper.logScreenshot();
	}
}
