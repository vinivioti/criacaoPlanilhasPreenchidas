package utils;

import static drivers.web.DriverWeb.getDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.log4j.Logger;
import interacoes.InteracaoWeb;
import interfaces.log.LogWeb;

public class RegrasUtils implements InteracaoWeb{

	/**
	 * *********************************************************************************************************************************
	 * 	A classe deve ser responsável por conter todos os novos métodos referente a regra de negócio do sistema,					   *
	 * como menus principais, rotinas especificas, entre outros. 																	   *													   *	
	 * 																																   *
	 * @author leonardoananias																										   *
	 * @serialData 15-10-2020																										   *
	 * ********************************************************************************************************************************/
	
	static Logger logger = LogWeb.getLogger(RegrasUtils.class);
	private WaitHelper waitHelper;
	
	public synchronized WaitHelper getWaitHelper() {
		return (waitHelper == null) ? new WaitHelper(getDriver()) : waitHelper;
	}

	public String getUrl() {
		try {
			logger.info("Url do sistema");
			return getProjectProperty("url");
		} catch (Exception e) {
			logger.error("Erro ao obter a url do arquivo de configuração ->" + e.getMessage());
			return null;
		}
	} 
			
	private static String getProjectProperty(String string) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(caminhoPastaProperties()+"ConfiguracaoPadrao.properties"));
		return properties.getProperty(string);
	}

	private static String caminhoPastaProperties() {
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "armazenador" + File.separator;
	}
	public String dataFormatadaPlanilha(int dias, String formatacao) {
		LocalDate date = LocalDate.now().plusDays(dias);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(formatacao);
		return date.format(formatters);

	}
}
