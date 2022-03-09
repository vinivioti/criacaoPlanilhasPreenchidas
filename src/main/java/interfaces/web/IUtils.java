package interfaces.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import drivers.web.DriverWeb;
import interfaces.log.LogWeb;

public interface IUtils {

	static Logger logger = LogWeb.getLogger(IUtils.class);

	default void tirarScreenshotWeb(String caminhoDaImagem, String nomeDaImagem) {
		try {
			logger.info(String.format("Realizar a ação do método [tirarScreenshotWeb] com o nome da imagem [%s]",
					nomeDaImagem));
			File screenshot = ((TakesScreenshot) DriverWeb.getDriver()).getScreenshotAs(OutputType.FILE);
			logger.info(String.format("Salvo a imagem [%s] no caminho [%s]", nomeDaImagem, caminhoDaImagem));
			File destino = new File(caminhoDaImagem + nomeDaImagem + ".png");
			FileUtils.copyFile(screenshot, destino);
		} catch (IOException e) {
			logger.error(" -- ERRO: Imagem: '" + nomeDaImagem + "' NÃO disponivel no caminho: '" + caminhoDaImagem);
			Assert.fail(LocalDateTime.now() + " -- A imagem: " + nomeDaImagem + "NÃO disponivel no caminho' :" + caminhoDaImagem);
		}
	}
}
