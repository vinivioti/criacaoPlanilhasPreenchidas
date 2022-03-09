package thread;

import java.util.List;

import enums.Browser;

public abstract class Inicializador extends Exec {

	public void setarConfiguracoes(String identificadorConfiguracaoCucumberPlanilhaDeTestes, List<String> tags, Browser navegadorDeExecucao, String caminhoDoDriver,boolean executarSemInterfaceGrafica) {
		ConfigurarThread configurarThread = new ConfigurarThread(identificadorConfiguracaoCucumberPlanilhaDeTestes);
		configurarThread.setarConfiguracoes(tags, navegadorDeExecucao, caminhoDoDriver, executarSemInterfaceGrafica);
	}

	public void setarConfiguracoes(String caminhoFeatures, String pluginJunit, String pluginJsonCucumber,
			String pacoteDeSteps, List<String> tags, boolean encerrarDrivers, Browser navegadorDeExecucao,
			String caminhoDoDriver, boolean maximizarJanelaDoNavegador, boolean executarSemInterfaceGrafica) {
		ConfigurarThread configurarThread = new ConfigurarThread(pluginJunit, pluginJsonCucumber, caminhoFeatures,
				pacoteDeSteps);
		configurarThread.setarConfiguracoes(tags, navegadorDeExecucao, caminhoDoDriver,executarSemInterfaceGrafica);
	}


	public void setarConfiguracoes(String caminhoFeatures, String pluginJunit, String pluginJsonCucumber,
			String pacoteDeSteps, List<String> tags, int quantidadeDeExecucoes) {
		ConfigurarThread configurarThread = new ConfigurarThread(pluginJunit, pluginJsonCucumber, caminhoFeatures,
				pacoteDeSteps);
		configurarThread.setarConfiguracoes(tags, quantidadeDeExecucoes);
	}
}