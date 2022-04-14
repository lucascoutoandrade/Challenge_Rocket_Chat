package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	
	static WebDriver driver;
	
	WebDriverWait wait = new WebDriverWait(driver, 15);
	
	 @Rule
	 public TestName name= new TestName();

	@BeforeClass
	public static void setupTest() {

		// Seta path do driver executavel
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver_100.exe");

		// Adiciona argumentos para instanca do driver
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");

		// Atribui argumentos opcionais
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


		
		
		
	}
	
//	@Before
//	public void beforeEachTest() throws InterruptedException {
//
//
//	}
	
	@After
	public void afterEachTest() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//Convert webdriver for takescreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Create image file
		 File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		 //Copy file in folder below
		 FileUtils.copyFile(srcFile, new File("screenshots/"+name.getMethodName()+"_"+timeStamp+".png"));
		
	}
//
	@AfterClass
	public static void endsTests() {

		// Finish the driver
		driver.quit();

	}
	
	public boolean waitElementVisibility(By by) {
		//Wait element from conditions
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		return true;
		
	}

}
