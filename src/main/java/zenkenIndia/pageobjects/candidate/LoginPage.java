package zenkenIndia.pageobjects.candidate;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	public WebElement idError;
	
	@FindBy(xpath="//span[contains(.,'The password field is required.')]")
	public WebElement passError;
	
	@FindBy(css=".u-c-red.u-d-ib")
	WebElement matchRecord;
	
	@FindBy(id="email")
	WebElement emailField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(css=".u-mt-10.u-c-red.u-d-ib")
	WebElement emailError;
	
	@FindBy(linkText="Scouts")//TopPage2 class's element
	public WebElement scoutss;
	
	public void goTo()
	{
		String url = formUrl("/login");
		driver.get(url);
	}
	
	public List<String> getReqText() throws InterruptedException
	{
		logIn.click();
		waitUntilElementAppears(idError);
		waitUntilElementAppears(passError);
		List<String> texts = new ArrayList<>();
		texts.add(idError.getText());
		texts.add(passError.getText());
		return texts;
	}
	
	public String getEmailReqText(String password) throws InterruptedException
	{
		passwordField.sendKeys(password);//send correct password
		logIn.click(); ;
		waitUntilElementAppears(idError);
		String emailReq = idError.getText();
		return emailReq;
	}
	
	public String getPassReqText(String email) throws InterruptedException
	{
		emailField.sendKeys(email);//send correct email
		logIn.click(); 
		waitUntilElementAppears(passError);
		String passReq = passError.getText();
		return passReq;
	}
	
	public String getIncorrectEmailText(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//incorrect email
		passwordField.sendKeys(password);//correct password
		logIn.click(); 
		waitUntilElementAppears(matchRecord);
		String text = matchRecord.getText();
		return text;
	}
	
	public String getIncorrectPassText(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//correct email
		passwordField.sendKeys(password);//incorrect password
		logIn.click();
		waitUntilElementAppears(matchRecord);
		String text = matchRecord.getText();
		return text;
	}
	
	public String getInvalidEmailText(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//invalid email
		passwordField.sendKeys(password);//correct password
		logIn.click(); 
		waitUntilElementAppears(emailError);
		String text = emailError.getText();
		return text;
	}
	
	public void verifyLogIn(String email, String password) throws InterruptedException
	{
		emailField.sendKeys(email);//correct email
		passwordField.sendKeys(password);//correct password
		logIn.click();
		waitUntilElementAppears(scoutss);
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
