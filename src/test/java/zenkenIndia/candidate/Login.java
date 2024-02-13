package zenkenIndia.candidate;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import zenkenIndia.testcomponents.BaseTest;

public class Login extends BaseTest{
	
	String expectedText1 = "The email field is required.";
	String expectedText2 = "The password field is required.";
	String expectedText3 = "These credentials do not match our records.";
		
	@Test(groups="candidateLogin")
	public void invalidLoginBlankFields() throws InterruptedException, IOException
	{
		List<String> texts = loginPage.getReqText();
		Assert.assertEquals(texts.get(0), expectedText1);
		Assert.assertEquals(texts.get(1), expectedText2);
	}
	
	@Test(groups="candidateLogin")
	public void invalidLoginIdBlank() throws InterruptedException
	{
		String text = loginPage.getEmailReqText("Test123^");
		Assert.assertEquals(text, expectedText1);
		//verify password note is not displayed
		WebElement ele = loginPage.passError;
		Assert.assertFalse(loginPage.isElementPresent(ele));
	}
	
	@Test(groups="candidateLogin")
	public void invalidLoginPasswordBlank() throws InterruptedException
	{
		String text = loginPage.getPassReqText("prasanna.inamdar@zenken.co.jp");
		Assert.assertEquals(text, expectedText2);
		//verify email note is not displayed
		WebElement ele = loginPage.idError;
		Assert.assertFalse(loginPage.isElementPresent(ele));
	}
	
	@Test(groups="candidateLogin")
	public void invalidLoginIncorrectId() throws InterruptedException
	{
		String text = loginPage.getIncorrectEmailText("prasanna.inamdar@zenken.co.jp1", "Test123^");
		Assert.assertEquals(text, expectedText3);
	}
	
	@Test(groups="candidateLogin")
	public void invalidLoginIncorrectPassword() throws InterruptedException
	{
		String text = loginPage.getIncorrectPassText("prasanna.inamdar@zenken.co.jp", "test123^");
		Assert.assertEquals(text, expectedText3);
	}
	
	@Test(groups="candidateLogin")
	public void invalidLoginInvalidId() throws InterruptedException
	{
		String text = loginPage.getInvalidEmailText("Hey there", "Test123^");
		Assert.assertEquals(text, "The email must be a valid email address.");
	}
	
	@Test(groups="candidateLogin")
	public void validLogin() throws InterruptedException
	{
		loginPage.verifyLogIn("prasanna.inamdar@zenken.co.jp", "Test123^");
		WebElement ele = loginPage.scoutss;
		Assert.assertTrue(loginPage.isElementPresent(ele));
	}
		
	}

