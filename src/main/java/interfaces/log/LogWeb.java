package interfaces.log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogWeb {
	
	private static final String RESOURCES = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "conflogs";

	private LogWeb() {}
	
	public static Logger getLogger(Object object) {
		return getLogger(object.getClass());
	}

	public static Logger getLogger(Class<? extends Object> classTarget) {
		System.setProperty("fName",getCurrentDateTime("dd_MM_yyyy[HH-mm]"));
		Logger logger = Logger.getLogger(classTarget);
		String log4jConfigFile = RESOURCES + File.separator+"logAutomacao.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		return logger;
	}
	
	private static String getCurrentDateTime(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
		Date date = new Date();
		return formatter.format(date);
	}
	
}
