package thread;

import java.util.ArrayList;
import java.util.List;

import drivers.web.DriverWeb;
import enums.Browser;
import enums.TipoRetorno;
import planilha.Planilha;

public class ConfigurarThread {

	private List<Thread> th = new ArrayList<>();
	private ConfiguracoesCucumber configuracoesCucumber = new ConfiguracoesCucumber();
	private String pluginReportJunit = null;
	private String pluginReportJsonCucumber = null;
	private String caminhoDasFeatures = null;
	private String pacoteSteps = null;

	protected ConfigurarThread(String identificadorConfiguracaoCucumberPlanilhaDeTestes) {
		Planilha planilha = new Planilha();
		pacoteSteps = planilha.retornarElementoDaPlanilha(identificadorConfiguracaoCucumberPlanilhaDeTestes,
				TipoRetorno.PACOTESTEPS);
		caminhoDasFeatures = planilha.retornarElementoDaPlanilha(identificadorConfiguracaoCucumberPlanilhaDeTestes,
				TipoRetorno.CAMINHODASFEATURES);
		pluginReportJunit = planilha.retornarElementoDaPlanilha(identificadorConfiguracaoCucumberPlanilhaDeTestes,
				TipoRetorno.CAMINHOARQUIVORELATORIOXML);
		pluginReportJsonCucumber = planilha.retornarElementoDaPlanilha(
				identificadorConfiguracaoCucumberPlanilhaDeTestes, TipoRetorno.CAMINHOARQUIVORELATORIOJSON);
	}

	protected ConfigurarThread(String pluginJunit, String pluginJsonCucumber, String caminhoFeatures,
			String pacoteDeSteps) {
		pacoteSteps = pacoteDeSteps;
		caminhoDasFeatures = caminhoFeatures;
		pluginReportJunit = pluginJunit;
		pluginReportJsonCucumber = pluginJsonCucumber;
	}

	protected void setarConfiguracoes(List<String> tags, Browser navegadorDeExecucao,
			String caminhoDoDriver, boolean executarSemInterfaceGrafica) {
		executor(pluginReportJunit, pluginReportJsonCucumber, tags, caminhoDasFeatures, pacoteSteps,
				navegadorDeExecucao, caminhoDoDriver, executarSemInterfaceGrafica);
	}

	protected void setarConfiguracoes(List<String> tags, int quantidadeDeExecucoes) {
		executor(pluginReportJunit, pluginReportJsonCucumber, tags, caminhoDasFeatures, pacoteSteps,
				quantidadeDeExecucoes);
	}

	private void executor(String pluginReportJunit, String pluginReportJsonCucumber, List<String> tags,
			String caminhoDasFeatures, String pacoteSteps, Browser navegadorDeExecucao,
			String caminhoDoDriver, boolean executarSemInterfaceGrafica) {
		addThread(pluginReportJunit, pluginReportJsonCucumber, tags, caminhoDasFeatures, pacoteSteps,
				navegadorDeExecucao, executarSemInterfaceGrafica);
		iniciarThread();
	}

	private void executor(String pluginReportJunit, String pluginReportJsonCucumber, List<String> tags,
			String caminhoDasFeatures, String pacoteSteps, int quantidadeDeExecucoes) {
		addThread(pluginReportJunit, pluginReportJsonCucumber, tags, caminhoDasFeatures, pacoteSteps,
				quantidadeDeExecucoes);
		iniciarThread();
	}

	private void addThread(String pluginReportJunit, String pluginReportJsonCucumber, List<String> tags,
			String caminhoDasFeatures, String pacoteSteps, Browser navegadorDeExecucao, boolean executarSemInterfaceGrafica) {
		th.add(new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				DriverWeb.getDriver(navegadorDeExecucao, executarSemInterfaceGrafica);
				configuracoesCucumber.configurarOpcoes(pluginReportJunit, pluginReportJsonCucumber, tags,
						caminhoDasFeatures, pacoteSteps);

			}
		});
	}

	private void addThread(String pluginReportJunit, String pluginReportJsonCucumber, List<String> tags,
			String caminhoDasFeatures, String pacoteSteps, int quantidadeDeExecucoes) {
		for (int i = 1; i <= quantidadeDeExecucoes; i++) {
			th.add(new Thread() {
				@Override
				public void run() {
					configuracoesCucumber.configurarOpcoes(pluginReportJunit, pluginReportJsonCucumber, tags,
							caminhoDasFeatures, pacoteSteps);
				}
			});
		}
	}

	private void iniciarThread() {
		for (Thread t : th) {
			t.start();
		}
		for (Thread t : th) {
			try {
				t.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

}