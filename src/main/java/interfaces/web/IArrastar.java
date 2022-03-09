package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IArrastar{
	static Logger logger = LogWeb.getLogger(IArrastar.class);

	default void arrastar(By elemento, By elementoDois, String descricaoDoPasso) {
		Actions action = new Actions(DriverWeb.getDriver());
		try {
			logger.info("Realizar a ação do método [arrastar] no elemento especificado.");
			action.dragAndDrop(DriverWeb.getDriver().findElement(elemento), DriverWeb.getDriver().findElement(elementoDois));
			action.perform();
		} catch (NoSuchElementException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO encontrado.'");
			Assert.fail(LocalDateTime.now() + " -- NAO foi possivel localizar o elemento: '" + elemento + "' em tela.");
		} catch (TimeoutException e) {
			logger.error(" -- ERRO: Tempo excedido para encontrar elemento: '" + elemento);
			Assert.fail(LocalDateTime.now() + " Tempo excedido para encontrar o elemento: '" + elemento + "' em tela.");
		} catch (ElementNotVisibleException e) {
			logger.error(" -- ERRO: elemento: '" + elemento + "' NAO esta visivel na plataforma: '");
			Assert.fail(LocalDateTime.now() + " -- O elemento: " + elemento + "NAO esta visivel' em tela.");
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao arrastar elemento:" + elemento);
			Assert.fail(LocalDateTime.now() + "erro ao arrastar elemento:" + elemento);
		}
	}
}
