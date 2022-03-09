package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IObterJS {
	static Logger logger = LogWeb.getLogger(IObterJS.class);
	
	default String obterValorCssJavascriptPorId(String idDoElemento) {
		String value = null;
		try {
			logger.info(String.format("Realizar a ação do método [obterValorCssJavascriptPorId] com o id [%s].", idDoElemento));
			JavascriptExecutor jse = (JavascriptExecutor) DriverWeb.getDriver();
			value = (String) jse.executeScript(
					"" + "if (document.getElementById('" + idDoElemento + "').style.display == 'none'){   }");
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + idDoElemento + "' NAO encontrado.'");
			Assert.fail(
					LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + idDoElemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + idDoElemento);
			Assert.fail(
					LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + idDoElemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + idDoElemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + idDoElemento + "NAO esta visivel' em tela.");
		} catch (JavaScriptException e) {
			logger.error(" -- ERRO: erro de Javascript ao tentar realizar acao no elemento: '" + idDoElemento);
			Assert.fail(LocalDateTime.now() + " -- erro de Javascript ao tentar realizar acao no elemento: '"
					+ idDoElemento);
		}
		return value;
	}
}