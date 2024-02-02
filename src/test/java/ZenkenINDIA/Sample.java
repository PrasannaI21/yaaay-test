package ZenkenINDIA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import zenkenIndia.pageobjects.LoginPage;
import zenkenIndia.pageobjects.TopPage;
import zenkenIndia.pageobjects.TopPage2;

public class Sample{
	
	WebDriver driver = new ChromeDriver();
	
	@Test
	public void invalidLoginIdBlank() throws InterruptedException
	{
		TopPage topPage = new TopPage(driver);
		topPage.goTo();
		Thread.sleep(3000);
		topPage.acceptCookies();
		topPage.logIn();
		Thread.sleep(1000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.verifyReqText();
		loginPage.verifyEmailReqText();
		loginPage.verifyPassReqText();
		loginPage.verifyIncorrectEmail();
		loginPage.verifyInvalidEmail();
		loginPage.verifyIncorrectPass();
		loginPage.verifyLogIn();
	}
	
	@Test
	public void invalidLoginBlank() throws InterruptedException
	{
		TopPage topPage = new TopPage(driver);
		topPage.goTo();
		Thread.sleep(3000);
		topPage.acceptCookies();
		topPage.logIn();
		Thread.sleep(1000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.verifyLogIn();
		
	}
	
	@Test
	public void terminate()
	{
		driver.close();
	}
		
	}

