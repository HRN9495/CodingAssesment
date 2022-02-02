package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.List;
//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import data.DataFile;

public class MainPage {
	WebDriver driver;
	Properties prop12;
	DataFile df = new DataFile();
	@FindBy(name = "q")
	public WebElement searchBox;
	@FindBy(xpath = "//a[contains(.,'Advanced search')]")
	public WebElement advanceSearch;
	@FindBy(id = "search_language")
	public WebElement language;
	@FindBy(id = "search_stars")
	public WebElement searchStar;
	@FindBy(id = "search_followers")
	public WebElement searchFollowers;
	@FindBy(id = "search_state")
	public WebElement searchState;
	@FindBy(id = "search_license")
	public WebElement license;
	@FindBy(xpath = "(//.[contains(text(),'  Search')])[6]")
	public WebElement search;
	@FindBy(xpath = "//ul[@class='repo-list']/li")
	public List<WebElement> repoCount;
	@FindBy(xpath = "//ul[@class='repo-list']//a")
	public WebElement repoName;
	@FindBy(xpath = "//a[contains(.,'README.md')]")
	public WebElement readMe;
	@FindBy(id = "raw-url")
	public WebElement rawData;

	public void openBrowser() throws IOException {
		FileInputStream f = new FileInputStream(
				"D:\\Software Engineer\\Eclipse Workspace\\BasicFramework57\\src\\utilities\\prop12.properties");
		Properties prop12 = new Properties();
		prop12.load(f);
		String Browser = prop12.getProperty("browser");

		if (Browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Software Engineer\\SeleniumJars\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Software Engineer\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.safaridriver", "D:\\Software Engineer\\SeleniumJars\\safaridriver(2).exe");
			driver = new SafariDriver();
		}
		PageFactory.initElements(driver, this);
	}

	public void openurl() {

		driver.get("https://github.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void closebrowser() {
		driver.quit();
	}

	public void searchRepo() throws InterruptedException {
		searchBox.sendKeys("react" + Keys.ENTER);
        advanceSearch.click();
		Select objSelect;
		objSelect = new Select(language);
		objSelect.selectByVisibleText("JavaScript");
		searchStar.sendKeys(">45");
		objSelect = new Select(license);
		objSelect.selectByVisibleText("Boost Software License 1.0");
		objSelect = new Select(searchState);
		objSelect.selectByVisibleText("closed");
		searchFollowers.sendKeys(">50");
		search.click();

	}

	public int repoCount() throws InterruptedException {

		System.out.println("List size is: " + repoCount.size());
		return repoCount.size();
	}

	public void repoName() throws InterruptedException {
		
		repoName.click();
		readMe.click();

	}

	public void readMe300Char() throws InterruptedException {
		
		String text = driver.findElement(By.xpath("//div[@id='readme']/article")).getText();
		String count1 = text.substring(0, Math.min(text.length(), 300));
		System.out.println(count1);

	}

}
