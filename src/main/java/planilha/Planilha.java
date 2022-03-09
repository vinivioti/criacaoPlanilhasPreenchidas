package planilha;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import enums.TipoRetorno;
import models.planilhadetestes.AbaDoisPlanilha;
import models.planilhadetestes.AbaQuatroPlanilha;
import models.planilhadetestes.AbaTresPlanilha;
import models.planilhadetestes.AbaUmPlanilha;

public class Planilha {
	private List<AbaUmPlanilha> modelRequisicoes = new ArrayList<>();
	private List<AbaDoisPlanilha> modelDados = new ArrayList<>();
	private List<AbaTresPlanilha> modelMobile = new ArrayList<>();
	private List<AbaQuatroPlanilha> modelCucumber = new ArrayList<>();
	private LeitorDeDadosExcel leitor = null;
	private String caminhoExcel = System.getProperties().getProperty("pathPlanilha");
	private String arquivoExcel = null;

	public String retornarElementoDaPlanilha(String identificadorDoElemento, TipoRetorno tipoDoRetorno) {
		validarCaminhoDaPlanilha();
		leitor = new LeitorDeDadosExcel();
		if (validarTipoDeRetorno(tipoDoRetorno) == 0) {
			injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaUm();
			return retornarDadoDaAbaUm(identificadorDoElemento, tipoDoRetorno);
		} else if (validarTipoDeRetorno(tipoDoRetorno) == 1) {
			injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaDois();
			return retornarDadoDaAbaDois(identificadorDoElemento, tipoDoRetorno);
		} else if (validarTipoDeRetorno(tipoDoRetorno) == 2) {
			injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaTres();
			return retornarDadoDaAbaTres(identificadorDoElemento, tipoDoRetorno);
		} else {
			injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaQuatro();
			return retornarDadoDaAbaQuatro(identificadorDoElemento, tipoDoRetorno);
		}
	}

	private int validarTipoDeRetorno(TipoRetorno tipoDoRetorno) {
		if (tipoDoRetorno == TipoRetorno.URL || tipoDoRetorno == TipoRetorno.CORPO || tipoDoRetorno == TipoRetorno.STATUS) {
			return 0;
		} else if (tipoDoRetorno == TipoRetorno.MASSA) {
			return 1;
		} else if (tipoDoRetorno == TipoRetorno.APPWAITACTIVITY || tipoDoRetorno == TipoRetorno.NOMEAPK
				|| tipoDoRetorno == TipoRetorno.DEVICEID || tipoDoRetorno == TipoRetorno.VERSAODOSO
				|| tipoDoRetorno == TipoRetorno.URLAPPIUM) {
			return 2;
		} else {
			return 3;
		}
	}

	private void validarCaminhoDaPlanilha() {
		if (caminhoExcel == null) {
			caminhoExcel = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "armazenador";
			arquivoExcel = caminhoExcel + File.separator + "planilha_de_testes.xls";
		}else {
			arquivoExcel = caminhoExcel + File.separator + "planilha_de_testes.xls";
		}
	}

	@SuppressWarnings("unchecked")
	private void injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaUm() {
		modelRequisicoes = (List<AbaUmPlanilha>) leitor.getDadosExcel(AbaUmPlanilha.class,
				arquivoExcel, 0);
	}

	private String retornarDadoDaAbaUm(String identificadorDoElemento, TipoRetorno tipoDoRetorno) {
		return procurarElementoAbaUm(modelRequisicoes, identificadorDoElemento, tipoDoRetorno);
	}

	@SuppressWarnings("unchecked")
	private void injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaDois() {
		modelDados = (List<AbaDoisPlanilha>) leitor.getDadosExcel(AbaDoisPlanilha.class,
				arquivoExcel, 1);
	}

	private String retornarDadoDaAbaDois(String identificadorDoElemento, TipoRetorno tipoDoRetorno) {
		return procurarElementoAbaDois(modelDados, identificadorDoElemento, tipoDoRetorno);
	}

	@SuppressWarnings("unchecked")
	private void injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaTres() {
		modelMobile = (List<AbaTresPlanilha>) leitor.getDadosExcel(AbaTresPlanilha.class,
				arquivoExcel, 2);
	}

	private String retornarDadoDaAbaTres(String identificadorDoElemento, TipoRetorno tipoDoRetorno) {
		return procurarElementoAbaTres(modelMobile, identificadorDoElemento, tipoDoRetorno);
	}

	@SuppressWarnings("unchecked")
	private void injetarDadosRecebidosDaPlanilhaNoObjetoDaAbaQuatro() {
		modelCucumber = (List<AbaQuatroPlanilha>) leitor.getDadosExcel(AbaQuatroPlanilha.class,
				arquivoExcel, 3);
	}

	private String retornarDadoDaAbaQuatro(String identificadorDoElemento, TipoRetorno tipoDoRetorno) {
		return procurarElementoAbaQuatro(modelCucumber, identificadorDoElemento, tipoDoRetorno);
	}

	private String procurarElementoAbaUm(List<AbaUmPlanilha> objetoModelo, String identificadorDoElemento,
			TipoRetorno tipoDoRetorno) {
		List<AbaUmPlanilha> objetos = new ArrayList<>();
		objetos = objetoModelo;
		String retorno = null;

		for (AbaUmPlanilha objeto : objetos) {
			if (objeto.identificador != null) {
				if (retorno == null) {
					if (objeto.identificador.equals(identificadorDoElemento)) {
						if (tipoDoRetorno == TipoRetorno.CORPO) {
							if (objeto.corpo != null) {
								retorno = objeto.corpo;
							}
						} else if (tipoDoRetorno == TipoRetorno.URL) {
							if (objeto.url != null) {
								retorno = objeto.url;
							}
						} else if (tipoDoRetorno == TipoRetorno.STATUS) {
							if (objeto.status != null) {
								retorno = objeto.status;
							}
						}
					}
				}
			}
		}
		return retorno;
	}

	private String procurarElementoAbaDois(List<AbaDoisPlanilha> modelDados, String identificadorDoElemento,
			TipoRetorno tipoDoRetorno) {
		List<AbaDoisPlanilha> objetos = new ArrayList<>();
		objetos = modelDados;
		String retorno = null;

		for (AbaDoisPlanilha objeto : objetos) {
			if (objeto.identificador != null && objeto.massa != null) {
				if (retorno == null) {
					if (objeto.identificador.equals(identificadorDoElemento)) {
						retorno = objeto.massa;
					}
				}
			}
		}
		return retorno;
	}

	private String procurarElementoAbaTres(List<AbaTresPlanilha> modelDados, String identificadorDoElemento,
			TipoRetorno tipoDoRetorno) {
		List<AbaTresPlanilha> objetos = new ArrayList<>();
		objetos = modelDados;
		String retorno = null;

		for (AbaTresPlanilha objeto : objetos) {
			if (objeto.identificador != null) {
				if (retorno == null) {
					if (objeto.identificador.equals(identificadorDoElemento)) {
						if (tipoDoRetorno == TipoRetorno.APPWAITACTIVITY) {
							if (objeto.appwaitactivity != null) {
								retorno = objeto.appwaitactivity;
							}
						} else if (tipoDoRetorno == TipoRetorno.NOMEAPK) {
							if (objeto.nomeapk != null) {
								retorno = objeto.nomeapk;
							}
						} else if (tipoDoRetorno == TipoRetorno.DEVICEID) {
							if (objeto.versaodoandroid != null) {
								retorno = objeto.deviceid;
							}
						} else if (tipoDoRetorno == TipoRetorno.VERSAODOSO) {
							if (objeto.versaodoandroid != null) {
								retorno = objeto.versaodoandroid;
							}
						} else if (tipoDoRetorno == TipoRetorno.URLAPPIUM) {
							if (objeto.urlAppium != null) {
								retorno = objeto.urlAppium;
							}
						}
					}
				}
			}
		}
		return retorno;
	}

	private String procurarElementoAbaQuatro(List<AbaQuatroPlanilha> modelDados, String identificadorDoElemento,
			TipoRetorno tipoDoRetorno) {
		List<AbaQuatroPlanilha> objetos = new ArrayList<>();
		objetos = modelDados;
		String retorno = null;

		for (AbaQuatroPlanilha objeto : objetos) {
			if (objeto.identificador != null) {
				if (retorno == null) {
					if (objeto.identificador.equals(identificadorDoElemento)) {
						if (tipoDoRetorno == TipoRetorno.CAMINHODASFEATURES) {
							if (objeto.caminhodasfeatures != null) {
								retorno = objeto.caminhodasfeatures;
							}
						} else if (tipoDoRetorno == TipoRetorno.CAMINHOARQUIVORELATORIOJSON) {
							if (objeto.caminhoarquivorelatoriojson != null) {
								retorno = objeto.caminhoarquivorelatoriojson;
							}
						} else if (tipoDoRetorno == TipoRetorno.CAMINHOARQUIVORELATORIOXML) {
							if (objeto.caminhoarquivorelatorioxml != null) {
								retorno = objeto.caminhoarquivorelatorioxml;
							}
						} else if (tipoDoRetorno == TipoRetorno.CAMINHODASFEATURESWEBMOBILE) {
							if (objeto.caminhodasfeatureswebmobile != null) {
								retorno = objeto.caminhodasfeatureswebmobile;
							}
						} else if (tipoDoRetorno == TipoRetorno.PACOTESTEPS) {
							if (objeto.pacotesteps != null) {
								retorno = objeto.pacotesteps;
							}
						}
					}
				}
			}
		}
		return retorno;
	}
}