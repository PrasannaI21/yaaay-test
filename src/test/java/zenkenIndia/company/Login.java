package zenkenIndia.company;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zenkenIndia.pageobjects.company.LoginPage;

public class Login {

	WebDriver driver;
	LoginPage loginPage = new LoginPage(driver);
	
	String username = "dspfuser";
	String password = "FobaRm8.";
	String domain = "stage.dspf-dev.com/company/login";
	String url;
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prasa\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = "https://" + username + ":" + password + "@" + domain;
		driver.get(url);
	}
	
	@Test
	public void invalidLoginBlankFields() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyReqText();
	}
	
	@Test
	public void invalidLoginIdBlank()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyEmailReqText("DarkKnight2!");
	}
	
	@Test
	public void invalidLoginPasswordBlank()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyPassReqText("prasanna.inamdar@zenken.co.jp");
	}
	
	@Test
	public void invalidLoginIncorrectId()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyIncorrectEmail("prasanna.inamdar@zenken.co.jp1", "DarkKnight2!");
	}
	
	@Test
	public void invalidLoginIncorrectPassword()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyIncorrectPass("prasanna.inamdar@zenken.co.jp", "DarkKnighT2!");
	}
	
	@Test
	public void invalidLoginInvalidId()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyInvalidEmail("Hey there", "DarkKnight2!");
	}
	
	@Test
	public void validLogin()
	{
		loginPage = new LoginPage(driver);
		loginPage.verifyLogIn("prasanna.inamdar@zenken.co.jp", "DarkKnight2!");
	}
	
	@AfterMethod
	public void terminate()
	{
		//driver.close();
		driver.quit();
	}
}
