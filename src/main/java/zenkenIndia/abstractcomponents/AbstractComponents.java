package zenkenIndia.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	public String username = "dspfuser";
	public String password = "FobaRm8.";
	public String domain = "stage.dspf-dev.com";
	public String url;
	
	public AbstractComponents(WebDriver diver)
	{
		this.driver=diver;
	}

	public void waitUntilElementAppears(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String formUrl(String subDomain)
	{
		url = "https://" + username + ":" + password + "@" + domain + subDomain;
		return url;
	}
}
