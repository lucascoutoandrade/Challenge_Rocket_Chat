package tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChallengeRocketChatTest extends BaseTest {
	
	String capturedText = null;
	String expectedText = "Bem vindo, Lucas!";
	
	@Test
	public void a_mustLoginIn() {
	
	capturedText = driver.findElement(By.cssSelector("body > div.alert.alert-success")).getText();
	System.out.println("Captured text: "+capturedText);
	// checkpoint
	Assert.assertEquals(expectedText, capturedText);
			
	}
	
	@Test
	public void b_mustCreateAccount() {
		
		
	}
	
	@Test
	public void c_mustCreateMovement() {}
	
	@Test
	public void d_mustVerifyMonthlySummary() {}
	
	@Test
	public void e_mustLogout() {}

}
