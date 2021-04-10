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

public class repository {
	
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
		WebElement signinButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/a[1]"));
		test.log(LogStatus.INFO, "Checking for the presence of Signin Button");
		if(signinButton.isEnabled()) {
			test.log(LogStatus.PASS, "Signin button is present.");
			test.log(LogStatus.INFO, "Clicking on Signin button.");
			signinButton.click();
		}
		else {
			test.log(LogStatus.ERROR, "Problem with the Signin button element.");
		}
	}
	
	@Test(priority=1)
	public void inputFieldsPresent() {
		WebElement usernameField = driver.findElement(By.name("login"));
		WebElement passwordField = driver.findElement(By.name("password"));
		test.log(LogStatus.INFO, "Checking for the presence of input fields.");
		if(usernameField.isDisplayed() && passwordField.isDisplayed()) {
			test.log(LogStatus.INFO, "Input fields are present.");
		}
		else {
			test.log(LogStatus.FAIL, "Something wrong with input fields.");
		}
	}
	
	@Test(priority=2)
	public void inputCredentials() {
		test.log(LogStatus.INFO, "Entering credentials for login.");
		driver.findElement(By.name("login")).sendKeys("ingitag777");
		driver.findElement(By.name("password")).sendKeys("10658ingitag");
		driver.findElement(By.name("commit")).click();
		
	}
	
	@Test(priority=3)
	public void newrepo() {
		driver.findElement(By.xpath("//*[@id=\"repos-container\"]/h2/a")).click();
		test.log(LogStatus.INFO, "Entering name for New Repository.");
		driver.findElement(By.id("repository_name")).sendKeys("Test repo");
		driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/div[4]/div[1]/label")).click();
		
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