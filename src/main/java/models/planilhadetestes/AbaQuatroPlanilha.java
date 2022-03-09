package models.planilhadetestes;

import com.poiji.annotation.ExcelCellName;

public class AbaQuatroPlanilha {
	
	@ExcelCellName("IDENTIFICADOR")
	public String identificador;

	@ExcelCellName("CAMINHODASFEATURES")
	public String caminhodasfeatures;
	
	@ExcelCellName("CAMINHOARQUIVORELATORIOJSON")
	public String caminhoarquivorelatoriojson;
	
	@ExcelCellName("CAMINHOARQUIVORELATORIOXML")
	public String caminhoarquivorelatorioxml;
	
	@ExcelCellName("CAMINHODASFEATURESWEBMOBILE")
	public String caminhodasfeatureswebmobile;
	
	@ExcelCellName("PACOTESTEPS")
	public String pacotesteps;
	
	@ExcelCellName("QUANTIDADEDEEXECUCOESEMPARALELO")
	public String quantidadedeexecucoesemparalelo;
	
					

}