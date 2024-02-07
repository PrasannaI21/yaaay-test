package zenkenIndia.pageobjects.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import zenkenIndia.abstractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="email")
	WebElement emailField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(css="[type='submit']")
	WebElement logIn;
	
	@FindBy(xpath="//span[contains(.,'メール')]")
	WebElement idError;
	
	@FindBy(xpath="//span[contains(.,'パス')]")
	WebElement passError;
	
	@FindBy(css="[role='alert']")
	WebElement incorrectIdOrPass;
	
	@FindBy(xpath="//span[contains(.,'有効')]")
	WebElement emailError;
	
	@FindBy (css=".u-fz-32.u-fw-500")
	WebElement applications;
	
	public void verifyReqText()
	{
		logIn.click();
		waitUntilElementAppears(idError);
		waitUntilElementAppears(passError);
		String emailReq = idError.getText();
		Assert.assertEquals(emailReq, "メールアドレスは必須項目です。");
		String passReq = passError.getText();
		Assert.assertEquals(passReq, "パスワードは必須項目です。");
	}
	
	public void verifyEmailReqText(String password) 
	{
		passwordField.sendKeys(password);//send correct password
		logIn.click(); ;
		waitUntilElementAppears(idError);
		String emailReq = idError.getText();
		Assert.assertEquals(emailReq, "メールアドレスは必須項目です。");
		//verify password note is not displayed
		Assert.assertFalse(isElementPresent(passError));
		
	}
	
	public void verifyPassReqText(String email) 
	{
		emailField.sendKeys(email);//send correct email
		logIn.click(); 
		waitUntilElementAppears(passError);
		String passReq = passError.getText();
		Assert.assertEquals(passReq, "パスワードは必須項目です。");
		//verify email note is not displayed
		Assert.assertFalse(isElementPresent(idError));
		
	}
	
	public void verifyIncorrectEmail(String email, String password) 
	{
		emailField.sendKeys(email);//send incorrect email
		passwordField.sendKeys(password);//send correct password
		logIn.click(); 
		waitUntilElementAppears(incorrectIdOrPass);
		String text = incorrectIdOrPass.getText();
		Assert.assertEquals(text, "メールアドレスまたはパスワードが正しくありません。");
	}
	
	public void verifyInvalidEmail(String email, String password)
	{
		emailField.sendKeys(email);//send invalid email
		passwordField.sendKeys(password);//send correct password
		logIn.click(); 
		waitUntilElementAppears(emailError);
		String text = emailError.getText();
		Assert.assertEquals(text, "メールアドレスには、有効なメールアドレスを入力してください。");
		
	}
	
	public void verifyIncorrectPass(String email, String password) 
	{
		emailField.sendKeys(email);//send correct email
		passwordField.sendKeys(password);//send incorrect password
		logIn.click();
		waitUntilElementAppears(incorrectIdOrPass);
		String text = incorrectIdOrPass.getText();
		Assert.assertEquals(text, "メールアドレスまたはパスワードが正しくありません。");
	}
	
	public void verifyLogIn(String email, String password) 
	{
		emailField.sendKeys(email);//correct email
		passwordField.sendKeys(password);//correct password
		logIn.click();
		waitUntilElementAppears(applications);
		Assert.assertTrue(isElementPresent(applications));
	}
	
	//utility method to check the presence of an element
	boolean isElementPresent(WebElement element)
	{
		try {
			return element.isDisplayed();
			
		}catch(Exception e){
			return false;
		}
	}
}
