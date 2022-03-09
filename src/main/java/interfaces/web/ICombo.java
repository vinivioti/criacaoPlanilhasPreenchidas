package interfaces.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface ICombo extends IEspera {
	static Logger logger = LogWeb.getLogger(ICombo.class);


	default void selecionarCombo(By elemento, String valor) {
		logger.info(String.format("Realizar a ação do método [selecionarCombo] com o elemento [%s] e o valor [%s].",
				elemento, valor));
		try {
			WebElement webElement = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(webElement);
			combo.selectByVisibleText(valor);
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
	
	default void selecionarComboPorValue(By elemento, String valor) {
		logger.info(String.format("Realizar a ação do método [selecionarCombo] com o elemento [%s] e o valor [%s].",
				elemento, valor));
		try {
			WebElement webElement = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(webElement);
			combo.selectByValue(valor);
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


	/**
	 * Clicar no combo pela posição (index)
	 * 
	 * @param elemento
	 * @param posicao
	 */
	default void selecionarCombo(By elemento, int posicao) {
		try {
			logger.info(String.format("Realizar a ação do método [selecionarCombo] com o elemento [%s] e o valor [%s].",
					elemento, posicao));
			WebElement webElement = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(webElement);
			if (posicao == -1) {
				combo.selectByIndex(combo.getOptions().size() - 1);
			} else {
				combo.selectByIndex(posicao);
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
		}
	}
	
	/**
	 * Clicar no combo pela posição (index)
	 * 
	 * @param elemento
	 * @param posicao
	 */
	default void selecionarCombo(WebElement elemento, int posicao) {
		try {
			logger.info(String.format("Realizar a ação do método [selecionarCombo] com o elemento [%s] e o valor [%s].", elemento, posicao));
			Select combo = new Select(elemento);
			combo.selectByIndex(posicao);
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

	/**
	 * Obter texto da primeira posição do combo
	 * 
	 * @param elemento
	 * @return String
	 */
	default String obterTextoDaPrimeiraPosicaoDoCombo(By elemento) {
		Select combo = null;
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterTextoDaPrimeiraPosicaoDoCombo] com o elemento [%s] retornando a quantidade de opções.",elemento));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			combo = new Select(element);
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
		return combo.getFirstSelectedOption().getText();
	}

	/**
	 * Obter a quantidade de opções do combo
	 * 
	 * @param elemento
	 * @return Integer
	 */
	default Integer obterQuantidadeOpcoesCombo(By elemento) {
		List<WebElement> options = new ArrayList<>();
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterQuantidadeOpcoesCombo] com o elemento [%s] retornando a quantidade de opções.",
					elemento));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(element);
			options = combo.getOptions();
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
		return options.size();
	}

	/**
	 * Passar um texto e verificar se existe a opção no combo. Ex.: Se quiser
	 * verificar num combo de cidade a opção "São Paulo", deverá ser passado "São
	 * Paulo" como parâmetro
	 * 
	 * @param elemento
	 * @param texto
	 * @return boolean
	 */
	default boolean passarTextoEverificarSeExisteOpcaoDeAcordoComOtextoNoCombo(By elemento, String texto) {
		boolean retorno = false;
		try {
			logger.info(String.format(
					"Realizar a ação do método [passarTextoEverificarSeExisteOpcaoDeAcordoComOtextoNoCombo] com o elemento [%s] verificando o texto [%s].",
					elemento, texto));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(element);
			List<WebElement> options = combo.getOptions();
			for (WebElement option : options) {
				if (option.getText().equals(texto)) {
					retorno = true;
				}
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
		}
		return retorno;
	}

	/**
	 * Desmarcar combo de acordo com o texto
	 * 
	 * @param elemento
	 * @param valor
	 */
	default void desmarcarComboPorTextoVisivel(By elemento, String valor) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [desmarcarComboPorTextoVisivel] com o elemento [%s] desmarcando o texto [%s].",
					elemento, valor));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(element);
			combo.deselectByVisibleText(valor);
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

	/**
	 * Obter todos os textos do combo
	 * 
	 * @param elemento
	 * @return List
	 */
	default List<String> obterTextosCombo(By elemento) {
		List<String> listaDeTexto = new ArrayList<String>();
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterTextosCombo] com o elemento [%s] retornando os textos do campo.",elemento));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(element);
			for (WebElement opção : combo.getOptions()) {
				listaDeTexto.add(opção.getText().trim());
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
		}
		return listaDeTexto;
	}
	
	/**
	 * Obter todos os textos do combo
	 * 
	 * @param elemento
	 * @return List
	 */
	default List<String> obterTextosCombo(WebElement elemento) {
		List<String> listaDeTexto = new ArrayList<String>();
		try {
			logger.info(String.format(
					"Realizar a ação do método [obterTextosCombo] com o elemento [%s] retornando os textos do campo.",
					elemento));
			Select combo = new Select(elemento);
			for (WebElement opção : combo.getOptions()) {
				listaDeTexto.add(opção.getText().trim());
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
		}
		return listaDeTexto;
	}


	default void selecionarComboQueContenhaTexto(By elemento, String tagName, String texto) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [selecionarComboQueContenhaTexto] com o elemento [%s] selecionando o texto [%s].",
					elemento, texto));
			List<WebElement> options = DriverWeb.getDriver().findElement(elemento).findElements(By.tagName(tagName));
			for (WebElement option : options) {
				if (option.getText().contains(texto)) {
					selecionarCombo(elemento, Integer.parseInt(option.getAttribute("index")));
					break;
				}
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
		}

	}
	
	default void selecionarOpcaoDoComboAleatorioPorIndex(By elemento,String descricaoDoPasso) {
		try {
			logger.info(String.format(
					"Realizar a ação do método [selecionarOpcaoDoComboAleatorioPorIndex] com o elemento [%s] selecionando uma opção aleatoriamente.",
					elemento));
			WebElement element = DriverWeb.getDriver().findElement(elemento);
			Select combo = new Select(element);
			int numero = new Random().nextInt(combo.getOptions().size());
			if(numero == 0) {numero++;}
			combo.selectByIndex(numero);
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (UnexpectedTagNameException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' Deveria ter sido selecionado'");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "Deveria ter sido selecionado");
		}catch (StaleElementReferenceException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + " De referência não está anexado ao documento de página'");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "De referência não está anexado ao documento de página.");
		}catch (IllegalArgumentException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' Não contém no range: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "Não contém no range.");
		}
	}
}
