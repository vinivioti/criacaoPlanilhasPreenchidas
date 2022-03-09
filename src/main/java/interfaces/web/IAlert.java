package interfaces.web;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IAlert {

	static Logger logger = LogWeb.getLogger(IAlert.class);

	default void aceitarAlerta() {
		try {
			logger.info("Realizar a ação do método [aceitarAlerta] na tela.");
			Alert alert = DriverWeb.getDriver().switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			logger.error(" -- ERRO: NAO ha alerta presente na tela.");
			Assert.fail(LocalDateTime.now() + " NAO ha alerta presente na tela.");
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao aceitar alerta:" + e.getMessage());
			Assert.fail(LocalDateTime.now() + "erro ao aceitar alerta: " + e.getMessage());
		}
	}

	default String obterTextoDoAlerta() {
		String texto = null;
		try {
			logger.info("Realizar a ação do método [obterTextoDoAlerta] na tela.");
			Alert alert = DriverWeb.getDriver().switchTo().alert();
			texto = alert.getText();
		} catch (NoAlertPresentException e) {
			logger.error(" -- ERRO: NAO ha alerta presente na tela.");
			Assert.fail(LocalDateTime.now() + " NAO ha alerta presente na tela.");
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao obter texto do alerta:" + e.getMessage());
			Assert.fail(LocalDateTime.now() + "erro ao obter texto do alerta: " + e.getMessage());
		}
		return texto;
	}

	default void negarAlerta() {
		try {
			logger.info("Realizar a ação do método [negarAlerta] na tela.");
			Alert alert = DriverWeb.getDriver().switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			logger.error(" -- ERRO: NAO ha alerta presente na tela.");
			Assert.fail(LocalDateTime.now() + " NAO ha alerta presente na tela.");
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao escrever no alerta:" + e.getMessage());
			Assert.fail(LocalDateTime.now() + "erro ao escrever no alerta: " + e.getMessage());
		}
	}

	default void escreverNoAlerta(String texto) {
		try {
			logger.info(String.format("Realizar a ação do método [escreverNoAlerta] com o texto [%s].", texto));
			Alert alert = DriverWeb.getDriver().switchTo().alert();
			alert.sendKeys(texto);
		} catch (Exception e) {
			logger.error(" -- ERRO: erro ao escrever o texto: " + texto + " no alerta:" + e.getMessage());
			Assert.fail(LocalDateTime.now() + " erro ao escrever o texto: " + texto + " no alerta: " + e.getMessage());
		}
	}

	default Boolean alertaPresente() {
		Boolean status = false;
		logger.info("Realizar a ação do método [alertaPresente] na tela.");
			for (int i = 0; i < 10; i++) {
				if (DriverWeb.getDriver().switchTo().alert() != null) {
					aceitarAlerta();
					logger.info("Alert foi exibido!");
					status = true;
					break;
				} else if (i == 99) {
					logger.info("Alert não foi exibido!");
				}
			}	
		return status;
    }   
}