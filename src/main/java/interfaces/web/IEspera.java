package interfaces.web;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IEspera {
	static Logger logger = LogWeb.getLogger(IEspera.class);
	long TIMEOUT = 40;

	default void esperarSerClicavel(By elemento, int tempoEmSegundos) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [esperarElementoSerClicavel] com elemento [%s] por %d segundos", elemento,
					tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.elementToBeClickable(elemento));
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

	
	default void esperarSerClicavel(WebElement elemento, int tempoEmSegundos) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [esperarElementoSerClicavel] com elemento [%s] por %d segundos", elemento,
					tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.elementToBeClickable(elemento));
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
	
	default void esperarSerSelecionavel(By elemento, int tempoEmSegundos) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [esperarElementoSerSelecionado] com elemento [%s] por %d segundos", elemento,
					tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.elementToBeSelected(elemento));
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

	default void esperarUrlSerCarregada(String url, int tempoEmSegundos) {
		try {
			logger.info(String.format("Realizar a ação do método [esperarUrlSerCarregada] com url [%s] por %d segundos",
					url, tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.urlToBe(url));
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para carregar a url: '" + url);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para carregar a url: '" + url + "' em tela.");
		}
	}

	default void esperarCarregamento(int posTime, long time) {
		logger.info(String.format("Realizar a ação do método [esperarCarregamento] por %d segundos.", posTime));
		WebDriver driver = DriverWeb.getDriver();
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(pageLoadCondition);
			esperarPadrao(posTime);
			
		} catch (Exception e) {
			logger.error(" -- ERRO: Tempo excedido para carregar a url");
			Assert.fail(LocalDateTime.now() + " Tempo excedido para carregar a url em tela.");
		}
	}
	
	default WebElement esperarElementoExistir(By elemento, int tempoEmSegundos) {
		logger.info(String.format("Realizar a ação do método [esperarElementoExistir] por %d segundos.", tempoEmSegundos));
		try {
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			return wait.until(ExpectedConditions.presenceOfElementLocated(elemento));
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
		return null;
	}

	default void esperarElementoSumir(By elemento, int tempoEmSegundos, String descricaoDoPasso) {
		logger.info(
				String.format("Realizar a ação do método [esperarElementoSumir] por %d segundos.", tempoEmSegundos));

		try {
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			esperarElementoAparecer(elemento, tempoEmSegundos);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elemento));
		}catch (NoSuchElementException e) {
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

	default void esperarElementoAparecer(By elemento, int tempoEmSegundos){
		logger.info(
				String.format("Realizar a ação do método [esperarElementoAparecer] com elemento [%s] por %d segundos.",
						elemento, tempoEmSegundos));
		WebDriver driver = DriverWeb.getDriver();
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, tempoEmSegundos);
			wait.until(ExpectedConditions.presenceOfElementLocated(elemento));

		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}
	


	default void esperarElementoSumir(WebElement elemento, long tempoEmSegundos) {
		logger.info(String.format("Realizar a ação do método [esperarElementoSumir] com elemento [%s] por %d segundos",
				elemento.toString(), tempoEmSegundos));
		try {
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.stalenessOf(elemento));

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

	default void esperarVisibilidadeDoElemento(By elemento, int tempoEmSegundos) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [esperarVisibilidadeDoElemento] com elemento [%s] por %d segundos.",
					elemento, tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.visibilityOf(DriverWeb.getDriver().findElement(elemento)));
			logger.info(" Realizou a ação do método [esperarVisibilidadeDoElemento]"
					+ " da classe " + IEspera.class.toString()+ " por  " + "[ " + tempoEmSegundos + " ]" + "segundos");			
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

	default void esperarElementoPorTextoVisivel(String texto, String elemento, int tempoEmSegundos) {
		try {
			logger.info(String.format("Realizar a ação do método [esperarElementoPorTextoVisivel] por %d segundos.",
					tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until(ExpectedConditions.visibilityOf(
					DriverWeb.getDriver().findElement(By.xpath("//" + elemento + "[text()='" + texto + "']"))));		
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: texto: '" + texto + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento pelo texto: '" + texto
					+ "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + texto + "pelo texto");
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento pelo texto: '" + texto);
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: texto: '" + texto + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O texto: " + texto + "NAO esta visivel' em tela.");
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao clicar no elemento pelo texto:" + texto);
			Assert.fail(LocalDateTime.now() + "erro ao clicar no elemento pelo texto:" + texto);
		}
	}

	default String esperarCampoConterValor(By elemento) {
		logger.info(
				String.format("Realizar a ação do método [esperarCampoConterValor] com o elemento [%s].", elemento));
		try {
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), TIMEOUT);
			wait.until(ExpectedConditions.attributeToBeNotEmpty(DriverWeb.getDriver().findElement(elemento), "value"));
			return DriverWeb.getDriver().findElement(elemento).getAttribute("value");		
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + " NAO esta visivel' em tela.");
		} catch (StaleElementReferenceException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO possui value: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + " NAO possui value.");
		}
		return null;
	}

	default String esperarCampoConterTexto(By elemento) {
		String resultado = null;
		try {
			logger.info(
					String.format("Realizar a ação do método [esperarCampoConterValor] com o elemento [%s].", elemento));
			WebDriver driver = DriverWeb.getDriver();
			(new WebDriverWait(driver, TIMEOUT)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(elemento).getText().length() != 0;
				}
			});
			resultado = DriverWeb.getDriver().findElement(elemento).getText();
			if (resultado.length() == 0) {
				logger.warn(" -- ERRO: O elemento: '" + elemento + "NAO obteve nenhum valor.");
				Assert.fail(LocalDateTime.now() + " -- NAO obteve nenhum resultado no campo.");
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
		} catch (StaleElementReferenceException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO possui Text: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + " NAO possui Text.");
		}
		return resultado;
	}

	default void esperarPadrao(int tempoEmSegundos) {
		logger.info(String.format("Realizar a ação do método [esperarPadrao] por %d segundos.", tempoEmSegundos));

		try {
			Thread.sleep(tempoEmSegundos * 1000L);
		} catch (InterruptedException e) {
			logger.error(" -- ERRO: Tempo excedido '");
			Assert.fail(LocalDateTime.now() + " Tempo excedido '");
			Thread.currentThread().interrupt();
		}
	}

	default void esperarPaginaSerCarregada(int tempoEmSegundos) {
		try {
			logger.info(String.format("Realizar a ação do método [esperarPaginaSerCarregada] por %d segundos.",
					tempoEmSegundos));
			WebDriverWait wait = new WebDriverWait(DriverWeb.getDriver(), tempoEmSegundos);
			wait.until((dr -> ((JavascriptExecutor) dr)
					.executeScript("return document.readyState").equals("complete")));
		
		} catch (Exception e) {
			logger.error(" -- ERRO: Página atual não foi carregada totalmente em " + tempoEmSegundos + ".");
			Assert.fail(LocalDateTime.now() + " --  Página atual não foi carregada totalmente em " + tempoEmSegundos
					+ ". -->" + e.getMessage());
		}
	}

	default void esperarElementoDeixarDeExistir(By elemento, int tempoEmSegundos) {
		logger.info(String.format(
				"Realizar a ação do método [esperarElementoDeixarDeExistir] com o elemento [%s] por %d segundos",
				elemento, tempoEmSegundos));

		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, tempoEmSegundos);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.numberOfElementsToBe(elemento, 0));

		} catch (Exception e) {
			logger.error(" -- ERRO: elemento: " + elemento + "NAO encontrado. ");
			Assert.fail(LocalDateTime.now() +  " -- ERRO: elemento: " + elemento + "NAO encontrado. " + tempoEmSegundos
					+ ". -->" + e.getMessage());

		} finally {
			DriverWeb.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Troca de janela até achar a que contém o título definido.
	 * 
	 * @param titulo
	 * @author ymnoda
	 * @throws Exception 
	 */
	default void esperarJanelaComTitulo(String titulo, long timeoutEmSegundos){
		logger.info(String.format("Espera a janela com título \"%s\" aparecer.", titulo));
		long start = System.currentTimeMillis();
		long end = start + 1000 * timeoutEmSegundos;
		WebDriver driver = DriverWeb.getDriver();
		while (System.currentTimeMillis() < end) {
			try {
				for (String janela : driver.getWindowHandles()) {
					driver.switchTo().window(janela);
					logger.info(String.format("Trocou para a janela com título \"%s\".", driver.getTitle()));
					if (driver.getTitle().equals(titulo))
						return;
				}
				Thread.sleep(100);
			} catch (InterruptedException t) {
				logger.error("Tempo excedido para esperar a janela com título \"%s\" carregar."+ titulo);
				Thread.currentThread().interrupt();
			}
		}
	}

	default boolean elementoDeixarDeExistirDepoisDe(By elemento, int tempoEmSegundos) {
		logger.info(
				String.format("Realizar a ação do método [elementoSumirDepoisDe] com elemento [%s] por %s segundos.",
						elemento, tempoEmSegundos));

		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, tempoEmSegundos);
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(elemento);
		} catch (Exception t) {
			logger.error(t.getMessage());
		}
		try {
			wait.until(ExpectedConditions.numberOfElementsToBe(elemento, 0));
			return true;
		} catch (Exception t) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}

	default void esperarElementoSumirPorDuracao(WebElement elemento, int tempoInvisivel, int timeout) {
		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, tempoInvisivel);
		long end = System.currentTimeMillis() + 1000 * timeout;

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			while (System.currentTimeMillis() < end) {
				try {
					wait.until(ExpectedConditions.visibilityOf(elemento));
				} catch (TimeoutException | NoSuchElementException e) {
					return;

				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}

	}

	default void esperarElementoSumirPorDuracao(By by, int tempoInvisivel, int timeout) {
		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, tempoInvisivel);
		long end = System.currentTimeMillis() + 1000 * timeout;

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			logger.info(String.format("Esperando elemento sumir por %d segundos...", tempoInvisivel));

			while (System.currentTimeMillis() < end) {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(by));
					esperarPadrao(500);
				} catch (TimeoutException | NoSuchElementException e) {
					logger.error(String.format("O elemento ficou invisível por mais de %d segundos.", tempoInvisivel));
					return;

				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}

	default void esperarElementoConterTexto(By by, String texto, int timeout) {
		logger.info(
				String.format("Realizar a ação do método [esperarElementoConterTexto] com elemento [%s] e o texto [%s] por %s segundos.",
						by, texto, timeout));
		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			zerarTimeoutImplicito();
			wait.until(ExpectedConditions.textToBe(by, texto));
		} finally {
			setDefaultTimeoutImplicito();
		}

	}

	default void esperarElementoDepoisDeTrocarTela(By by, int timeout) {
		WebDriver driver = DriverWeb.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		long end = System.currentTimeMillis() + 1000 * timeout;

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			while (System.currentTimeMillis() < end) {
				try {
					wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));

				} catch (TimeoutException e) {
					throw e;

				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				try {
					wait.until(ExpectedConditions.numberOfElementsToBe(by, 0));
				} catch (TimeoutException e) {
					return;
				}
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}

	default void zerarTimeoutImplicito() {
		DriverWeb.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	default void setDefaultTimeoutImplicito() {
		DriverWeb.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}


}