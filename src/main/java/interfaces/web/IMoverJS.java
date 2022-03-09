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
import org.openqa.selenium.support.events.EventFiringWebDriver;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IMoverJS {
	
	static Logger logger = LogWeb.getLogger(IMoverJS.class);
	
	default Boolean moverParaOelementoJavascript(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaOelementoJavascript] com o elemento [%s].", elemento));	
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", DriverWeb.getDriver().findElement(elemento));	
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
		}
		return true;
	}

	default void moverParaOelementoJavascript(WebElement elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaOelementoJavascript] com o elemento [%s].", elemento));	
			JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", elemento);	
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
		}
	}

	default void scrollJavaScript(int scroll) {
		try {
			logger.info(String.format("Realizar a ação do método [scrollJavaScript] com a quantidae %d. de pixel", scroll));
			JavascriptExecutor jse = (JavascriptExecutor) DriverWeb.getDriver();
			jse.executeScript("window.scrollBy(0," + scroll + ")", "");				
		} catch (Exception e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao de scroll: '");
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar a acao dde scroll: '");
		}
	}

	default void scrollInsideWebelement(String executeScript) {
		try {
			logger.info(String.format("Realizar a ação do método [scrollInsideWebelement] com o script [%s]", executeScript));
			EventFiringWebDriver event = new EventFiringWebDriver(DriverWeb.getDriver());
			event.executeScript(executeScript);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao de scrollInsideWebelement : '");
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar a acao scrollInsideWebelement: '");
		}		
	}

	default void scrollInsideWebelement(String executeScript, WebElement element) {
		try {
			logger.info(String.format("Realizar a ação do método [scrollInsideWebelement] com o script [%s]", executeScript));
			EventFiringWebDriver event = new EventFiringWebDriver(DriverWeb.getDriver());
			event.executeScript(executeScript, element);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao de scrollInsideWebelement : '");
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar a acao scrollInsideWebelement: '");
		}
	}
}