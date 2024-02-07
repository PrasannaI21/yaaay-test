package zenkenIndia.candidate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zenkenIndia.pageobjects.candidate.LoginPage;
import zenkenIndia.pageobjects.candidate.TopPage;

public class Login{
	
	WebDriver driver;
	TopPage topPage;
	LoginPage loginPage; 
	
	String username = "dspfuser";
	String password = "FobaRm8.";
	String domain = "stage.dspf-dev.com/login";
	String url;
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prasa\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		url = "https://" + username + ":" + password + "@" + domain;
		driver.get(url);
		topPage = new TopPage(driver);
		topPage.acceptCookies();
	}
	
	@Test
	public void invalidLoginBlankFields() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyReqText();
	}
	
	@Test
	public void invalidLoginIdBlank() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyEmailReqText("Test123^");
	}
	
	@Test
	public void invalidLoginPasswordBlank() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyEmailReqText("prasanna.inamdar@zenken.co.jp");
	}
	
	@Test
	public void invalidLoginIncorrectId() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyIncorrectEmail("prasanna.inamdar@zenken.co.jp1", "Test123^");
	}
	
	@Test
	public void invalidLoginInvalidId() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyInvalidEmail("Hey there", "Test123^");
	}
	
	@Test
	public void invalidLoginIncorrectPassword() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyIncorrectPass("prasanna.inamdar@zenken.co.jp", "test123^");
	}
	
	@Test
	public void validLogin() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyLogIn("prasanna.inamdar@zenken.co.jp", "Test123^");
	}
	
	@AfterMethod
	public void terminate()
	{
		//driver.close();
		driver.quit();
	}
		
	}

