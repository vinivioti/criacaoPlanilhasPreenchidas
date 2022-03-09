package drivers.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import interfaces.log.LogWeb;
import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * Classe responsavel por criar a instância do Internet explorer
 * @author leonardoananias
 *
 */
public class DriverInternetExplorer {
	private InternetExplorerDriver driver;
	static Logger logger = LogWeb.getLogger(DriverInternetExplorer.class);
	
	public DriverInternetExplorer(WebDriver driver) {
		WebDriverManager.iedriver().setup();
		InternetExplorerOptions capability = setupBrowser();
		driver = new InternetExplorerDriver(capability);
		logger.info("Internet Explorer iniciado com sucesso.");
	}
	
	private static InternetExplorerOptions setupBrowser() {
		String site = "";
		InternetExplorerOptions capability = new InternetExplorerOptions();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capability.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, site);

		return capability;
	}

	public WebDriver getDriver() {
		return driver;
	}

	
}
