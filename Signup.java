package module1;

import java.lang.reflect.Method;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Signup {
	
	WebDriver driver;
	
	static ExtentReports report = new ExtentReports("F:\\ExtentReportresults\\signup.html");
	static ExtentTest test; 
	
	@BeforeClass
	public void openGithub() {
		System.setProperty("webdriver.chrome.driver", "F:\\Chromedriver88\\chromedriver.exe");  
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.github.com/");
	}
	
	@BeforeMethod
	public static void startReport(Method result) {
		test = report.startTest(result.getName());
	}
	
	@Test(priority=0)
	public void clickOnSigninButton() {
		WebElement signupButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/a[2]"));
		test.log(LogStatus.INFO, "Checking for the presence of Signup Button");
		if(signupButton.isEnabled()) {
			test.log(LogStatus.PASS, "Signup button is present.");
			test.log(LogStatus.INFO, "Clicking on Signup button.");
			signupButton.click();
		}
		else {
			test.log(LogStatus.ERROR, "Problem with the Signup button element.");
		}
	}
	
	@Test(priority=1)
	public void inputFieldsPresent() {
		WebElement usernameField = driver.findElement(By.name("user[email]"));
		WebElement emailField = driver.findElement(By.id("user_email"));
		WebElement passwordField = driver.findElement(By.id("user_password"));
		test.log(LogStatus.INFO, "Checking for the presence of input fields.");
		if(usernameField.isDisplayed() && passwordField.isDisplayed() && emailField.isDisplayed()) {
			test.log(LogStatus.INFO, "Input fields are present.");
		}
		else {
			test.log(LogStatus.FAIL, "Something wrong with input fields.");
		}
	}
	
	@Test(priority=4)
	public void inputCredentials() {
		test.log(LogStatus.INFO, "Entering credentials for Signup.");
		WebElement signupbutton = driver.findElement(By.className("my-3"));
		driver.findElement(By.name("user[email]")).sendKeys("vasuag777");
		driver.findElement(By.id("user_email")).sendKeys("vasuag777@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("iam12");
		driver.findElement(By.className("my-3")).click();
		if(signupbutton.isEnabled()) {
			test.log(LogStatus.PASS, "Signup button is present.");
			test.log(LogStatus.INFO, "Clicking on Signup button.");
			signupbutton.click();
		}
		else {
			test.log(LogStatus.ERROR, "Problem with the Create Account button element.");
		}
		test.log(LogStatus.PASS, "Redirecting to home page...");
	}
	
	@AfterClass
	public void closeChromeWindow() {
		driver.close();
    }
	
	@AfterMethod
	public void EndReport() {
		report.endTest(test);
		report.flush();
	}
}