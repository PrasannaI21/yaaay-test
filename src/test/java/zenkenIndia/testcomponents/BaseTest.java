package zenkenIndia.testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import zenkenIndia.pageobjects.candidate.LoginPage;
import zenkenIndia.pageobjects.candidate.TopPage;
import zenkenIndia.pageobjects.company.CLoginPage;

public class BaseTest {
	
	public WebDriver driver;
	TopPage topPage;
	public LoginPage loginPage;
	public CLoginPage cLoginPage;

	@SuppressWarnings("deprecation")
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fIS = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\zenkenIndia\\resources\\GlobalData.properties");
		prop.load(fIS);
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox code
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge code
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(groups="candidateLogin")
	public LoginPage launchCandidateApp() throws IOException
	{
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		topPage = new TopPage(driver);
		topPage.acceptCookies();
		return loginPage;
	}
	
	@BeforeMethod(groups="companyLogin")
	public CLoginPage launchCompanyApp() throws IOException
	{
		driver = initializeDriver();
		cLoginPage = new CLoginPage(driver);
		cLoginPage.goTo();
		return cLoginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void terminate()
	{
		//driver.close();
		driver.quit();
	}
}
