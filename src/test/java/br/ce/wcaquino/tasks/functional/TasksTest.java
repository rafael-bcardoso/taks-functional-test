package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver loadWebDriverApplication() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.173:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.173:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = loadWebDriverApplication();
		
		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição
			driver.findElement(By.id("task")).sendKeys("Nova Descrição");
			
			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("31/01/2100");
			
			// Clicar em Salvar 
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRetornarErroParaInsercaoDeTodoComDataPassada() throws MalformedURLException {
		WebDriver driver = loadWebDriverApplication();
		
		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição
			driver.findElement(By.id("task")).sendKeys("Nova Descrição");
			
			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("31/01/2000");
			
			// Clicar em Salvar 
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally{
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRetornarErroParaInsercaoDeTodoSemDescricao() throws MalformedURLException {
		WebDriver driver = loadWebDriverApplication();
		
		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição
			driver.findElement(By.id("task")).sendKeys("");
			
			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("31/01/2000");
			
			// Clicar em Salvar 
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
			
		} finally{
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRetornarErroParaInsercaoDeTodoSemData() throws MalformedURLException {
		WebDriver driver = loadWebDriverApplication();
		
		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição
			driver.findElement(By.id("task")).sendKeys("Descrição de Test");
			
			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("");
			
			// Clicar em Salvar 
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
			
		} finally{
			// Fechar o browser
			driver.quit();
		}
	}
}
