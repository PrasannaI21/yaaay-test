package zenkenIndia.pageobjects.company;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import zenkenIndia.abstractcomponents.AbstractComponents;

public class CLoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CLoginPage(WebDriver driver)
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
	public WebElement idError;
	
	@FindBy(xpath="//span[contains(.,'パス')]")
	public WebElement passError;
	
	@FindBy(css="[role='alert']")
	WebElement incorrectIdOrPass;
	
	@FindBy(xpath="//span[contains(.,'有効')]")
	WebElement emailError;
	
	@FindBy (css=".u-fz-32.u-fw-500")
	public WebElement applications;//Applications class's element
	
	public void goTo()
	{
		String url = formUrl("/company/login");
		driver.get(url);
	}
	
	public List<String> getReqText()
	{
		logIn.click();
		waitUntilElementAppears(idError);
		waitUntilElementAppears(passError);
		List<String> texts = new ArrayList<>();
		texts.add(idError.getText());
		texts.add(passError.getText());
		return texts;
	}
	
	public String getEmailReqText(String password) 
	{
		passwordField.sendKeys(password);//send correct password
		logIn.click(); ;
		waitUntilElementAppears(idError);
		String emailReq = idError.getText();
		return emailReq;
	}
	
	public String getPassReqText(String email) 
	{
		emailField.sendKeys(email);//send correct email
		logIn.click(); 
		waitUntilElementAppears(passError);
		String passReq = passError.getText();
		return passReq;		
	}
	
	public String getIncorrectEmailText(String email, String password) 
	{
		emailField.sendKeys(email);//send incorrect email
		passwordField.sendKeys(password);//send correct password
		logIn.click(); 
		waitUntilElementAppears(incorrectIdOrPass);
		String text = incorrectIdOrPass.getText();
		return text;
	//	Assert.assertEquals(text, "メールアドレスまたはパスワードが正しくありません。");
	}
	
	public String getIncorrectPassText(String email, String password) 
	{
		emailField.sendKeys(email);//send correct email
		passwordField.sendKeys(password);//send incorrect password
		logIn.click();
		waitUntilElementAppears(incorrectIdOrPass);
		String text = incorrectIdOrPass.getText();
		return text;
//		Assert.assertEquals(text, "メールアドレスまたはパスワードが正しくありません。");
	}
	
	public String getInvalidEmailText(String email, String password)
	{
		emailField.sendKeys(email);//send invalid email
		passwordField.sendKeys(password);//send correct password
		logIn.click(); 
		waitUntilElementAppears(emailError);
		String text = emailError.getText();
		return text;
//		Assert.assertEquals(text, "メールアドレスには、有効なメールアドレスを入力してください。");
		
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
	public boolean isElementPresent(WebElement element)
	{
		try {
			return element.isDisplayed();
			
		}catch(Exception e){
			return false;
		}
	}
}
