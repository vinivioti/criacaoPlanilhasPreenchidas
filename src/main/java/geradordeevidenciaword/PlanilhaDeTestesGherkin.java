package geradordeevidenciaword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import enums.TipoRetorno;
import planilha.Planilha;

@SuppressWarnings("unused")
public class PlanilhaDeTestesGherkin {
	private XSSFWorkbook workbook;
	private XSSFSheet sheets;
	private int rowNumColunaUm = 0;
	private int rowNumColunaDois = 0;
	private List<String> caminho_features = new ArrayList<>();
	private int primeira_vez_que_cria_a_coluna_um = 0;
	private int primeira_vez_que_cria_a_coluna_dois = 0;
	private String linha_completa = null;
	private int numRowPreCondicao = 7;
	private int numRowResultadoEsperado = 7;
	private int numRowSteps = 7;
	private int numRowFuncionalidade = 7;
	private ObterPastas obterPastas = new ObterPastas();
	private Planilha planilha = new Planilha();

	public void gerar() throws IOException, InterruptedException, InvalidFormatException {
		gerarPlanilha(obterPastas.obterPastas(
				planilha.retornarElementoDaPlanilha("CONFIGURACAO1", TipoRetorno.CAMINHODASFEATURES))); // getCaminhoOndeBuscarAsFeaturesParaORelatorio()));
	}

	private void gerarPlanilha(List<String> features) throws IOException, InvalidFormatException, InterruptedException {
		workbook = new XSSFWorkbook(
				OPCPackage.open(new FileInputStream("armazenador" + File.separator + "Template_Cenarios.xlsm")));
		sheets = workbook.getSheetAt(0);
		for (String feature : features) {

			ler(feature);
		}
		salvar();
	}

	private void ler(String feature) throws IOException {
		List<String> linhas = Files.readAllLines(Paths.get(feature));
		Collections.reverse(linhas);
		int inicioSteps = 0;
		int fimSteps = 0;
		int cenario_anterior = 0;
		String descricao_funcionalidade = null;

		for (int i = 0; i <= linhas.size() - 1; i++) {
			if (linhas.get(i).length() > 15) {
				if (linhas.get(i).substring(0, 15).matches("Funcionalidade:")) {
					descricao_funcionalidade = linhas.get(i).substring(15, linhas.get(i).length());
				}
			}
		}
		for (int i = 0; i <= linhas.size() - 1; i++) {

			if (linhas.get(i).length() > 8) {
				if (linhas.get(i).substring(0, 8).matches("Cenário:")) {
					System.out.println(linhas.get(i));
					for (int j = cenario_anterior; j <= i; j++) {
						if (linhas.get(j).length() > 5) {
							if (linhas.get(j).substring(0, 5).matches("Então")) {
								escrever_resultado_esperado(linhas, j);
								fimSteps = j + 1;
								j = i;
							}
						}
					}
					for (int y = cenario_anterior; y <= i; y++) {
						if (linhas.get(y).length() > 4) {
							if (linhas.get(y).substring(0, 4).matches("Dado")) {
								escrever_pre_condicao(linhas.get(y));
								inicioSteps = y - 1;
								y = i;
							}
						}
					}
					escrever_passos(linhas, inicioSteps, fimSteps);
					escrever_funcionalidade(descricao_funcionalidade);
					cenario_anterior = i;
				}
			}
		}
		System.out.println("oi");
	}

	private void escrever_passos(List<String> l, int inicioSteps, int fimSteps) {
		XSSFRow row;
		if (sheets.getRow(numRowSteps) == null) {
			row = sheets.createRow(numRowSteps);
		} else {
			row = sheets.getRow(numRowSteps);
		}
		String texto_completo = "";
		for (int i = inicioSteps; i >= fimSteps; i--) {
			texto_completo = texto_completo + l.get(i) + "\n";

		}
		escrever(texto_completo, row, 5);
		numRowSteps++;
	}

	private void escrever_pre_condicao(String l) {
		XSSFRow row;
		if (sheets.getRow(numRowPreCondicao) == null) {
			row = sheets.createRow(numRowPreCondicao);
		} else {
			row = sheets.getRow(numRowPreCondicao);
		}
		escrever(l, row, 6);
		numRowPreCondicao++;
	}

	private void escrever_funcionalidade(String l) {
		XSSFRow row;
		if (sheets.getRow(numRowFuncionalidade) == null) {
			row = sheets.createRow(numRowFuncionalidade);
		} else {
			row = sheets.getRow(numRowFuncionalidade);
		}
		escrever(l, row, 4);
		numRowFuncionalidade++;
	}

	private void escrever_resultado_esperado(List<String> l, int posicao) {
		XSSFRow row;
		if (sheets.getRow(numRowResultadoEsperado) == null) {
			row = sheets.createRow(numRowResultadoEsperado);
		} else {
			row = sheets.getRow(numRowResultadoEsperado);
		}
		String texto_completo = "";
		for (int i = posicao; i >= 0; i--) {
			texto_completo = texto_completo + l.get(i) + "\n";

		}
		escrever(texto_completo, row, 7);
		numRowResultadoEsperado++;
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
//		style.setFillPattern(FillPatternType.THIN_HORZ_BANDS);
//		row.setRowStyle(style);
	}

	private void escrever(String linha, XSSFRow row, int coluna) {

		XSSFCell cell = row.createCell(coluna);
		XSSFCellStyle cStyle = workbook.createCellStyle();
		cStyle.setAlignment(HorizontalAlignment.JUSTIFY);
		cStyle.setWrapText(true);
		cStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cStyle.setBorderBottom(BorderStyle.THIN);
		cStyle.setBorderTop(BorderStyle.THIN);
		cStyle.setBorderLeft(BorderStyle.THIN);
		cStyle.setBorderRight(BorderStyle.THIN);
		if (row.getRowNum() % 2 == 0) {
			cStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
			cStyle.setFillPattern(FillPatternType.THIN_HORZ_BANDS);
		}

		cell.setCellStyle(cStyle);
		cell.setCellValue(linha);

	}

//	@SuppressWarnings("resource")
//	public void ler(String feature) throws IOException {
//		File feat = new File(feature);
//		InputStreamReader leitor = new InputStreamReader(new FileInputStream(feat), "UTF-8");
//		BufferedReader ler = new BufferedReader(leitor);
//
//		String linha_anterior = null;
//
//		while ((linha = ler.readLine()) != null) {
//
//			if (linha.contains("Funcionalidade")) {
//				escrever_funcionalidade();
//			}
//
//			if (linha.contains("Cenário")) {
//				escrever_cenario();
//			}
//
//			if (linha.contains("Quando")) {
//				linha_anterior = "Quando";
//				escrever_coluna_um(false);
//
//			}
//
//			if (linha.contains("Então")) {
//				linha_anterior = "Então";
//				escrever_coluna_dois(false);
//			}
//
//			if (linha.contains("E")) {
//				if (linha.substring(0, 2).matches("E ")) {
//					if (linha_anterior.matches("Quando")) {
//						escrever_coluna_um(true);
//					}
//					if (linha_anterior.matches("Então")) {
//						escrever_coluna_dois(true);
//					}
//				}
//			}
//		}
//	}

//	public void escrever_funcionalidade() {
//		// XSSFRow row = sheets.createRow(rowNum);
//		// escrever(row, 0);
//	}
//
//	public void escrever_cenario() {
////		XSSFRow row = sheets.createRow(rowNum);
//		// escrever(row, 0);
//		// rowNum++;
//	}
//
//	public void escrever_coluna_um(boolean pular_linha) {
//		XSSFRow row = sheets.createRow(rowNumColunaUm);
//		escrever(row, 0);
//		if (linha.contains("Quando")) {
//			rowNumColunaDois = rowNumColunaUm;
//		}
//		rowNumColunaUm++;
//	}

//	public void escrever_coluna_dois(boolean pular_linha) {
//		if (pular_linha) {
//			if (sheets.getRow(rowNumColunaDois) != null) {
//				XSSFRow row = sheets.getRow(rowNumColunaDois);
//				escrever(row, 1);
//			} else if (sheets.getRow(rowNumColunaDois) == null) {
//				XSSFRow row = sheets.createRow(rowNumColunaDois);
//				escrever(row, 1);
//
//			}
//			rowNumColunaDois++;
//			if (rowNumColunaUm <= rowNumColunaDois) {
//				rowNumColunaUm = rowNumColunaDois;
//			}
//		} else {
//			if (sheets.getRow(rowNumColunaDois) == null) {
//				XSSFRow row = sheets.createRow(rowNumColunaDois);
//				escrever(row, 1);
//			} else {
//				XSSFRow row = sheets.getRow(rowNumColunaDois);
//				escrever(row, 1);
//			}
//			rowNumColunaDois++;
//			if (rowNumColunaUm <= rowNumColunaDois) {
//				rowNumColunaUm = rowNumColunaDois;
//			}
//		}
//	}

	private void salvar() throws IOException, InterruptedException {

		try {
			FileOutputStream out = new FileOutputStream("src\\test\\resources\\planilhas\\Planilha.xlsm");
			workbook.write(out);
			out.close();
			Thread.sleep(4000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}