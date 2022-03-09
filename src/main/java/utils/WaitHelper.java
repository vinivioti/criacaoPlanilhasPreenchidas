package utils;

import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public class WaitHelper {

	WebDriver driver;
	private Wait<WebDriver> wait;
	private Duration withTimeout = Duration.ofMinutes(1);

	static Logger logger = LogWeb.getLogger(WaitHelper.class);


	public WaitHelper(WebDriver driver) {
		this.wait = new FluentWait<WebDriver>(driver).withTimeout(withTimeout)
				.pollingEvery(Duration.ofMillis(5))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(TimeoutException.class);
	}

	public void clicarWaitHelper(WebElement elemento) {
		this.wait.until(ExpectedConditions.elementToBeClickable(elemento)).click();
	}


	/**
	 * @see Espera por Javascript e Ajax serem carregados com o tempo máximo e
	 *      verificação de carregamento no construtor da classe.
	 * @return
	 * @author leonardoananias
	 */

	public boolean waitForElementsToLoad() {

		ExpectedCondition<Boolean> jQueryLoad = null, jsLoad = null;

		try {
			// wait for jQuery to load
			jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) DriverWeb.getDriver())
								.executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			// wait for Javascript to load
			jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) DriverWeb.getDriver()).executeScript("return document.readyState")
							.toString().equals("complete");
				}
			};

			logger.info(" Realizou a ação do método [waitForElementsToLoad] " + " da classe " + WaitHelper.class.toString());
		} catch (TimeoutException e) {
			logger.warn(" -- ERRO: elementos da página não carregados");
			Assert.fail(LocalDateTime.now() + " Tempo excedido para carregar elementos na página");
		}
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	
	public boolean waitForElementsToLoad(int tempo) {
		ExpectedCondition<Boolean> jQueryLoad = null, jsLoad = null;
		withTimeout = Duration.ofSeconds(tempo);
		try {
			// wait for jQuery to load
			jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) DriverWeb.getDriver())
								.executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			// wait for Javascript to load
			jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) DriverWeb.getDriver()).executeScript("return document.readyState")
							.toString().equals("complete");
				}
			};

			System.out.println("[" + LocalDateTime.now() + " ]" + " Realizou a ação do método [waitForElementsToLoad] "
					+ " da classe " + WaitHelper.class.toString());
		} catch (TimeoutException e) {
			logger.warn(" -- ERRO: elementos da página não carregados");
			Assert.fail(LocalDateTime.now() + " Tempo excedido para carregar elementos na página");
		}
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}


}
