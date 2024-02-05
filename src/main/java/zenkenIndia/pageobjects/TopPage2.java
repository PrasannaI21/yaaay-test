package zenkenIndia.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import zenkenIndia.abstractcomponents.AbstractComponents;

public class TopPage2 extends AbstractComponents{
	
	//TopPage after login
	WebDriver driver;

	public TopPage2(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[alt='Account icon']")
	WebElement icon;
	
	@FindBy(linkText="Scouts")
	WebElement scouts;
	
}
