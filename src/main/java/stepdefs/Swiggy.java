package stepdefs;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.SecureCheckout;
import pageobjects.SwiggyHome;
import pageobjects.SwiggyRestaurants;
import pageobjects.restaurants.BiteMe;
import pageobjects.restaurants.LeonGrill;
import pageobjects.restaurants.Restaurant;
import utilities.Browser;
import utilities.Helper;

public class Swiggy {
	// Declarations
	private WebDriver driver;
	private Helper helper;
	private Logger logger;
	private SwiggyHome swiggyHome;
	private SwiggyRestaurants swiggyRestaurants;
	private Restaurant restaurant;
	private SecureCheckout checkout;
	private Map<String, Integer> items;

	/**
	 * Below method will launch the browser and navigates to the provided url
	 * @param browserName
	 * @param url
	 */
	@Given("^I launch the (.*) and navigate to (.*)/$")
	public void iLaunchTheChromeAndNavigateToSwiggy(String browserName, String url) {
		driver = new Browser().setup(browserName);
		driver.get(url);

		PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j.properties");
		logger = Logger.getLogger(Swiggy.class.getName());

		helper = new Helper(driver, logger);
		swiggyHome = new SwiggyHome(driver, logger, helper);
		swiggyRestaurants = new SwiggyRestaurants(driver, logger, helper);
		checkout = new SecureCheckout(driver, logger, helper);

		items = new LinkedHashMap<>();
	}

	@When("^I am on (.*) page$")
	public void iAmOnSwiggyHomePage(String url) {
		helper.waitUntilDOMLoaded();
		helper.verifyCurrentPageURL(url);
	}

	@Then("^I verify the page title is displayed as (.*)$")
	public void iVerifyThePageTitle(String pageTitle) {
		helper.verifyPageTitle(pageTitle);
	}

	@When("^I enter the delivery location as (.*)$")
	public void iEnterTheDeliveryLocation(String deliveryLocation) {
		swiggyHome.enterLocation(deliveryLocation);
	}

	@Then("^I select the result which is (.*)$")
	public void iSelectTheResult(String result) {
		swiggyHome.selectFromSuggestions(result);
	}

	@Then("^I click on the search$")
	public void iClickOnTheSearch() {
		swiggyHome.clickOnFindFood();
	}

	@Then("^I should navigate to (.*) page$")
	public void iShouldNavigateToProvidedPage(String page) {
		helper.waitUntilDOMLoaded();
		swiggyRestaurants.waitForTitle();
		helper.verifyCurrentPageURL(page);
	}

	@When("^I search for (.*) and click on search$")
	public void iSearchForRestaurant(String restaurant) {
		swiggyRestaurants.clickOnSearch();
		swiggyRestaurants.searchForRestaurants(restaurant);
		switch (restaurant.trim()) {
		case "Bite Me":
			this.restaurant = new BiteMe(driver, logger, helper);
			break;
		case "Leon Grill":
			this.restaurant = new LeonGrill(driver, logger, helper); 
			break;
		default:
			break;
		}
	}

	@Then("^I select the Restaurant as (.*)$")
	public void iSelectTheRestaurant(String restaurant) {
		swiggyRestaurants.selectRestaurant(restaurant);
	}

	@Then("^I select outlet as (.*) if displayed$")
	public void iSelectOutletIfDisplayed(String outlet) {
		swiggyRestaurants.selectOutletIfExists(outlet);
	}

	@Then("^I should land on the (.*) page$")
	public void iShouldLandOnThisPage(String page) {
		this.restaurant.verifyRestaurantTitle(page);
	}

	@Then("^I add (\\d+) (.*) to the cart$")
	public void iAddItemsToCart(int quatity, String item) {
		this.restaurant.addItems(item, quatity);
		items.put(item, quatity);
	}

	@When("^I click on checkout$")
	public void iClickOnCheckout() {
		this.restaurant.clickOnCheckout();
	}

	@Then("^I verify items added in checkout page (.*)$")
	public void iVerifyItemsAddedInCheckout(String page) {
		helper.verifyCurrentPageURL(page);

		checkout.verifyCheckoutItems(items);
	}

	@When("^I click on New to Swiggy\\? SIGN UP$")
	public void iClickOnNewToSwiggySIGNUP() {
		checkout.clickOnSignUp();
	}

	@Then("^I enter name as (.*)$")
	public void iEnterName(String name) {
		checkout.enterName(name);
	}

	@Then("^I enter phone number as (.*)$")
	public void iEnterPhoneNumber(String mobile) {
		checkout.enterPhoneNumber(mobile);
	}

	@Then("^I enter email as (.*)$")
	public void iEnterEmail(String email) {
		checkout.enterEmail(email);
	}

	@Then("^I enter password as (.*)$")
	public void iEnterPassword(String password) {
		checkout.enterPassword(password);
	}

	@Then("^I click on the Have a referral code\\?$")
	public void iClickOnTheHaveAReferralCode() {
		checkout.clickOnHaveAReferallCode();
	}

	@When("^I click on the CONTINUE$")
	public void iClickOnTheCONTINUE() {
		checkout.clickOnContinue();
	}

	@Then("^I should see an error message displayed as (.*) and I capture the screenshot$")
	public void iShouldSeeAnErrorMessageAndICaptureTheScreenshot(String expectedError) {
		checkout.verifyEmailErrorMessage(expectedError);
		helper.logScreenshot("InvalidEmail");
	}

	@Then("^I change the (.*) quantity to (\\d+)$")
	public void iChangeTheRedVelvetCupcakeQuantity(String itemName, int quantity) {
		checkout.removeItemFromCheckout(itemName, quantity);
	}

	@Then("^I capture the screenshot$")
	public void iCaptureTheScreenshot() {
		helper.logScreenshot("FinalCheckoutItems");
	}

	@Then("^I close the browser$")
	public void iCloseTheBrowser() {
		driver.quit();
	}
}
