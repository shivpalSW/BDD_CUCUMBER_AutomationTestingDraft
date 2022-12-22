package utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.vimalselvam.cucumber.listener.Reporter;

public class Helper {

	// Declarations
	private WebDriver driver;
	private JavascriptExecutor jse;
	private FluentWait<WebDriver> wait;
	private Logger logger;

	/**
	 * Constructor for initializing the JavaScriptExecutor, FluentWait and assigning driver, logger instances for method interactions
	 * @param driver
	 * @param logger
	 */
	public Helper(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
		jse = (JavascriptExecutor) driver;
		wait = new FluentWait<WebDriver>(driver).withTimeout(3, TimeUnit.MINUTES).pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
	}

	/**
	 * Below method returns the current page title
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle().trim();
	}

	/**
	 * Below method is to validate the title
	 * @param title
	 */
	public void verifyPageTitle(String expectedTitle) {
		assertEquals(expectedTitle, getTitle());
	}

	/**
	 * Below method checks and waits until the DOM is loaded
	 */
	public void waitUntilDOMLoaded() {
		boolean domLoaded = false;
		int tries = 0;
		while(!domLoaded && tries++ <= 30) {
			String state = jse.executeScript("return document.readyState;").toString().trim();
			if(state.equalsIgnoreCase("complete")) {
				domLoaded = !domLoaded;
				logger.info("=> The DOM is completely loaded after '"+tries+"' seconds...");
			} else {
				logger.info("=> Waited '"+tries+"' seconds but the DOM is not yet loaded...");
			}
		}
	}

	/** Below method checks the given element is displayed or not in a page
	 * 
	 * @param element
	 */
	public void isDisplayed(WebElement element) {
		boolean actual = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return element;
			}
		}).isDisplayed();
		assertTrue(actual, "=> Element is not Displayed...");
	}

	/**
	 * Below method checks the given element is selected or not in a page
	 * @param element
	 */
	public void isSelected(WebElement element) {
		boolean actual = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return element;
			}
		}).isSelected();
		assertTrue(actual, "=> Element is not Selected...");
	}

	/**
	 * Below method performs validation based on the provided value 
	 * @param element
	 * @param value
	 */
	public void validateValue(WebElement element, String value) {
		isDisplayed(element);
		String actual = element.getAttribute("value").trim();
		String expected = value.trim();
		assertEquals(actual, expected);
	}

	/**
	 * Below method performs validation based on the provided value 
	 * @param element
	 * @param value
	 */
	public void validatePlaceholder(WebElement element, String value) {
		isDisplayed(element);
		String actual = element.getAttribute("placeholder").trim();
		String expected = value.trim();
		assertEquals(actual, expected);
	}

	/**
	 * Below method takes the screenshot and logs into the report
	 */
	public void logScreenshot() {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destin = new File("Screenshots"+File.separator+"Swiggy_"+new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date())+".png");
		try {
			com.google.common.io.Files.copy(source.getAbsoluteFile(), destin);
			Reporter.addScreenCaptureFromPath(destin.getAbsolutePath());
		} catch (Exception e) {
			logger.info("=> Error occurred while taking and logging the screenshot, error is : "+e);
		}
	}
	
	/**
	 * Below method takes the screenshot and logs into the report with specified name
	 */
	public void logScreenshot(String screenshotName) {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destin = new File("Screenshots"+File.separator+screenshotName+"_"+new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date())+".png");
		try {
			com.google.common.io.Files.copy(source.getAbsoluteFile(), destin);
			Reporter.addScreenCaptureFromPath(destin.getAbsolutePath());
		} catch (Exception e) {
			logger.info("=> Error occurred while taking and logging the screenshot, error is : "+e);
		}
	}

	/**
	 * Below method is to delete the screenshots
	 */
	public void deleteOldScreenshots() {
		try {
			java.nio.file.Files.list(Paths.get("Screenshots")).forEach(e -> {
				try {
					java.nio.file.Files.deleteIfExists(e);
					logger.info(e+" is deleted successfully...");
				} catch (IOException e1) {
					logger.info("=> Error occurred while deleting the screenshot '"+e+"', error is : "+e1);
				}
			});
		} catch (Exception e) {
			logger.info("=> Error occurred while deleting the screenshot, error is : "+e);
		}
	}

	/**
	 * Below method performs mouse over on the given element
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		new Actions(driver).moveToElement(element).build().perform();
	}

	/**
	 * Returns some randomly generated number
	 * @return
	 */
	public String getRandomNumber() {
		String randomNum = String.valueOf(new Random().nextInt(100000));
		logger.info("=> Randomly generated number is : "+randomNum);
		return randomNum;
	}

	/**
	 * Below method scrolls into the provided element view
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Below method is for validating the current page URL
	 * @param expectedURL
	 */
	public void verifyCurrentPageURL(String expectedURL) {
		assertEquals(driver.getCurrentUrl().trim(), expectedURL);
	}

	/**
	 * Below method is for waiting the specified amount of time
	 * @param seconds
	 */
	public void waitFor(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
			logger.info("=> Waited for '"+(seconds * 1000)+ "' milli senconds...");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Below method is for waiting an elements visibility
	 * @param elements
	 * @param seconds
	 */
	public void waitForElementVisibility(List<WebElement> elements, long seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	/**
	 * Below method is for waiting an element visibility
	 * @param element
	 * @param seconds
	 */
	public void waitForElementVisibility(WebElement element, long seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Below method is to assert actual and expected values same or not?
	 * @param actual
	 * @param expected
	 */
	public void validateActualAndExpected(String actual, String expected) {
		assertEquals(actual, expected);
	}
	
	/**
	 * Below method is for waiting an element clickable
	 * @param element
	 * @param seconds
	 */
	public void waitForElementClickability(WebElement element, long seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
	}
}
