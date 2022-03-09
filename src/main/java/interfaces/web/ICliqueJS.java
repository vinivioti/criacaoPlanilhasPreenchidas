package interfaces.web;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface ICliqueJS {
	static Logger logger = LogWeb.getLogger(ICliqueJS.class);
	long TIMEOUT = 40;

	default void clicarJavascript(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [clicarJavascript] com o elemento [%s].", elemento));
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			DriverWeb.getDriver().manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), TIMEOUT);
			executor.executeScript("arguments[0].click();",
					wait.until(ExpectedConditions.elementToBeClickable(elemento)));
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
			Assert.fail(
					LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao clicar no elemento:" + elemento);
			Assert.fail(LocalDateTime.now() + "erro ao clicar no elemento:" + elemento);
		}
	}
	
	default void clicarJavascript(WebElement elemento, String descricaoDoPasso) {
		try {
			logger.info(String.format("Realizar a ação do método [clicarJavascript] com o elemento [%s].", elemento));
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			DriverWeb.getDriver().manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), TIMEOUT);
			executor.executeScript("arguments[0].click();",
					wait.until(ExpectedConditions.elementToBeClickable(elemento)));
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
			Assert.fail(
					LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao clicar no elemento:" + elemento);
			Assert.fail(LocalDateTime.now() + "erro ao clicar no elemento:" + elemento);
		}
	}
	
	default void clicarJavascriptPorId(String id){
		try {
			logger.info(String.format("Realizar a ação do método [clicarJavascript] com o elemento de id [%s].", id));
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), 5);
			By elemento = By.id(id);
			executor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(elemento)));
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + id + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + id + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + id);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + id + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + id + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + id + "NAO esta visivel' em tela.");
		} catch (JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + id);
			Assert.fail(
					LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '" + id);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao clicar no elemento:" + id);
			Assert.fail(LocalDateTime.now() + "erro ao clicar no elemento:" + id);
		}
	}
}