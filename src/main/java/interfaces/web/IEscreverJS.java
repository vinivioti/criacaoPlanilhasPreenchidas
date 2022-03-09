package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IEscreverJS {
	static Logger logger = LogWeb.getLogger(IEscreverJS.class);

	
	default void escreverJavascript(By elemento, String texto) {
		try {
			logger.info(
					String.format("Realizar a ação do método [escreverJavascript] no elemento [%s] e texto [%s].", elemento, texto));
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			executor.executeScript("arguments[0].value=\"" + texto + "\";", DriverWeb.getDriver().findElement(elemento));
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}catch(JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
		}
	}
	
	default void escreverJavascript(WebElement elemento, String texto) {
		try {
			logger.info(
					String.format("Realizar a ação do método [escreverJavascript] no elemento [%s] e texto [%s].", elemento, texto));
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			executor.executeScript("arguments[0].value=\"" + texto + "\";", elemento);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}catch(JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '" + elemento);
		}
	}
}