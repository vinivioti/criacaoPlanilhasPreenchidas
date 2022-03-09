package interfaces.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IDesambiguador extends IEspera {
	static Logger logger = LogWeb.getLogger(IDesambiguador.class);
	long TIMEOUT = 40;


	default By desambiguador(ArrayList<By> elementos, String descricaoDoPasso) {
		logger.info("----" + descricaoDoPasso);
		DriverWeb.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		By resultado = null;
		int i = 0;
		do {
			for (By elemento : elementos) {
				if (resultado == null) {
					int contador = DriverWeb.getDriver().findElements(elemento).size();
					resultado =  contador > 0 ? elemento : null;
				}
			}
			esperarPadrao(100);
			i++;
		} while (resultado == null && i < TIMEOUT * 10);
		if (resultado == null) {
			logger.error(" -- ERRO: elementos: '" + elementos + "' NAO encontrados.'");
			Assert.fail(
					LocalDateTime.now() + " -- NAO foi possivel localizar os elementos: '" + elementos + "' em tela.");
		}
		DriverWeb.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		return resultado;
	}

}
