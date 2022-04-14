package tests;

import java.util.Random;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChallengeRocketChatTest extends BaseTest {
	
	private String capturedText = null;
	private String expectedText = null;
	private Random random = new Random();
	
	
	@Test
	public void a_mustLoginIn() throws InterruptedException {
		
    expectedText = "Bem vindo, Lucas!";
    
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
	
	capturedText = driver.findElement(By.cssSelector("body > div.alert.alert-success")).getText();
	
	// checkpoint
	Assert.assertEquals(expectedText, capturedText);
			
	}
	
	@Test
	public void b_mustCreateAccount() {
		
		 expectedText = "Conta adicionada com sucesso!";
	
		// Click on Contas
		driver.findElement(By.xpath("//*[contains(text(),'Contas')]")).click();
		
		// Select Adicionar
		driver.findElement(By.xpath("//*[contains(text(),'Adicionar')]")).click();
		
		driver.findElement(By.xpath("//*[@name='nome']")).sendKeys("TESTE "+random.nextInt(9999));
		
		driver.findElement(By.xpath("//*[contains(text(),'Salvar')]")).click();
		
		capturedText = driver.findElement(By.cssSelector(".alert")).getText();
		
		Assert.assertEquals(expectedText, capturedText);
	}
	
	@Test
	public void c_mustCreateMovement() {
		
		 expectedText = "Movimentação adicionada com sucesso!";
		
		// Click on Movimentação
		driver.findElement(By.xpath("//a[contains(@href,'movimentacao')]")).click();
		
		// Write Data de movimentacao
		driver.findElement(By.xpath("//*[@name='data_transacao']")).sendKeys("20/01/2021");
		// Write Data do pagamento
		driver.findElement(By.xpath("//*[@name='data_pagamento']")).sendKeys("14/04/2022");
		// Write Descricao
		driver.findElement(By.xpath("//*[@name='descricao']")).sendKeys("Teste"+random.nextInt(9999));
		// Write Interessado
		driver.findElement(By.xpath("//*[@name='interessado']")).sendKeys("Teste");
		// Write Interessado
		driver.findElement(By.xpath("//*[@name='valor']")).sendKeys("10000");
		
		// Click on Salvar
		driver.findElement(By.xpath("//*[contains(text(),'Salvar')]")).click();
		
		capturedText = driver.findElement(By.cssSelector(".alert")).getText();
		
		// Checkpoint
		Assert.assertEquals(expectedText, capturedText);
		
	}
	
	@Test
	public void d_mustRemoveMovement() {
		
		 expectedText = "Movimentação removida com sucesso!";
		
		// Click on Movimentação
		driver.findElement(By.xpath("//a[contains(@href,'extrato')]")).click();
		
		// Click on Remove button
		driver.findElement(By.xpath("//a[contains(@href,'removerMovimentacao')]")).click();
		
	    capturedText = driver.findElement(By.cssSelector(".alert")).getText();
		
		// Checkpoint
		Assert.assertEquals(expectedText, capturedText);
		
	}
	
	@Test
	public void e_mustLogout() {
		
		
		// Click on Movimentação
		driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();
		
		Assert.assertTrue(waitElementVisibility(By.xpath("//*[@id='email']")));
		
		
	}

}
