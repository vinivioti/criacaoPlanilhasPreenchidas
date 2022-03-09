package drivers.web;

import org.openqa.selenium.WebDriver;

import enums.Browser;

public class DriverWeb {

	private static WebDriver driver = null;
	private DriverWeb() {}
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver getDriver(Browser navegadorDeExecucao, boolean executarSemInterfaceGrafica) {
		if (driver == null) {
			DriverPath.informarPathDoDriver(navegadorDeExecucao.toString(), driver, executarSemInterfaceGrafica);
			driver = DriverPath.driverInicializado(navegadorDeExecucao.toString());
		}
		return driver;
	}


	public static void finalizarDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}