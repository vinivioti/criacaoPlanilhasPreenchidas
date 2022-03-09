package models.planilhadetestes;

import com.poiji.annotation.ExcelCellName;

public class AbaUmPlanilha {
	@ExcelCellName("IDENTIFICADOR")
	public String identificador;

	@ExcelCellName("URL")
	public String url;
	
	@ExcelCellName("CORPO")
	public String corpo;
	
	@ExcelCellName("STATUS")
	public String status;
}