package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IFrame {
	static Logger logger = LogWeb.getLogger(IFrame.class);

	default void entrarFrame(String elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [entrarFrame] com elemento [%s]", elemento));
			DriverWeb.getDriver().switchTo().frame(elemento);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: frame: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o frame: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: tempo excedido para encontrar frame: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o frame: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O frame: " + elemento + "NAO esta visivel' em tela.");
		} 
	}

	default void entrarFrame(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [entrarFrame] com elemento [%s]", elemento));
			WebDriver driver = DriverWeb.getDriver();
			WebElement webElemento = DriverWeb.getDriver().findElement(elemento);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.presenceOfElementLocated(elemento));
			wait.until(ExpectedConditions.visibilityOf(webElemento));
			wait.until(ExpectedConditions.elementToBeClickable(elemento));
			driver.switchTo().frame(webElemento);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: frame: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o frame: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: tempo excedido para encontrar frame: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o frame: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O frame: " + elemento + "NAO esta visivel' em tela.");
		}
	}

	default void sairFrame() {
		logger.info("Realizar a ação do método [sairFrame].");
		DriverWeb.getDriver().switchTo().defaultContent();
	}
}