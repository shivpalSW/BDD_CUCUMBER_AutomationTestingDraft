package pageobjects.restaurants;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Helper;

public class LeonGrill implements Restaurant {
	// Declarations
	private Helper helper;
	private Logger logger;

	/**
	 * Constructor for initializing the web elements and assigning logger, helper instances for method interactions
	 * @param driver
	 * @param logger
	 * @param helper
	 */
	public LeonGrill(WebDriver driver, Logger logger, Helper helper) {
		PageFactory.initElements(driver, this);
		this.logger = logger;
		this.helper = helper;
	}

	@FindBy(xpath = "//h1[@title='Leon Grill']")
	private WebElement biteMeTitle;

	@FindBy(xpath = "//div[h2[text()='Beverages']]//div[@itemprop]")
	private List<WebElement> allBeverages;

	@FindBy(xpath = "//div[h2[text()='Beverages']]//div[text()='ADD']")
	private List<WebElement> addBeverages;

	@FindBy(xpath = "//div[h2[text()='Beverages']]//div[text()='ADD']/following::div[text()='+']")
	private List<WebElement> addMoreBeverages;

	@FindBy(xpath = "//div[text()=' is currently closed. Please try some other restaurants.']")
	private List<WebElement> biteMeClosed;

	@FindBy(xpath = "//div[text()='Checkout']")
	private WebElement checkout;

	/**
	 * Below method is to validate Bite Me Restaurant Page title
	 * @param expectedTitle
	 */
	@Override
	public void verifyRestaurantTitle(String expectedTitle) {
		helper.waitForElementVisibility(this.biteMeTitle, 3);
		helper.isDisplayed(this.biteMeTitle);

		helper.validateActualAndExpected(this.biteMeTitle.getText().trim(), expectedTitle);
		logger.info("=> Bite Me Restaurant page is displayed...");
		helper.logScreenshot();
	}

	/**
	 * Below method is selecting the particular dessert and quantity of the desert
	 * @param cupcake
	 * @param quantity
	 */
	public void addItems(String beverage, int quantity) {
		helper.waitForElementVisibility(this.allBeverages, 8);
		for(int i=0;i<this.allBeverages.size();i++) {
			if(this.allBeverages.get(i).getText().trim().equalsIgnoreCase(beverage) || beverage.contains(this.allBeverages.get(i).getText().trim())) {
				logger.info("=> Desert '"+beverage+"' is there...");
				try {
					if(this.addBeverages.get(i) != null) {
						helper.waitFor(1);
						helper.waitForElementClickability(this.addBeverages.get(i), 3);
						this.addBeverages.get(i).click();
						while(--quantity > 0) {
							helper.waitFor(2);
							WebElement ele = this.addMoreBeverages.get(i);
							helper.waitForElementVisibility(ele, 5);
							ele.click();
							helper.logScreenshot();
						}
						logger.info("=> More '"+beverage+"' Cupcakes  added...");
						helper.logScreenshot();
					}
				} catch (Exception e) {
					if(e.toString().contains("IndexOutOfBoundsException")) {
						logger.error("=> Beverage '"+beverage+"' is not availale...");
						helper.logScreenshot();
					}
				}
			}
		}
	}

	/**
	 * Below method is to click on Checkout
	 */
	@Override
	public void clickOnCheckout() {
		helper.waitForElementVisibility(this.checkout, 3);
		helper.isDisplayed(this.checkout);

		this.checkout.click();
		logger.info("=> Clicked on the Checkout...");
	}

}
