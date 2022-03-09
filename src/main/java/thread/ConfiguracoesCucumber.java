package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;

public class ConfiguracoesCucumber{
	
	private static Logger logger = Logger.getLogger(ConfiguracoesCucumber.class);
	private static List<String> pluginList = new ArrayList<String>();

	private List<String> definirTagsEpluginsCucumber(List<String> tags, String pluginReportJunit, String pluginReportJsonCucumber) {
		try {
			logger.info(" -- 1 - Definir tags...");
			definirTags(tags);
			logger.info(" -- Tags definidas com sucesso.");
		} catch (Exception e) {
			logger.error("Problema ao definir tags!");
		}

		try {
			logger.info(" -- 2 - Definir plugins...");
			definirPlugins(pluginReportJunit, pluginReportJsonCucumber);
			logger.info(" -- Plugins definidos com sucesso.");
		} catch (Exception e) {
			logger.error("Problema ao definir Plugins!");
		}
		return pluginList;
	}

	private void definirTags(List<String> tags) {
		if (tags != null) {
			if (tags.size() > 1) {
				for (String tag : tags) {
					pluginList.add("--tags");
					pluginList.add(tag);
				}
			} else if (tags.size() == 1) {
				pluginList.add("--tags");
				pluginList.add(tags.get(0));

			}
		} else {
			logger.info("Parâmetro tags está nulo!");
		}
	}

	private void definirPlugins(String pluginReportJunit, String pluginReportJsonCucumber) {
		if (pluginReportJunit != null) { pluginList.add("--plugin"); pluginList.add(pluginReportJunit); } else { logger.info("Parâmetro pluginReportJunit está nulo!"); }
		if (pluginReportJsonCucumber != null) { pluginList.add("--plugin"); pluginList.add(pluginReportJsonCucumber); } else { logger.info("Parâmetro pluginReportJsonCucumber está nulo!"); }
		pluginList.add("--monochrome");
	}

	private RuntimeOptions definirCaminhoFeaturesEsteps(List<String> pluginList, String caminhoDasFeatures, String pacoteSteps) {
		RuntimeOptions ro = new RuntimeOptions(pluginList);
		try {
			logger.info(" -- 3 - Definir caminho das features...");
			ro.getFeaturePaths().add(caminhoDasFeatures);
			logger.info(" -- Caminho das features definido com sucesso.");
		} catch (Exception e) {
			logger.error("Problema ao setar o caminho das features!");
		}
		try {
			logger.info(" -- 4 - Definir caminho dos steps...");
			ro.getGlue().add(pacoteSteps);
			logger.info(" -- Caminho dos steps definido com sucesso.");
		} catch (Exception e) {
			logger.error("Problema ao definir caminho dos steps");
		}
		return ro;
	}

	private ClassLoader definirClassLoader() { return Cucumber.class.getClassLoader(); }

	private ResourceLoader definirResourceLoader() { return new MultiLoader(definirClassLoader()); }

	private ClassFinder definirClassFinder() { return new ResourceLoaderClassFinder(definirResourceLoader(), definirClassLoader()); }

	protected void configurarOpcoes(String pluginReportJunit, String pluginReportJsonCucumber, List<String> tags, String caminhoDasFeatures, String pacoteSteps) {
		ResourceLoader resourceLoader = definirResourceLoader();
		ClassFinder classFinder = definirClassFinder();
		ClassLoader classLoader = definirClassLoader();
		List<String> pluginsList = definirTagsEpluginsCucumber(tags, pluginReportJunit, pluginReportJsonCucumber);
		RuntimeOptions runtimeOptions = definirCaminhoFeaturesEsteps(pluginsList, caminhoDasFeatures, pacoteSteps);
		cucumber.runtime.Runtime runtime = new cucumber.runtime.Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
		try {
			runtime.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}