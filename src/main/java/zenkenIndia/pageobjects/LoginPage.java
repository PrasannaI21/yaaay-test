package zenkenIndia.pageobjects;

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
	
	TopPage2 topPage2 = new TopPage2(driver);
	
	@FindBy(css="[type='submit']")
	WebElement logIn;
	
	@FindBy(xpath="//span[contains(.,'The email field is required.')]")
	WebElement idError;
	
	@FindBy(xpath="//span[contains(.,'The password field is required.')]")
	WebElement passError;
	
	@FindBy(css=".u-c-red.u-d-ib")
	WebElement matchRecord;
	
	@FindBy(id="email")
	WebElement emailField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(css=".u-mt-10.u-c-red.u-d-ib")
	WebElement emailError;
	
	@FindBy(linkText="Scouts")//TopPage2 class's element
	WebElement scoutss;
	
	public void clearFields()
	{
		emailField.clear();
		passwordField.clear();
	}
	
	public void verifyReqText() throws InterruptedException
	{
		logIn.click();
		waitUntilElementAppears(idError);
		waitUntilElementAppears(passError);
		String emailReq=idError.getText();
		Assert.assertEquals(emailReq, "The email field is required.");
		String passReq=passError.getText();
		Assert.assertEquals(passReq, "The password field is required.");
	}
	
	public void verifyEmailReqText(String password) throws InterruptedException
	{
		passwordField.sendKeys(password);//correct password
		logIn.click(); ;
		waitUntilElementAppears(idError);
		String emailReq = idError.getText();
		Assert.assertEquals(emailReq, "The email field is required.");
		//verify password note is not displayed
		Assert.assertFalse(isElementPresent(passError));
		
	}
	
	public void verifyPassReqText(String email) throws InterruptedException
	{
		emailField.sendKeys(email);
		logIn.click(); 
		waitUntilElementAppears(passError);
		String passReq = passError.getText();
		Assert.assertEquals(passReq, "The password field is required.");
		//verify email note is not displayed
		Assert.assertFalse(isElementPresent(idError));
		
	}
	
	public void verifyIncorrectEmail(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//incorrect email
		passwordField.sendKeys(password);//correct password
		logIn.click(); 
		waitUntilElementAppears(matchRecord);
		String text = matchRecord.getText();
		Assert.assertEquals(text, "These credentials do not match our records.");
	}
	
	public void verifyInvalidEmail(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//invalid email
		passwordField.sendKeys(password);//correct password
		logIn.click(); 
		waitUntilElementAppears(emailError);
		String text = emailError.getText();
		Assert.assertEquals(text, "The email must be a valid email address.");
		
	}
	
	public void verifyIncorrectPass(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//correct email
		passwordField.sendKeys(password);//incorrect password
		logIn.click();
		waitUntilElementAppears(matchRecord);
		String text = matchRecord.getText();
		Assert.assertEquals(text, "These credentials do not match our records.");
	}
	
	public void verifyLogIn(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//correct email
		passwordField.sendKeys(password);//correct password
		logIn.click();
//		Thread.sleep(2000);
//		WebElement iconEle = scoutss;
		waitUntilElementAppears(scoutss);
		Assert.assertTrue(isElementPresent(scoutss));
		
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
