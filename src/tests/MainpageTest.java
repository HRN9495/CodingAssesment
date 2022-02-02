package tests;

import org.testng.annotations.Test;

import data.DataFile;
import pages.MainPage;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class MainpageTest {
	WebDriver driver;
	MainPage mp = new MainPage();
	DataFile df = new DataFile();

	@BeforeMethod
	public void beforeMethod() throws IOException {
		mp.openBrowser();
		mp.openurl();

	}

	@AfterMethod
	public void afterMethod() {
		mp.closebrowser();
	}

	@Test(priority = 1)
	public void validateRepo() throws InterruptedException {
		mp.searchRepo();
		Assert.assertEquals(mp.repoCount(), 1);
		Assert.assertEquals(mp.repoName.getText(), "mvoloskov/decider");
		mp.repoName();
		mp.readMe300Char();
	}

}
