package pageobjects;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Helper;

public class SecureCheckout {
	// Declarations
	private Helper helper;
	private Logger logger;

	/**
	 * Constructor for initializing the web elements and assigning logger, helper instances for method interactions
	 * @param driver
	 * @param logger
	 * @param helper
	 */
	public SecureCheckout(WebDriver driver, Logger logger, Helper helper) {
		PageFactory.initElements(driver, this);
		this.logger = logger;
		this.helper = helper;
	}

	@FindBy(xpath = "//div[@class='_33KRy']")
	private List<WebElement> allItems;

	@FindBy(xpath = "//div[@class='_2zAXs']")
	private List<WebElement> allItemsCount;

	@FindBy(xpath = "//div[text()='+']")
	private List<WebElement> addItems;

	@FindBy(xpath = "//div[text()='+']/following::div[1]")
	private List<WebElement> removeItems;

	@FindBy(xpath = "//div[text()='SIGN UP' or text()='New to Swiggy?']")
	private WebElement signUp;

	@FindBy(id = "name")
	private WebElement name;

	@FindBy(id = "mobile")
	private WebElement mobile;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//div[text()='Have a referral code?']")
	private WebElement haveAReferralCode;

	@FindBy(id = "referral")
	private WebElement referral;

	@FindBy(xpath = "//a[text()='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(xpath = "//label[@for='email']")
	private WebElement invalidEmailAddress;

	/**
	 * Below method validates an actual and expected items, item counts added to the cart
	 * @param expectedItems
	 */
	public void verifyCheckoutItems(Map<String, Integer> expectedItems) {
		helper.waitForElementVisibility(this.allItems, 8);

		// Logging items and quantities for the reference
		for(Entry<String, Integer> set: expectedItems.entrySet()) {
			logger.info("=> Item: "+set.getKey()+", quantity: "+set.getValue());
		}

		// Validating items and quantities from checkout list
		for(int i=0;i<this.allItems.size();i++) {
			this.allItemsCount.get(i).getText().trim().equals(expectedItems.get(this.allItems.get(i).getText().trim()).toString());
		}

		logger.info("=> '"+expectedItems+"' are there in the Checkout page...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to click on the sign up button
	 */
	public void clickOnSignUp() {
		helper.waitForElementVisibility(this.signUp, 3);
		helper.isDisplayed(this.signUp);

		this.signUp.click();
		logger.info("=> Clicked on the New User Sign Up...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to enter the name
	 */
	public void enterName(String name) {
		helper.isDisplayed(this.name);

		this.name.sendKeys(name);
		helper.validateValue(this.name, name);
		logger.info("=> Entered the name as '"+name+"'...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to enter the mobile number
	 */
	public void enterPhoneNumber(String number) {
		helper.isDisplayed(this.mobile);

		this.mobile.sendKeys(number);
		helper.validateValue(this.mobile, number);
		logger.info("=> Entered the mobile number as '"+number+"'...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to enter the email address
	 */
	public void enterEmail(String email) {
		helper.isDisplayed(this.email);

		this.email.sendKeys(email);
		helper.validateValue(this.email, email);
		logger.info("=> Entered the email as '"+email+"'...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to enter the password
	 */
	public void enterPassword(String password) {
		helper.isDisplayed(this.password);

		this.password.sendKeys(password);
		helper.validateValue(this.password, password);
		logger.info("=> Entered the password as '"+password+"'...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to click on Have a referral code?
	 */
	public void clickOnHaveAReferallCode() {
		helper.isDisplayed(this.haveAReferralCode);

		this.haveAReferralCode.click();
		logger.info("=> Clicked on the Have a referral code?...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to click on CONTINUE
	 */
	public void clickOnContinue() {
		helper.isDisplayed(this.continueBtn);

		this.continueBtn.click();
		logger.info("=> Clicked on the CONTINUE...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to verify Invalid Email error text
	 * @param expectedError
	 */
	public void verifyEmailErrorMessage(String expectedError) {
		helper.waitForElementVisibility(this.invalidEmailAddress, 3);
		helper.isDisplayed(this.invalidEmailAddress);
		helper.waitFor(2);

		helper.validateActualAndExpected(this.invalidEmailAddress.getText().trim(), expectedError);
		logger.info("=> Actual '"+this.invalidEmailAddress.getText().trim()+"' and Expected '"+expectedError+"' invalid email error is verified...");
		helper.logScreenshot();
	}

	/**
	 * Below method is to remove the item quantity based on the provived item
	 * @param itemName
	 * @param quantity
	 */
	public void removeItemFromCheckout(String itemName, int quantity) {
		helper.waitForElementVisibility(this.allItems, 8);

		for(int i=0;i<allItems.size();i++) {
			if(allItems.get(i).getText().trim().equals(itemName)) {
				while(quantity-->0) {
					WebElement ele = this.removeItems.get(i);
					helper.waitForElementVisibility(ele, 3);
					helper.waitForElementClickability(ele, 3);
					ele.click();
					helper.logScreenshot();
				}
				break;
			}
		}
		helper.logScreenshot();
	}
}
