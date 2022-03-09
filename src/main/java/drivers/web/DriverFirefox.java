package drivers.web;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import interfaces.log.LogWeb;
import io.github.bonigarcia.wdm.WebDriverManager;


/***
 * Classe responsavel por criar a instância do gecko driver
 * @author leonardoananias
 *
 */
public class DriverFirefox {

	private FirefoxDriver driver;
	static Logger logger = LogWeb.getLogger(DriverFirefox.class);
	
	public DriverFirefox(Boolean headless) {
		WebDriverManager.firefoxdriver().setup();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		FirefoxOptions firefoxOptions = setupBrowser(headless);

		driver = new FirefoxDriver(firefoxOptions);
		logger.info("Firefox iniciado com sucesso.");
	}
	
	private FirefoxOptions setupBrowser(Boolean headless) {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", System.getProperty("user.dir") + File.separator);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.closeWhenDone", true);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/msword, application/csv, application/ris, text/csv, image/png, "
						+ "application/pdf, text/html, text/plain, application/zip, application/x-zip, "
						+ "application/x-zip-compressed, application/download, application/octet-stream");
		profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		profile.setPreference("pdfjs.disabled", true);

		// Configuracoes das opcoes do perfil para inserir no navegador
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setProfile(profile);
		firefoxOptions.setHeadless(headless);
		return firefoxOptions;
	}

	public static void finalizarProcessoFirefox() {
		try {
			Process process = Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			@SuppressWarnings("resource")
			Scanner leitor = new Scanner(process.getInputStream());
			while (leitor.hasNextLine()) {
				System.out.println(leitor.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public FirefoxDriver getDriver() {
		return driver;
	}

	
}
