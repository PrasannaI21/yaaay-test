package ZenkenINDIA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zenkenIndia.pageobjects.LoginPage;
import zenkenIndia.pageobjects.TopPage;

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
	
//	@Test
//	public void invalidLoginIdBlankw() throws InterruptedException
//	{
//		TopPage topPage = new TopPage(driver);
//		topPage.goTo();
//		Thread.sleep(3000);
//		topPage.acceptCookies();
//		topPage.logIn();
//		Thread.sleep(1000);
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.verifyReqText();
//		loginPage.verifyEmailReqText();
//		loginPage.verifyPassReqText();
//		loginPage.verifyIncorrectEmail();
//		loginPage.verifyInvalidEmail();
//		loginPage.verifyIncorrectPass();
//		loginPage.verifyLogIn();
//	}
	
//	@Test
//	public void invalidLoginBlank() throws InterruptedException
//	{
//		TopPage topPage = new TopPage(driver);
//		topPage.goTo();
//		Thread.sleep(3000);
//		topPage.acceptCookies();
//		topPage.logIn();
//		Thread.sleep(1000);
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.verifyLogIn();
		
//	}
	
	@AfterMethod
	public void terminate()
	{
		driver.close();
	}
		
	}

