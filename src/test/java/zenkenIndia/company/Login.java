package zenkenIndia.company;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import zenkenIndia.testcomponents.BaseTest;

public class Login extends BaseTest{
	
	String expectedText1 = "メールアドレスは必須項目です。";
	String expectedText2 = "パスワードは必須項目です。";
	String expectedText3 = "メールアドレスまたはパスワードが正しくありません。";
	
	@Test(groups="companyLogin")
	public void invalidLoginBlankFields() throws InterruptedException
	{
		List<String> texts = cLoginPage.getReqText();
		Assert.assertEquals(texts.get(0), expectedText1);
		Assert.assertEquals(texts.get(1), expectedText2);
	}
	
	@Test(groups="companyLogin")
	public void invalidLoginIdBlank() throws InterruptedException
	{
		String text = cLoginPage.getEmailReqText("DarkKnight2!");
		Assert.assertEquals(text, expectedText1);
		//verify password note is not displayed
		WebElement ele = cLoginPage.passError;
		Assert.assertFalse(cLoginPage.isElementPresent(ele));
	}
	
	@Test(groups="companyLogin")
	public void invalidLoginPasswordBlank()
	{
		String text = cLoginPage.getPassReqText("prasanna.inamdar@zenken.co.jp");
		Assert.assertEquals(text, expectedText2);
		//verify email note is not displayed
		WebElement ele = cLoginPage.idError;
		Assert.assertFalse(cLoginPage.isElementPresent(ele));
	}
	
	@Test(groups="companyLogin")
	public void invalidLoginIncorrectId() throws InterruptedException
	{
		String text = cLoginPage.getIncorrectEmailText("prasanna.inamdar@zenken.co.jp1", "DarkKnight2!");
		Assert.assertEquals(text, expectedText3);
	}
	
	@Test(groups="companyLogin")
	public void invalidLoginIncorrectPassword()
	{
		String text = cLoginPage.getIncorrectPassText("prasanna.inamdar@zenken.co.jp", "DarkKnighT2!");
		Assert.assertEquals(text, expectedText3);
	}
	
	@Test(groups="companyLogin")
	public void invalidLoginInvalidId() throws InterruptedException
	{
		String text = cLoginPage.getInvalidEmailText("Hey there", "DarkKnight2!");
		Assert.assertEquals(text, "メールアドレスには、有効なメールアドレスを入力してください。");
	}
	
	@Test(groups="companyLogin")
	public void validLogin() throws InterruptedException
	{
		cLoginPage.verifyLogIn("prasanna.inamdar@zenken.co.jp", "DarkKnight2!");
		WebElement ele = cLoginPage.applications;
		Assert.assertTrue(cLoginPage.isElementPresent(ele));
	}

}
