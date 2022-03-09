package models.planilhadetestes;

import com.poiji.annotation.ExcelCellName;

public class AbaTresPlanilha {

	@ExcelCellName("IDENTIFICADOR")
	public String identificador;

	@ExcelCellName("APK")
	public String nomeapk;

	@ExcelCellName("DEVICEID")
	public String deviceid;

	@ExcelCellName("VERSAODOSO")
	public String versaodoandroid;

	@ExcelCellName("APPWAITACTIVITY")
	public String appwaitactivity;
	
	@ExcelCellName("URLAPPIUM")
	public String urlAppium;

}