package util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * 
 * 
 * @author ynoda Esta classe server para medir a performance do que for
 *         realizado
 */
public class Performance {
	private static Map<String, StopWatch> sw;
	private static Logger logger;
	private static Set<Logger> otherLoggers;
	private static final String PATTERN = "%d [%m]%n";

	public static void setLogger(Logger l) {
		if (l != null)
			logger = l;
		logger.debug("Atualizado o Logger da classe de performance");
	}

	/**
	 * @author ynoda Inicia o cronômetro para o valor definido
	 */
	public static String startTime(String valor) {
		logger = logger == null ? logger = novoLogger() : logger;
		sw = sw == null ? new HashMap<String, StopWatch>() : sw;
		if (!sw.containsKey(valor))
			sw.put(valor, new StopWatch());
		StopWatch watch = sw.get(valor);
		if (watch.isStarted()) {
			watch.stop();
			watch.reset();
		}
		watch.start();
		return valor;
	}

	private static Logger novoLogger() {
		Logger l = org.apache.log4j.LogManager.getLogger("PERFORMANCE");
		Layout lay = new PatternLayout(PATTERN);
		String file = "performanceLog.log";
		try {
			FileAppender fa = new FileAppender(lay, file, true);
			l.addAppender(fa);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * Encerra o cronômetro e registra no log quanto tempo passou
	 * 
	 * @author ynoda
	 * @param valor Define qual o valor ao qual o tempo deve ser atribuído,
	 *              facilitando a consulta no log
	 * @return retorna o tempo em Milisegundos desde que o cronômetro foi iniciado
	 */
	public static long stopTime(String valor) {
		if (sw == null)
			return 0;
		if (!sw.containsKey(valor))
			return 0;
		StopWatch watch = sw.get(valor);
		if (!watch.isStarted())
			return 0;
		watch.stop();
		long get = watch.getTime();
		watch.reset();
		String entry = String.format("%s:%s", valor, get);
		logger.info(entry);
		if (otherLoggers != null) {
			for (Logger l : otherLoggers) {
				l.info(entry);
			}
		}
		return get;
	}

	public static void addLogFile(Path path) {
		try {
			otherLoggers = otherLoggers == null ? new HashSet<>() : otherLoggers;
			Logger novo = Logger.getLogger(path.toString().hashCode() + "");
			Appender app;
			Layout lay = new PatternLayout(PATTERN);
			app = new RollingFileAppender(lay, path.toString());
			novo.addAppender(app);
			otherLoggers.add(novo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
