package zenkenIndia.pageobjects.candidate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zenkenIndia.abstractcomponents.AbstractComponents;

public class TopPage extends AbstractComponents {

	//TopPage before login
	WebDriver driver;

	String username = "dspfuser";
	String password = "FobaRm8.";
	String domain = "stage.dspf-dev.com";
	String url;

	public TopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "onetrust-accept-btn-handler")
	WebElement accCookie;

	@FindBy(linkText = "Log In")
	WebElement logIn;

	public void goTo() {
		url = "https://" + username + ":" + password + "@" + domain;
		driver.get(url);
	}

	public void acceptCookies() {
		waitUntilElementAppears(accCookie);
		accCookie.click();
	}

	public void logIn() {
		logIn.click();
	}

}
