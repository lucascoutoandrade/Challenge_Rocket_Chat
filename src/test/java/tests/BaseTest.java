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
	
	@Before
	public void beforeEachTest() throws InterruptedException {

		// Acess the url
		driver.get("https://seubarriga.wcaquino.me/");
		Thread.sleep(2000);

		// set email and password
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("lucascouto.ads@gmail.com");
		driver.findElement(By.xpath("//*[@id='senha']")).sendKeys("88045630");
		
		// click on Enviar button
		driver.findElement(By.xpath("//*[contains(text(),'Entrar')]")).click();
		
		// get the text from welcome
		waitElementVisibility(By.cssSelector("body > div.alert.alert-success"));

	}
	
	@After
	public void depoisCadaTest() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println(timeStamp);
		
		//Convert webdriver for takescreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Create image file
		 File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		 //Copy file in folder below
		 FileUtils.copyFile(srcFile, new File("screenshots/"+name.getMethodName()+"_"+timeStamp+".png"));
		 
	}

	@AfterClass
	public static void finaliza() {

		// Finish the driver
		driver.close();

	}
	
	public void waitElementVisibility(By by) {
		//Wait element from conditions
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		
	}

}
