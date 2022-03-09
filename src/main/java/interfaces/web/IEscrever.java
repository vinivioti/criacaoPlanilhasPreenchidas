package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IEscrever extends IClique, IEscreverJS {
	static Logger logger = LogWeb.getLogger(IEscrever.class);

	default void escrever(By elemento, String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [escrever] no elemento [%s] e texto [%s].", elemento,
					texto));
			DriverWeb.getDriver().findElement(elemento).clear();
			DriverWeb.getDriver().findElement(elemento).sendKeys(Keys.END + Keys.chord(Keys.SHIFT, Keys.HOME));
			DriverWeb.getDriver().findElement(elemento).sendKeys(texto);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (InvalidElementStateException e) {
			logger.error(" -- FALHA: elemento: '" + elemento + "' NAO esta interativo na plataforma '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "' NAO esta interativo na plataforma '");
		}
	}

	default void superEscrever(By elemento, String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [superEscrever] no elemento [%s] e texto [%s].",
					elemento, texto));
			WebElement webElement = DriverWeb.getDriver().findElement(elemento);
			superClick(elemento);
			webElement.clear();
			webElement.sendKeys(texto);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (InvalidElementStateException e) {
			logger.error(" -- FALHA: elemento: '" + elemento + "' NAO esta interativo na plataforma '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "' NAO esta interativo na plataforma '");
		}
	}

	default void escreverUmaLetraPorVez(By elemento, String texto) {
		try {
			logger.info(
					String.format("Realizar a ação do método [escreverUmaLetraPorVez] no elemento [%s] e texto [%s].",
							elemento, texto));
			DriverWeb.getDriver().findElement(elemento).clear();
			for (int i = 0; i < texto.length(); i++) {
				char c = texto.charAt(i);
				String s = new StringBuilder().append(c).toString();
				DriverWeb.getDriver().findElement(elemento).sendKeys(s);
			}
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (InvalidElementStateException e) {
			logger.error(" -- FALHA: elemento: '" + elemento + "' NAO esta interativo na plataforma '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "' NAO esta interativo na plataforma '");
		}
	}

	default void escreverUmaLetraPorVez(WebElement elemento, String texto) {
		try {
			logger.info(
					String.format("Realizar a ação do método [escreverUmaLetraPorVez] no elemento [%s] e texto [%s].",
							elemento, texto));
			elemento.clear();
			for (int i = 0; i < texto.length(); i++) {
				char c = texto.charAt(i);
				String s = new StringBuilder().append(c).toString();
				(elemento).sendKeys(s);
			}
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (InvalidElementStateException e) {
			logger.error(" -- FALHA: elemento: '" + elemento + "' NAO esta interativo na plataforma '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "' NAO esta interativo na plataforma '");
		}
	}

	default void escrever(WebElement elemento, String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [escrever] no elemento [%s] e texto [%s].", elemento,
					texto));
			(elemento).sendKeys(Keys.END + Keys.chord(Keys.SHIFT, Keys.HOME));
			(elemento).sendKeys(texto);
		} catch (NoSuchElementException e) {
			logger.warn(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.warn(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.warn(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}
	
	default void escreverNoCampo(By elemento, String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [escrever] no elemento [%s] e texto [%s].", elemento,
					texto));

			DriverWeb.getDriver().findElement(elemento).sendKeys(texto);
			
		} catch (NoSuchElementException e) {
			logger.warn(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.warn(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.warn(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
	}
}
