package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IObter {
	static Logger logger = LogWeb.getLogger(IObter.class);

	default String obterTexto(By elemento) {
		String retorno = null;
		try {
			logger.info(String.format("Realizar a ação do método [obterTexto] com elemento [%s].", elemento));
			retorno = DriverWeb.getDriver().findElement(elemento).getText();
			logger.info(String.format("Obteve o texto [%s].", retorno));
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
		return retorno;
	}

	default String obterTexto(WebElement elemento) {
		String retorno = null;
		try {
			logger.info(String.format("Realizar a ação do método [obterTexto] com elemento [%s].", elemento));
			retorno = (elemento).getText();
			logger.info(String.format("Obteve o texto [%s].", retorno));
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
		return retorno;
	}

	default String obterValorCss(By elemento, String elementoCss) {
		String retorno = null;
		try {
			logger.info(String.format("Realizar a ação do método [obterValorCss] com elemento [%s] e o valor [%s].", elemento, elementoCss));
			retorno = DriverWeb.getDriver().findElement(elemento).getCssValue(elementoCss);
			logger.info(String.format("Obteve o valor [%s].", retorno));
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
		return retorno;
	}

	default String obterValorDeUmAtributoDoElemento(By elemento, String atributoAserObtidoOValor) {
		String retorno = null;
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterValorDeUmAtributoDoElemento] do elemento [%s] e atributo [%s].", elemento, atributoAserObtidoOValor));
			retorno = DriverWeb.getDriver().findElement(elemento).getAttribute(atributoAserObtidoOValor);
			logger.info(String.format("Obteve o valor [%s].", retorno));
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
		return retorno;
	}

	default String obterValorDeUmAtributoDoElemento(WebElement elemento, String atributoAserObtidoOValor) {
		String retorno = null;
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterValorDeUmAtributoDoElemento] do elemento [%s] e atributo [%s].", elemento, atributoAserObtidoOValor));
			retorno = elemento.getAttribute(atributoAserObtidoOValor);
			logger.info(String.format("Obteve o valor [%s].", retorno));
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
		return retorno;
	}
	
	default String obterAtributoPorLabel(String label, String atributo) {
		logger.info(String.format("Realizar a ação do método [obterTextoPorLabel] com elemento [%s] e atributo [%s].",
				label, atributo));
		String retorno = null;
		try {
			String xpath = "//b[text()='%s']/../following-sibling::input[@type='hidden']";
			By elemento = By.xpath(String.format(xpath, label));
			retorno = DriverWeb.getDriver().findElement(elemento).getAttribute(atributo);
			logger.info(String.format("Obteve o valor [%s].", retorno));
			return retorno;
		}catch (NoSuchElementException e) {
			logger.error(" -- ERRO: label: '" + label + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar a label: '" + label + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar a label: '" + label);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar a label: '" + label + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: label: '" + label + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- A label: " + label + "NAO esta visivel' em tela.");
		}
		return retorno;
	}

	default String obterTextoHidden(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [obterTexto] com elemento [%s].", elemento));
			String retorno = DriverWeb.getDriver().findElement(elemento).getAttribute("innerText");
			logger.info(String.format("Obteve o valor [%s].", retorno));
			return retorno;

		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar o elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		}
		return null;
	}
}