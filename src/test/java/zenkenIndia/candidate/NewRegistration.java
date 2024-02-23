package zenkenIndia.candidate;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import zenkenIndia.testcomponents.BaseTest;

public class NewRegistration extends BaseTest{

	List<String> expectedTexts = Arrays.asList(
			"The first name field is required.",
			"The last name field is required.",
			"The email field is required.",
			"The password field is required.",
			"You must check the box stating that you have read and understood the terms and conditions."
	);
	
	List<String> expectedTexts2 = Arrays.asList(
			"The first name must not be greater than 200 characters.",
			"The last name must not be greater than 200 characters.",
			"The current location city must not be greater than 200 characters."
	);
	
	String exceedText = "Get instant access to all job listings and moreGet instant access to all job listings "
			+ "and moreGet instant access to all job listings and moreGet instant access to all job listings and moreGet instant a";
	
	@Test(groups="register")
	public void invalidRegBlankFields() throws InterruptedException
	{
		List<String> elementTexts = registerPage.getErrorTexts();
		for(int i=0;i<elementTexts.size();i++)
		{
			String actualText = elementTexts.get(i);
			String expectedText = expectedTexts.get(i);
			Assert.assertEquals(actualText, expectedText);
		}
	}
	
	@Test(groups="register")
	public void invalidRegExceedFields()
	{
		List<String> elementTexts = registerPage.getCharExceedTexts(exceedText, exceedText, exceedText);
		for(int i=0;i<elementTexts.size();i++)
		{
			String actualText = elementTexts.get(i);
			String expectedText = expectedTexts2.get(i);
			Assert.assertEquals(actualText, expectedText);
		}
	}
	
	@Test(groups="register")
	public void verifyDropdowns()
	{
		List<String> texts = registerPage.selectDropdowns("Iraqi", "Norway");
		Assert.assertEquals(texts.get(0), "Iraqi");
		Assert.assertEquals(texts.get(1), "Norway");
	}
	
	@Test(groups="register")
	public void verifyRadioButtons()
	{
		registerPage.selectYes();
		Assert.assertTrue(registerPage.radio1.isSelected());
		registerPage.selectNo();
		Assert.assertTrue(registerPage.radio2.isSelected());
		registerPage.selectUnsure();
		Assert.assertTrue(registerPage.radio3.isSelected());
	}
	
	@Test(groups="register")
	public void invalidRegInvalidId()
	{
		String text = registerPage.getInvalidEmailText("Hey there", "Yaaay123@");
		Assert.assertEquals(text, "The email must be a valid email address.");
		WebElement ele1 = registerPage.fNameError;
		WebElement ele2 = registerPage.lNameError;
		WebElement ele3 = registerPage.termsError;
		Assert.assertFalse(registerPage.isElementPresent(ele1));
		Assert.assertFalse(registerPage.isElementPresent(ele2));
		Assert.assertFalse(registerPage.isElementPresent(ele3));
	}
	
	@Test(groups="register")
	public void invalidRegExistingEmail()
	{
		String text = registerPage.getExistingEmailText("prasanna.inamdar@zenken.co.jp", "Test@123");
		Assert.assertEquals(text, "The email has already been taken.");
	}
	
	@Test(groups="register")
	public void invalidRegInvalidPass1()
	{
		//8 Characters or More
		String text = registerPage.getIncorrectPassText1("prasanna.inamdar+user6@zenken.co.jp", "Yaaay@1");
		Assert.assertEquals(text, "The password must be at least 8 characters.");
		WebElement ele = registerPage.idError;
		Assert.assertFalse(registerPage.isElementPresent(ele));
	}
	
	@Test(groups="register")
	public void invalidRegInvalidPass2()
	{
		//Contains Number
		String text = registerPage.getIncorrectPassText2("prasanna.inamdar+user6@zenken.co.jp", "Yaaay@Yaaay");
		Assert.assertEquals(text, "The password field must contain at least one number.");
	}
	
	@Test(groups="register")
	public void invalidRegInvalidPass3()
	{
		//Contains Lowercase/ Uppercase
		String text = registerPage.getIncorrectPassText3("prasanna.inamdar+user6@zenken.co.jp", "yaaay@yaaay12");
		Assert.assertEquals(text, "The password field must contain at least one uppercase and one lowercase letter.");
	}
	
	@Test(groups="register")
	public void invalidRegInvalidPass4()
	{
		//Contains Special Character
		String text = registerPage.getIncorrectPassText4("prasanna.inamdar+user6@zenken.co.jp", "YaaayYaaay12");
		Assert.assertEquals(text, "The password field must contain at least one symbol.");
	}
	
	//Make sure the user with passed credentials doesn't exist (delete if so)
	@Test(groups="register")
	public void validRegistration()
	{
		registerPage.verifyRegistration("prasanna.inamdar+user6@zenken.co.jp", "Yaaay123@");
		WebElement ele = registerPage.verifyEmail;
		Assert.assertTrue(registerPage.isElementPresent(ele));
	}
	
	
	
	
	
	
	
	
}
