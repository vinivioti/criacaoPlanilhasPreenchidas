package interfaces.web;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IAcoesBrowser {
	
	static Logger logger = LogWeb.getLogger(IAcoesBrowser.class);
	
	default Boolean verificaDialogUrl() {
		logger.info(
				"Realizar a ação do método [verificaDialogUrl] da classe para validar se o navegador apresentou a alerta na tela.");
		boolean retorno = false;
		WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), 100 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			logger.error("alert was not present");
		} else {
			retorno = true;
			logger.info("alert was present");
		}
		return retorno;
	}

	default void abrirUrl(String url) {
		try {
			logger.info(String.format("Realizar a ação do método [abrirUrl] para acessar [%s].", url));
			DriverWeb.getDriver().get(url);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao abrir url: " + url);
			Assert.fail(LocalDateTime.now() + "erro ao abrir url: " + url);
		}
	}

	default void navegarUrl(String url) {
		try {
			logger.info(String.format("Realizar a ação do método [navegarUrl] com endereço [%s].", url));
			DriverWeb.getDriver().navigate().to(url);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao navegar para a url: " + url);
			Assert.fail(LocalDateTime.now() + "erro ao navegar para a url: " + url);
		}
	}

	default String trocarProximaJanela() {
		String indice = "Proximo";
		String parentWindowHandler = "";
		logger.info("Realizar a ação do método [trocarJanela] para a janela de título \"%s\".");
		try {
			parentWindowHandler = DriverWeb.getDriver().getWindowHandle(); // Store your parent window
			String subWindowHandler = null;
			Set<String> handles = DriverWeb.getDriver().getWindowHandles(); // get all window handles
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			DriverWeb.getDriver().switchTo().window(subWindowHandler); // switch to popup window
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: elemento: '" + indice + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a janela: '" + indice);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao trocar para a janela de índice: " + indice);
			Assert.fail(LocalDateTime.now() + "erro ao trocar para a janela de índice: " + indice);
		}
		return parentWindowHandler;
	}

	default void trocarJanela(Integer indice) {
		try {
			logger.info(String.format("Realizar a ação do método [mudarAba] alterando para a %dº aba.", indice));
			Set<String> handles = DriverWeb.getDriver().getWindowHandles();
			Object[] it = handles.toArray();
			DriverWeb.getDriver().switchTo().window((String) it[indice]);
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: elemento: '" + indice + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a janela: '" + indice);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao trocar para a janela de índice: " + indice);
			Assert.fail(LocalDateTime.now() + "erro ao trocar para a janela de índice: " + indice);
		}
	}

	default void trocarJanela(String nameOrHandle) {
		try {
			logger.info(String.format("Realizar a ação do método [trocarJanela] para a janela de título \"%s\".",
					nameOrHandle));
			DriverWeb.getDriver().switchTo().window(nameOrHandle);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + nameOrHandle + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a janela: '" + nameOrHandle);
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: tempo excedido para encontrar a janela: '" + nameOrHandle);
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a janela: '" + nameOrHandle);
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: janela: '" + nameOrHandle + "' NAO esta visivel.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a janela: '" + nameOrHandle);
		} 
	}

	default void duplicarAba(int numero) {
		try {
			logger.info(
					String.format("Realizar a ação do método [duplicarJanela] efetuando a copia da %dº janela.", numero));

			WebDriver driver = DriverWeb.getDriver();
			DriverWeb.getDriver().switchTo()
					.window((String) DriverWeb.getDriver().getWindowHandles().toArray()[numero]);
			String url = driver.getCurrentUrl();
			((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
			mudarAba(1);
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: aba de numero: '" + numero + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a aba: '" + numero);
		}
	}

	default void mudarAba(int numero) {
		try {
			logger.info(String.format("Realizar a ação do método [mudarAba] alterando para a %dº aba.", numero));
			DriverWeb.getDriver().switchTo()
					.window((String) DriverWeb.getDriver().getWindowHandles().toArray()[numero]);
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: aba de numero: '" + numero + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a aba: '" + numero);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			logger.error(" -- ERRO: aba de numero: '" + numero + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a aba: '" + numero);
		}
	}

	default void fecharAba(int numero) {
		try {
			logger.info(String.format("Realizar a ação do método [fecharJanela] com a %dº janela.", numero));
			DriverWeb.getDriver().switchTo().window((String) DriverWeb.getDriver().getWindowHandles().toArray()[numero])
					.close();
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: aba de numero: '" + numero + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a aba: '" + numero);
		}
	}

	default void fecharAba(String janela) {
		try {
			logger.info(String.format("Realizar a ação do método [fecharJanela] com a janela de handle \"%s\".", janela));
			DriverWeb.getDriver().switchTo().window(janela).close();
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: aba de numero: '" + janela + "' NAO encontrado.");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel trocar para a aba: '" + janela);
		}
	}

	default void atualizarPagina() {
		try {
			logger.info("Realizar a ação do método [atualizarPagina].");
			DriverWeb.getDriver().navigate().refresh();
		} catch (NoSuchWindowException e) {
			logger.error(" -- ERRO: Ao realizar o refresh na página");
			Assert.fail(LocalDateTime.now() + " -- ERRO: Ao realizar o refresh na página '");
		}
	}
}