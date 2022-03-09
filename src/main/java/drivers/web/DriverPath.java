package drivers.web;

import org.openqa.selenium.WebDriver;


public final class DriverPath {

	static String sistema = System.getProperty("os.name").toLowerCase();
	private static final String chrome = "CHROME";
	private static final String firefox = "FIREFOX";
	private static final String explorer = "EXPLORER";
	private static final String mensagem = "Navegador não encontrado.";
	static DriverChrome driverChrome;
	static DriverFirefox driverFirefox;
	static DriverInternetExplorer driverInternet;
	
	private DriverPath() {}
	
	public static void informarPathDoDriver(String navegador,  WebDriver driver, Boolean headless){
		switch (sistemaOperacional()) {
		case "Windows":
			pathWindows(navegador, driver , headless);
			break;
		
		case "Mac":
			pathMac(navegador, driver , headless);
			break;
		
		case "Linux":
			pathLinux(navegador, driver , headless);
			break;
			
		default:
			System.err.println(mensagem);
			break;
		}
	}
	
	public static WebDriver driverInicializado(String navegador) {
		switch (navegador) {
		case chrome:
			return driverChrome.getDriver();
		case firefox:
			return driverFirefox.getDriver();
		case explorer:
			return driverInternet.getDriver();
		default:
			System.err.println(mensagem);
			break;
		}
		return null;
	}
	
	private static void pathWindows(String navegador, WebDriver driver, Boolean headless) {
		switch (navegador) {
		case chrome:
			driverChrome = new DriverChrome(headless);
			break;
		case firefox:
			driverFirefox = new DriverFirefox(headless);
			break;
		case explorer:
			driverInternet = new DriverInternetExplorer(driver);
			break;
		default:
			System.err.println(mensagem);
			break;
		}
	} 
	
	private static void pathMac(String navegador, WebDriver driver, Boolean headless) {
		switch (navegador) {
		case chrome:
			driverChrome = new DriverChrome(headless);
			break;
		case firefox:
			driverFirefox = new DriverFirefox(headless);
			break;
		case explorer:
			driverInternet = new DriverInternetExplorer(driver);
			break;
		default:
			System.err.println(mensagem);
			break;
		}
	} 
	
	private static void pathLinux(String navegador, WebDriver driver, Boolean headless) {
		switch (navegador) {
		case chrome:
			driverChrome = new DriverChrome(headless);
			break;
		case firefox:
			driverFirefox = new DriverFirefox(headless);
			break;
		case explorer:
			driverInternet = new DriverInternetExplorer(driver);
			break;
		default:
			System.out.println(mensagem);
			break;
		}
	} 
	
	private static String sistemaOperacional() {
		if (sistema.contains("windows")) {
			return "Windows";	
		}else if(sistema.contains("mac")) {
			return "Mac";
		}
		else {
			return "Linux";
		}
	}	
}
