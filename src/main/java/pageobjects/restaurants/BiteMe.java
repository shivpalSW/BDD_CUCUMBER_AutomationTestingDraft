package pageobjects.restaurants;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Helper;

public class BiteMe implements Restaurant {
	// Declarations
	private Helper helper;
	private Logger logger;

	/**
	 * Constructor for initializing the web elements and assigning logger, helper instances for method interactions
	 * @param driver
	 * @param logger
	 * @param helper
	 */
	public BiteMe(WebDriver driver, Logger logger, Helper helper) {
		PageFactory.initElements(driver, this);
		this.logger = logger;
		this.helper = helper;
	}

	@FindBy(xpath = "//h1[@title='Bite Me']")
	private WebElement biteMeTitle;

	@FindBy(xpath = "//div[h2[text()='Cupcakes']]//div[@itemprop]")
	private List<WebElement> allCupcakes;

	@FindBy(xpath = "//div[h2[text()='Cupcakes']]//div[text()='ADD']")
	private List<WebElement> addCupcakes;

	@FindBy(xpath = "//div[h2[text()='Cupcakes']]//div[text()='ADD']/following::div[text()='+']")
	private List<WebElement> addMoreCupcakes;

	@FindBy(xpath = "//div[text()=' is currently closed. Please try some other restaurants.']")
	private List<WebElement> biteMeClosed;

	@FindBy(xpath = "//div[text()='Checkout']")
	private WebElement checkout;

	/**
	 * Below method is to validate Bite Me Restaurant Page title
	 * @param expectedTitle
	 */
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
	public void addItems(String dessert, int quantity) {
		helper.waitForElementVisibility(this.allCupcakes, 8);
		for(int i=0;i<this.allCupcakes.size();i++) {
			if(this.allCupcakes.get(i).getText().trim().equalsIgnoreCase(dessert) || dessert.contains(this.allCupcakes.get(i).getText().trim())) {
				logger.info("=> Desert '"+dessert+"' is there...");
				try {
					if(this.addCupcakes.get(i) != null) {
						helper.waitFor(3);
						helper.waitForElementClickability(this.addCupcakes.get(i), 3);
						this.addCupcakes.get(i).click();
						while(--quantity > 0) {
							helper.waitFor(2);
							WebElement ele = this.addMoreCupcakes.get(i);
							helper.waitForElementVisibility(ele, 5);
							ele.click();
							helper.logScreenshot();
						}
						logger.info("=> More '"+dessert+"' Cupcakes  added...");
						helper.logScreenshot();
					} 
				} catch (Exception e) {
					if(e.toString().contains("IndexOutOfBoundsException")) {
						logger.error("=> Dessert '"+dessert+"' is not availale...");
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
