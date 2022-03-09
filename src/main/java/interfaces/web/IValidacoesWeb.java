package interfaces.web;

import static drivers.web.DriverWeb.getDriver;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import interfaces.log.LogWeb;

public interface IValidacoesWeb extends IObter {
	static Logger logger = LogWeb.getLogger(IValidacoesWeb.class);

	default void validarMensagem(String textoOriginal, By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [validarContemMensagem] com elemento [%s] e texto [%s].",
					elemento, textoOriginal));
			String textoDeComparacao = null;
			textoDeComparacao = obterTexto(elemento);
			if (!textoOriginal.equalsIgnoreCase(textoDeComparacao)) {
				logger.info(
						String.format("O texto obtido do elemento [%s] é diferente do esperado: [%s]", textoDeComparacao, textoOriginal));
				Assert.fail(LocalDateTime.now() + " -- Texto obtido do elemento é diferente do esperado : '" + textoDeComparacao + "' em tela.");
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

	default void validarMensagem(String textoOriginal, WebElement elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [validarContemMensagem] com elemento [%s] e texto [%s].",
					elemento, textoOriginal));
			String textoDeComparacao = null;
			textoDeComparacao = obterTexto(elemento);
			if (!textoOriginal.equals(textoDeComparacao)) {
				logger.info(
						String.format("O texto obtido do elemento [%s] é diferente do esperado: [%s]", textoDeComparacao, textoOriginal));
				Assert.fail(LocalDateTime.now() + " -- Texto obtido do elemento é diferente do esperado : '" + textoDeComparacao + "' em tela.");
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

	default void validarSeElementoEstaVisivel(By elemento) {
		try {
			logger.info(
					String.format("Realizar a ação do método [validarSeElementoEstaVisivel] com elemento [%s].", elemento));
			assertEquals(true, getDriver().findElement(elemento).isDisplayed());
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

	default void validarSeElementoEstaHabilitado(By elemento) {
		try {
			logger.info(String.format("Realizar a ação do método [validarSeElementoEstaHabilitado] com elemento [%s].",
					elemento));
			assertEquals(true, getDriver().findElement(elemento).isEnabled());
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

	default boolean validarSeElementoExiste(By elemento) {
		logger.info(String.format("Realizar a ação do método [validarSeElementoExiste] com elemento [%s].", elemento));
		int contador = getDriver().findElements(elemento).size();
		return contador > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	default void validarTituloDoBrowser(String tituloDaAba) {
		try {
			logger.info(
					String.format("Realizar a ação do método [validarTituloDoNavegador] com título [%s],", tituloDaAba));
			assertEquals(tituloDaAba, getDriver().getTitle());
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: aba: '" + tituloDaAba + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar À aba: '" + tituloDaAba + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: tempo excedido para encontrar à aba: '" + tituloDaAba);
			Assert.fail(LocalDateTime.now() + " tempo excedido para encontrar à aba: '" + tituloDaAba + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: aba: '" + tituloDaAba + "' NAO esta visivel");
			Assert.fail(LocalDateTime.now() + " -- À aba: " + tituloDaAba + "NAO esta visivel'.");
		}
	}

	default void validarUrlAtual(String url) {
		try {
			logger.info(String.format("Realizar a ação do método [validarUrlAtual] com URL [%s].", url));
			assertEquals(url, getDriver().getCurrentUrl());
		} catch (Exception e) {
			logger.error(" -- ERRO: url: '" + url + "' NAO pode ser carregada.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel carregar a url: '" + url);
		}
	}

	default boolean validarSeElementoEstaSelecionado(By elemento) {
		boolean retorno = false;
		try {
			logger.info(String.format("Realizar a ação do método [validarSeElementoEstaSelecionado] com elemento [%s].", elemento));
			retorno = getDriver().findElement(elemento).isSelected();
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

	default boolean validarSeOcheckBoxEstaMarcado(By elemento) {
		boolean retorno = false;
		try {
			logger.info(String.format("Realizar a ação do método [validarSeOcheckBoxEstaMarcado] com elemento [%s].", elemento));
			retorno = getDriver().findElement(elemento).isSelected();
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

	
	default void validaFiltroMenorQue(By by, double valor) {
		List<WebElement> elementos = getDriver().findElements(by);
		List<Integer> retorno = new ArrayList<Integer>();

		for (int i = 0; i < elementos.size(); i++) {
			try {
				String textoSoNumeros = elementos.get(i).getText().replaceAll("[^0-9]", "");

				if (!textoSoNumeros.isEmpty()) {
					Integer texto = Integer.parseInt(textoSoNumeros);
					retorno.add(texto);
					logger.info(retorno);
				}
			} catch (StaleElementReferenceException e) {
				continue;
			}
			for (Integer inteiro : retorno) {
				if (inteiro > (valor * 100)) {
					logger.error("A listagem está com valor superior a do filtro selecionado.");
				}
			}
		}
		logger.info(valor);
	}

	default void validaOrdemCrescente(By elemento) {
		List<WebElement> elementos = getDriver().findElements(elemento);
		List<Integer> vetor = new ArrayList<Integer>();

		for (int i = 0; i < elementos.size(); i++) {
			try {
				String textoSoNumeros = elementos.get(i).getText().replaceAll("[^0-9]", "");

				if (!textoSoNumeros.isEmpty()) {
					Integer texto = Integer.parseInt(textoSoNumeros);
					vetor.add(texto);
				}
			} catch (StaleElementReferenceException e) {
				continue;
			}
		}

		for (int i = 1; i < vetor.size(); i++) {
			if (vetor.get(i - 1) > vetor.get(i)) {
				logger.error("A listagem não está em ordem crescente.");
			}
		}
		logger.info(vetor);

	}

	default void validaOrdemDecrescente(By elemento) {
		List<WebElement> elementos = getDriver().findElements(elemento);
		List<Integer> vetor = new ArrayList<>();

		for (int i = 0; i < elementos.size(); i++) {
			try {
				String textoSoNumeros = elementos.get(i).getText().replaceAll("[^0-9]", "");

				if (!textoSoNumeros.isEmpty()) {
					Integer texto = Integer.parseInt(textoSoNumeros);
					vetor.add(texto);
				}
			} catch (StaleElementReferenceException e) {
				continue;
			}
		}

		for (int i = 1; i < vetor.size(); i++) {
			if (vetor.get(i - 1) < vetor.get(i)) {
				logger.error("A listagem não está em ordem decrescente.");
			}
		}
		logger.info(vetor);

	}

}