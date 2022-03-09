package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IMover extends IMoverJS {
	static Logger logger = LogWeb.getLogger(IMover.class);

	/**
	 * @param elemento
	 */
	default void moverParaOelemento(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaOelemento] com o elemento [%s].", elemento));	
			Actions action = new Actions(DriverWeb.getDriver());
			action.moveToElement(DriverWeb.getDriver().findElement(elemento)).build().perform();		
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default void moverParaOelemento(WebElement elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaOelemento] com o elemento [%s].", elemento));
			Actions action = new Actions(DriverWeb.getDriver());	
			action.moveToElement(elemento).build().perform();
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default Boolean moverParaElementoeClicar(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaElementoEClicar] com o elemento [%s].", elemento));
			Actions action = new Actions(DriverWeb.getDriver());
			action.moveToElement(DriverWeb.getDriver().findElement(elemento)).click().build().perform();	
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
		return true;
	}

	default void moverParaElementoeClicar(WebElement elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaElementoEClicar] com o elemento [%s].", elemento));
			Actions action = new Actions(DriverWeb.getDriver());
			action.moveToElement(elemento).click().perform();	
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default void manterElementoPressionado(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [manterElementoPressionado] com o elemento [%s].", elemento));
			Actions action = new Actions(DriverWeb.getDriver());
			action.clickAndHold(DriverWeb.getDriver().findElement(elemento)).perform();		
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default void scrollToWebElement(By elemento) {
		logger.info(String.format("Realizar a ação do método [scrollToWebElement] com o elemento [%s].", elemento));
		WebElement element = DriverWeb.getDriver().findElement(elemento);
		scrollToWebElement(element);	
	}

	default void scrollToWebElement(WebElement elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [scrollToWebElement] com o elemento [%s].", elemento));
			Actions action = new Actions(DriverWeb.getDriver());
			action.moveToElement(elemento).perform();	
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default void moverParaElementoeEscreve(WebElement element, String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [moverParaElementoEEscreve] com o elemento [%s] informando o texto [%s].", element, texto));
			Actions actions = new Actions(DriverWeb.getDriver());
			actions.moveToElement(element);
			actions.click();
			actions.pause(10);
			actions.sendKeys(texto);
			actions.build().perform();
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + element.toString() + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + element.toString()
					+ "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + element.toString());
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + element.toString()
					+ "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + element.toString() + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + element.toString() + "NAO esta visivel' em tela.");
		}

	}

}