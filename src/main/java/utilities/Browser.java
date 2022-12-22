package utilities;

import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	// Declaring WebDriver
	private WebDriver driver;

	/**
	 * Below method will initialize the Driver and Launches the corresponding browser upon request
	 * @param browser
	 * @param mode
	 * @return
	 */
	public WebDriver setup(String browser) {
		switch (browser.trim().toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			driver = new ChromeDriver(options);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();

			/*FirefoxBinary binary = new FirefoxBinary(new File("C:\\Users\\sm3\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
			FirefoxProfile profile = new FirefoxProfile();
			driver = new FirefoxDriver(binary, profile);*/

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "safari":
			assertTrue(false, "=> "+browser+"? Implementation is not available...");
			break;
		default:
			assertTrue(false, "=> "+browser+"? Invalid Browser value provided OR Implementation is not available...");
			break;
		}
		return driver;
	}
}
