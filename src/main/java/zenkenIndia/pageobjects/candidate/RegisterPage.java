package zenkenIndia.pageobjects.candidate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zenkenIndia.abstractcomponents.AbstractComponents;

public class RegisterPage extends AbstractComponents{
	
	WebDriver driver;

	public RegisterPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="firstName")
	WebElement firstName;
	
	@FindBy(id="lastName")
	WebElement lastName;
	
	@FindBy(id="nationality")
	WebElement nationality;
	
	@FindBy(id="currentLocation")
	WebElement location;
	
	@FindBy(id="currentLocationCity")
	WebElement city;
	
	@FindBy(css="[value*='already']")
	public WebElement radio1;
	
	@FindBy(css="[value='no']")
	public WebElement radio2;
	
	@FindBy(css="[value='unsure']")
	public WebElement radio3;
	
	@FindBy(id="email")
	WebElement id;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="terms")
	WebElement terms;
	
	@FindBy(css="[type='submit']")
	WebElement submit;
	
	@FindBy(xpath="//span[contains(.,'first')]")
	public WebElement fNameError;
	
	@FindBy(xpath="//span[contains(.,'last')]")
	public WebElement lNameError;
	
	@FindBy(xpath="//span[contains(.,'email')]")
	public WebElement idError;
	
	@FindBy(xpath="//span[contains(.,'pass')]")
	WebElement passError;
	
	@FindBy(xpath="//span[contains(.,'terms')]")
	public WebElement termsError;
	
	@FindBy(xpath="//span[contains(.,'valid')]")
	WebElement invalidId;
	
	@FindBy(xpath="//span[contains(.,'already')]")
	WebElement existingId;
	
	@FindBy(xpath="//span[contains(.,'The first name must')]")
	WebElement nameExceed;
	
	@FindBy(xpath="//span[contains(.,'The last name must')]")
	WebElement lastNameExceed;
	
	@FindBy(xpath="//p[contains(.,'current')]")
	WebElement cityExceed;
	
	@FindBy(xpath="//span[contains(.,'8')]")
	WebElement invalidPass1;
	
	@FindBy(xpath="//span[contains(.,'number')]")
	WebElement invalidPass2;
	
	@FindBy(xpath="//span[contains(.,'upper')]")
	WebElement invalidPass3;
	
	@FindBy(xpath="//span[contains(.,'symbol')]")
	WebElement invalidPass4;
	
	@FindBy(css="[class*='-xlarge']")
	public WebElement verifyEmail;//VerifyEmail class's element
	
	public void sendReqInfo(String name, String lName)
	{
		firstName.sendKeys(name);
		lastName.sendKeys(lName);
		clickByJavascript(terms);;
	}
	
	public void goTo()
	{
		String url = formUrl("/register");
		driver.get(url);
	}
	
	public List<String> getErrorTexts() throws InterruptedException
	{
		clickByJavascript(submit);
		List<WebElement> elements = Arrays.asList(fNameError, lNameError, idError, passError, termsError);
		List<String> elementTexts = new ArrayList<>();
		for(WebElement element:elements)
		{
			waitUntilElementAppears(element);
			String text = element.getText();
			elementTexts.add(text);
		}
		return elementTexts;
	}
	
	public List<String> getCharExceedTexts(String text1, String text2, String text3)
	{
		firstName.sendKeys(text1);
		lastName.sendKeys(text2);
		city.sendKeys(text3);
		clickByJavascript(submit);
		List<WebElement> elements = Arrays.asList(nameExceed, lastNameExceed, cityExceed);
		List<String> elementTexts = new ArrayList<>();
		for(WebElement element:elements)
		{
			waitUntilElementAppears(element);
			String text = element.getText();
			elementTexts.add(text);
		}
		return elementTexts;
	}
	
	public List<String> selectDropdowns(String text1, String text2)
	{
		String actText1 = selectDropdown(nationality, text1);
		String actText2 = selectDropdown(location, text2);
		List<String> texts = new ArrayList<>();
		texts.add(actText1);
		texts.add(actText2);
		return texts;
	}
	
	public void selectYes()
	{
		clickByJavascript(radio1);
	}
	
	public void selectNo()
	{
		clickByJavascript(radio2);
	}
	
	public void selectUnsure()
	{
		clickByJavascript(radio3);
	}
	
	public String getInvalidEmailText(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(invalidId);
		String text = invalidId.getText();
		return text;
	}
	
	public String getExistingEmailText(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(existingId);
		String text = existingId.getText();
		return text;
	}
	
	public String getIncorrectPassText1(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(invalidPass1);
		String text = invalidPass1.getText();
		return text;
	}
	
	public String getIncorrectPassText2(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(invalidPass2);
		String text = invalidPass2.getText();
		return text;
	}
	
	public String getIncorrectPassText3(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(invalidPass3);
		String text = invalidPass3.getText();
		return text;
	}
	
	public String getIncorrectPassText4(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(invalidPass4);
		String text = invalidPass4.getText();
		return text;
	}
	
	public void verifyRegistration(String email, String pass)
	{
		sendReqInfo("Prasanna", "Inamdar");
		id.sendKeys(email);
		password.sendKeys(pass);
		clickByJavascript(submit);
		waitUntilElementAppears(verifyEmail);
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
