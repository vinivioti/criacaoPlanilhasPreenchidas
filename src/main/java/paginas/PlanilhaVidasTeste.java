package paginas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

import models.geradoresdemassa.GeraCpfCnpj;
import utils.RegrasUtils;

public class PlanilhaVidasTeste {

	private GeraCpfCnpj geraCpfCnpj = new GeraCpfCnpj();
	private Faker dados;
//	private final static String FILENAME = "/Users/vinissiusvioti/Desktop/PlanilhaVidas.xlsx";
	private final static String FILENAME = "/Users/vinissiusvioti/Desktop/PlanilhaVidas.xlsx";

	private RegrasUtils regUtils;
	private Map<String, String> massa;

	// public static void main(String[] args) throws IOException{

	public void GeraPlanilhaVidasTeste() {
		int contador = 0;
		String plano = "0";
		String planoEq = "0";
		String dataNasc = "0";

		String numerosCpfs = JOptionPane.showInputDialog("Entre com a quantidade de vidas desejada");
		String dataNascimento = JOptionPane.showInputDialog("Entre a Data de Nascimento)");
		String planoPrincipal = JOptionPane.showInputDialog("Entre com o Plano desejado");
		String planoEquivalente = JOptionPane.showInputDialog("Entre com o Plano Equivalente (se houver)");

		contador = Integer.parseInt(numerosCpfs);
		plano = String.valueOf(planoPrincipal);
		planoEq = String.valueOf(planoEquivalente);
		dataNasc = String.valueOf(dataNascimento);


		File deleta = new File(FILENAME);
		if (deleta.exists()) {
			deleta.delete();
		}

		// cria o arquivo excell na área de trabalho - xls
		// HSSFWorkbook workbook = new HSSFWorkbook();
		// HSSFSheet sheetCpf = workbook.createSheet("PlanilhaDeVidas");
		// HSSFRow row = sheetCpf.createRow((short)1);

		// cria o arquivo excell na área de trabalho - Usar para .xlsx

		Workbook workbook = new XSSFWorkbook();
		Sheet sheetCpf = workbook.createSheet("PlanilhaDeVidas");
		Row row = sheetCpf.createRow(0);

		List<Map> listOfHash = new ArrayList<Map>();

		for (int i = 0; i < contador; i++) {

			massa = new LinkedHashMap<String, String>();

			regUtils = new RegrasUtils();

			massa.put("tipoMovimento", "1");
			massa.put("estipulante", "1");
			massa.put("contrato", "1");
			massa.put("lotacao", "1");
			massa.put("familia", "1");
			massa.put("sequencia", "1");
			massa.put("dataAdesao", regUtils.dataFormatadaPlanilha(9, "dd/MM/yy"));
			massa.put("numSequencia1", String.valueOf(i + 1));
			massa.put("numSequencia2", "1");
			massa.put("nome", new Faker().name().firstName() + " " + new Faker().name().lastName());
			massa.put("nomeDependente", "");
			massa.put("nomeDaMae", new Faker().name().firstName() + " " + new Faker().name().lastName());
			massa.put("listacpf", new GeraCpfCnpj().cpf(false));
			massa.put("listarg", new GeraCpfCnpj().rg(false));
			massa.put("orgaoEmissor", "SSP");
			massa.put("dataExpedicao", "15/10/05");
			massa.put("passaporte", new Faker().number().digits(5));
			massa.put("pissPasep", new Faker().number().digits(11));
			massa.put("cartaoNacional", new Faker().number().digits(15));
			massa.put("dataNascimento", dataNascimento);
			massa.put("declNascidoVivo", "");
			massa.put("sexo", "M");
			massa.put("grauParentesco", "1");
			massa.put("estadoCivil", "1");
			massa.put("dataCasamento", "");
			massa.put("plano", planoPrincipal);
			massa.put("planoEquivalente", planoEquivalente);
			massa.put("cep", "02543");
			massa.put("cepComplemento", "000");
			massa.put("tipologradouro", "RUA");
			massa.put("logradouro", "JOAQUIM AFONSO DE SOUZA");
			massa.put("numero", "666");
			massa.put("complemento", "");
			massa.put("bairro", "CASA VERDE");
			massa.put("cidade", "SAO PAULO");
			massa.put("uf", "SP");
			massa.put("email", "roseli.laranjeiras@portoseguro.com.br");
			massa.put("ddd", "11");
			massa.put("telefone", "985452525");
			massa.put("dataAdmissao", "20/12/18");
			massa.put("cargo", new Faker().company().profession());
			massa.put("centroDeCusto", new Faker().company().buzzword());
			massa.put("RegistroFuncional", new Faker().number().digits(4));

			listOfHash.add(massa);

		}

		row = sheetCpf.createRow(0);

		Cell cell = row.createCell(0);
		cell.setCellValue("TIPO MOVIMENTO");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("ESTIPULANTE/APOLICE");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("CONTRATO/SUBESTIPULANTE");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("LOTAÇÃO/UNIDADE");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("FAMILIA");
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("SEQUÊNCIA DE DEP");
		Cell cell6 = row.createCell(6);
		cell6.setCellValue("DATA DE ADESÃO*");
		Cell cell7 = row.createCell(7);
		cell7.setCellValue("Nº SEQ.01");
		Cell cell8 = row.createCell(8);
		cell8.setCellValue("Nº SEQ.2");
		Cell cell9 = row.createCell(9);
		cell9.setCellValue("NOME*");
		Cell cell10 = row.createCell(10);
		cell10.setCellValue("NOME DEPENDENTE");
		Cell cell11 = row.createCell(11);
		cell11.setCellValue("NOME MAE*");
		Cell cell12 = row.createCell(12);
		cell12.setCellValue("CPF#");
		Cell cell13 = row.createCell(13);
		cell13.setCellValue("RG#");
		Cell cell14 = row.createCell(14);
		cell14.setCellValue("ORGAO EMISSOR#");
		Cell cell15 = row.createCell(15);
		cell15.setCellValue("DATA EXPEDIÇÃO RG#");
		Cell cell16 = row.createCell(16);
		cell16.setCellValue("PASSAPORTE");
		Cell cell17 = row.createCell(17);
		cell17.setCellValue("PIS/PASEP");
		Cell cell18 = row.createCell(18);
		cell18.setCellValue("CARTAO NAC");
		Cell cell19 = row.createCell(19);
		cell19.setCellValue("DATA NASC*");
		Cell cell20 = row.createCell(20);
		cell20.setCellValue("DECL NASCIDO VIVO");
		Cell cell21 = row.createCell(21);
		cell21.setCellValue("SEXO*");
		Cell cell22 = row.createCell(22);
		cell22.setCellValue("GRAU PARENTESCO*");
		Cell cell23 = row.createCell(23);
		cell23.setCellValue("ESTADO CIVIL*");
		Cell cell24 = row.createCell(24);
		cell24.setCellValue("DATA CASAMENTO");
		Cell cell25 = row.createCell(25);
		cell25.setCellValue("PLANO*");
		Cell cell26 = row.createCell(26);
		cell26.setCellValue("PLANO EQUIVALENTE");
		Cell cell27 = row.createCell(27);
		cell27.setCellValue("CEP*");
		Cell cell28 = row.createCell(28);
		cell28.setCellValue("CEP COMPLEMENTAR*");
		Cell cell29 = row.createCell(29);
		cell29.setCellValue("TIPO DE LOGRADOURO*");
		Cell cell30 = row.createCell(30);
		cell30.setCellValue("LOGRADOURO*");
		Cell cell31 = row.createCell(31);
		cell31.setCellValue("NUMERO*");
		Cell cell32 = row.createCell(32);
		cell32.setCellValue("COMPLEMENTO");
		Cell cell33 = row.createCell(33);
		cell33.setCellValue("BAIRRO*");
		Cell cell34 = row.createCell(34);
		cell34.setCellValue("CIDADE*");
		Cell cell35 = row.createCell(35);
		cell35.setCellValue("UF*");
		Cell cell36 = row.createCell(36);
		cell36.setCellValue("E-MAIL");
		Cell cell37 = row.createCell(37);
		cell37.setCellValue("DDD");
		Cell cell38 = row.createCell(38);
		cell38.setCellValue("TELEFONE");
		Cell cell39 = row.createCell(39);
		cell39.setCellValue("DATA ADMISSAO");
		Cell cell40 = row.createCell(40);
		cell40.setCellValue("CARGO");
		Cell cell41 = row.createCell(41);
		cell41.setCellValue("CENTRO DE CUSTO");
		Cell cell42 = row.createCell(42);
		cell42.setCellValue("REGISTRO FUNCIONAL");

		int linha = 1;

		for (Map map : listOfHash) {

			row = sheetCpf.createRow(linha++);
			Cell celula = row.createCell(cell.getColumnIndex());
			celula.setCellValue(map.get("tipoMovimento").toString());
			Cell celula1 = row.createCell(cell1.getColumnIndex());
			celula1.setCellValue(map.get("estipulante").toString());
			Cell celula2 = row.createCell(cell2.getColumnIndex());
			celula2.setCellValue(map.get("contrato").toString());
			Cell celula3 = row.createCell(cell3.getColumnIndex());
			celula3.setCellValue(map.get("lotacao").toString());
			Cell celula4 = row.createCell(cell4.getColumnIndex());
			celula4.setCellValue(map.get("familia").toString());
			Cell celula5 = row.createCell(cell5.getColumnIndex());
			celula5.setCellValue(map.get("sequencia").toString());
			Cell celula6 = row.createCell(cell6.getColumnIndex());
			celula6.setCellValue(map.get("dataAdesao").toString());
			Cell celula7 = row.createCell(cell7.getColumnIndex());
			celula7.setCellValue(map.get("numSequencia1").toString());
			Cell celula8 = row.createCell(cell8.getColumnIndex());
			celula8.setCellValue(map.get("numSequencia2").toString());
			Cell celula9 = row.createCell(cell9.getColumnIndex());
			celula9.setCellValue(map.get("nome").toString().toUpperCase());
			Cell celula10 = row.createCell(cell10.getColumnIndex());
			celula10.setCellValue(map.get("nomeDependente").toString().toUpperCase());
			Cell celula11 = row.createCell(cell11.getColumnIndex());
			celula11.setCellValue(map.get("nomeDaMae").toString().toUpperCase());
			Cell celula12 = row.createCell(cell12.getColumnIndex());
			celula12.setCellValue(map.get("listacpf").toString());
			Cell celula13 = row.createCell(cell13.getColumnIndex());
			celula13.setCellValue(map.get("listarg").toString());
			Cell celula14 = row.createCell(cell14.getColumnIndex());
			celula14.setCellValue(map.get("orgaoEmissor").toString());
			Cell celula15 = row.createCell(cell15.getColumnIndex());
			celula15.setCellValue(map.get("dataExpedicao").toString());
			Cell celula16 = row.createCell(cell16.getColumnIndex());
			celula16.setCellValue(map.get("passaporte").toString());
			Cell celula17 = row.createCell(cell17.getColumnIndex());
			celula17.setCellValue(map.get("pissPasep").toString());
			Cell celula18 = row.createCell(cell18.getColumnIndex());
			celula18.setCellValue(map.get("cartaoNacional").toString());
			Cell celula19 = row.createCell(cell19.getColumnIndex());
			celula19.setCellValue(map.get("dataNascimento").toString());
			Cell celula20 = row.createCell(cell20.getColumnIndex());
			celula20.setCellValue(map.get("declNascidoVivo").toString());
			Cell celula21 = row.createCell(cell21.getColumnIndex());
			celula21.setCellValue(map.get("sexo").toString());
			Cell celula22 = row.createCell(cell22.getColumnIndex());
			celula22.setCellValue(map.get("grauParentesco").toString());
			Cell celula23 = row.createCell(cell23.getColumnIndex());
			celula23.setCellValue(map.get("estadoCivil").toString());
			Cell celula24 = row.createCell(cell24.getColumnIndex());
			celula24.setCellValue(map.get("dataCasamento").toString());
			Cell celula25 = row.createCell(cell25.getColumnIndex());
			celula25.setCellValue(map.get("plano").toString());
			Cell celula26 = row.createCell(cell26.getColumnIndex());
			celula26.setCellValue(map.get("planoEquivalente").toString());
			Cell celula27 = row.createCell(cell27.getColumnIndex());
			celula27.setCellValue(map.get("cep").toString());
			Cell celula28 = row.createCell(cell28.getColumnIndex());
			celula28.setCellValue(map.get("cepComplemento").toString());
			Cell celula29 = row.createCell(cell29.getColumnIndex());
			celula29.setCellValue(map.get("tipologradouro").toString().toUpperCase());
			Cell celula30 = row.createCell(cell30.getColumnIndex());
			celula30.setCellValue(map.get("logradouro").toString().toUpperCase());
			Cell celula31 = row.createCell(cell31.getColumnIndex());
			celula31.setCellValue(map.get("numero").toString());
			Cell celula32 = row.createCell(cell32.getColumnIndex());
			celula32.setCellValue(map.get("complemento").toString().toUpperCase());
			Cell celula33 = row.createCell(cell33.getColumnIndex());
			celula33.setCellValue(map.get("bairro").toString().toUpperCase());
			Cell celula34 = row.createCell(cell34.getColumnIndex());
			celula34.setCellValue(map.get("cidade").toString().toUpperCase());
			Cell celula35 = row.createCell(cell35.getColumnIndex());
			celula35.setCellValue(map.get("uf").toString().toUpperCase());
			Cell celula36 = row.createCell(cell36.getColumnIndex());
			celula36.setCellValue(map.get("email").toString());
			Cell celula37 = row.createCell(cell37.getColumnIndex());
			celula37.setCellValue(map.get("ddd").toString());
			Cell celula38 = row.createCell(cell38.getColumnIndex());
			celula38.setCellValue(map.get("telefone").toString());
			Cell celula39 = row.createCell(cell39.getColumnIndex());
			celula39.setCellValue(map.get("dataAdmissao").toString());
			Cell celula40 = row.createCell(cell40.getColumnIndex());
			celula40.setCellValue(map.get("cargo").toString().toUpperCase());
			Cell celula41 = row.createCell(cell41.getColumnIndex());
			celula41.setCellValue(map.get("centroDeCusto").toString());
			Cell celula42 = row.createCell(cell42.getColumnIndex());
			celula42.setCellValue(map.get("RegistroFuncional").toString());

		}

		for (int column = 0; column <= 42; column++) {
			sheetCpf.autoSizeColumn(column);
		}

		try {
			FileOutputStream fileOut = new FileOutputStream(new File(PlanilhaVidasTeste.FILENAME));
			workbook.write(fileOut);
	   		sheetCpf.getWorkbook().close();
			fileOut.flush();
			fileOut.close();
 
			//workbook.close();
			
			if (workbook != null)
				workbook.close();

			System.out.println("Arquivo Excel criado com sucesso!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
 
    }

}
   
