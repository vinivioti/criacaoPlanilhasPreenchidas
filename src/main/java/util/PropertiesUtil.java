package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	/**
	 * O método tem como objetivo retornar a url do arquivo properties.
	 * @author leonardoananias
	 * @param string
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getProjectProperty(String string) throws FileNotFoundException, IOException {
		Properties properties  = new Properties();
		properties.load(new FileInputStream("Properties/project.properties"));
		return properties.getProperty(string);
	}

}
