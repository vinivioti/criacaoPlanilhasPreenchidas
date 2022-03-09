package geradordeevidenciaword;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import util.Utilitaria;

public class EscreverNoDocumento {
	private XWPFDocument document = null;
	private XWPFTable table = null;
	private XWPFParagraph paragraph = null;
	private XWPFRun run = null;
	private File filefeature = null;
	private FileOutputStream out = null;
	private XWPFTableRow linhaTabela = null;
	private static final String fonte = "Calibri";

	public void organizarTextoImagens(List<String> listaFeature, Map<String, String> listaImagem)
			throws InvalidFormatException, IOException, InterruptedException, AWTException {
		// Inicia a grava��o no documento
		for (String feature : listaFeature) {
			filefeature = new File(feature);
			document = obterTemplatePadrao();
			escreverPrimeiraPagina(filefeature.getName());
			organizar(feature, listaImagem);
			salvarDocumentoEfechar(out, filefeature, document);
		}
	}

	private void escreverPrimeiraPagina(String feature) {
		for (XWPFParagraph p : document.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains("funcionalidadexxx")) {
						text = text.replace("funcionalidadexxx", nomeFeatureSemExtensao(feature));
						r.setText(text, 0);
					}
				}
			}
		}
	}

	private void organizar(String feature, Map<String, String> listaImagens)
			throws IOException, InvalidFormatException, AWTException {
		int contadorVerificaProximo = 0;
		filefeature = new File(feature);
		String linha = null;
		String linhaDois = null;
		String cenario = "Cenario";
		String encondig = "UTF-8";
		String cenarioCorrente = null;
		InputStreamReader ler = null;
		InputStreamReader lerDois = null;
		
		// Percorre o txt. Usa dois leitores. O linha dois � pra verificar se h� um
		// pr�ximo cen�rio. Se a pr�xima linha for um segundo cen�rio, � printado as
		// imagens relativas ao cen�rio.
		// Depois do while, tbm � printado as imagens. Isso garante que ele printe as
		// imagens referentes ao �ltimo cen�rio.

		ler = new InputStreamReader(new FileInputStream(filefeature), encondig);
		lerDois = new InputStreamReader(new FileInputStream(filefeature), encondig);
		BufferedReader lerFeature = new BufferedReader(ler);
		BufferedReader featureComparacao = new BufferedReader(lerDois);
		
		try {

			while ((linhaDois = featureComparacao.readLine()) != null) {
				linhaDois = linhaDois.replace("á", "a");

				if (contadorVerificaProximo == 2) {

					validarScreenshotsComCenario(cenarioCorrente.toUpperCase(), listaImagens);
					adicionarBreakNaPagina(document, paragraph, run);
					contadorVerificaProximo = 1;
				}

				linha = lerFeature.readLine();
				linha = linha.replace("á", "a");

				if(!linha.contains("#") || !linha.contains("@")){
					escrever(linha);
				}
				
				if (linha.contains(cenario)) {
					cenarioCorrente = linha;
				}
			}
			validarScreenshotsComCenario(cenarioCorrente.toUpperCase(), listaImagens);	
		
		} finally {
			lerFeature.close();
			featureComparacao.close();
		}
		
	}


	// Aqui � verificado qual print refere-se a qual cenario
	private void validarScreenshotsComCenario(String nomeCenario, Map<String, String> listaDeImagem)
			throws InvalidFormatException, IOException {
		// aqui corta os caracteres: "Cen�rio :"
		nomeCenario = nomeCenario.substring(9).replace(" ", "");
		nomeCenario = nomeCenario.replace("-", "");

		for(String caminhoImagem : listaDeImagem.keySet()) {

			String nome = listaDeImagem.get(caminhoImagem).toUpperCase();

			nome = nome.substring(0, obterPosicaoDoCaracter(nome, "."));
			nome = nome.replace(" ", "");
			nome = nome.replace("-", "");
			nome = validarNomeImagemUnderline(nome);

			if (validarImagemComNomeCenario(nome, nomeCenario))
				adicionarImagem(caminhoImagem);
		}
	}

	private void criarTabelaDeCenario() {
		document.getLastParagraph();
		criarParagrafo(document, paragraph, run, 1);
		table = document.createTable(2, 1);
		linhaTabela = table.getRow(0);
		linhaTabela.getCell(0).setColor("b8b79f");
		linhaTabela.createCell();
		table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(850));
		table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(7690));
		alinharTabela(table, STJc.CENTER);
		table.setCellMargins(10, 10, 10, 10);
	}

	private void escrever(String linha) throws AWTException {
		try {
			if (!linha.contains("Funcionalidade")) {
				if (linha.contains("Cenario")) {
					criarTabelaDeCenario();
					escreverPalavraCenarioNaTabela();
					escreverNomeDoCenarioNaTabela(linha);
				} else if (!linha.matches("")) {
					adicionarPalavraChaveNaTabelaLinhaDois(linha);
					adicionarStepNaTabelaCenario(linha);
				}
			}
		} catch (Exception e) {
			throw new AWTException("Erro ao escrever na linha");
		}
	}

	private void escreverPalavraCenarioNaTabela() {
		paragraph = table.getRow(0).getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setBold(true);
		run.setText("Cenário:");
		run.setFontFamily(fonte);
	}

	private void escreverNomeDoCenarioNaTabela(String linha) {
		linhaTabela.getCell(1).setText(linha.substring(8, linha.length() - 1));
	}

	private void adicionarPalavraChaveNaTabelaLinhaDois(String linha) {
		paragraph = table.getRow(1).getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		paragraph.setWordWrapped(true);
		run = paragraph.createRun();
		run.setBold(true);
		run.setFontFamily(fonte);
		run.setText(linha.substring(0, linha.indexOf(" ")));
	}

	private void adicionarStepNaTabelaCenario(String linha) {
		paragraph = table.getRow(1).getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setFontFamily(fonte);
		run.setText(linha.substring(linha.indexOf(" "), linha.length() - 1));
		run.addBreak(BreakType.TEXT_WRAPPING);
	}

	private void adicionarImagem(String caminhoImagem) throws InvalidFormatException, IOException {
		XWPFParagraph paragrafo = document.createParagraph();
		XWPFRun runner = paragrafo.createRun();
		paragrafo = document.createParagraph();
		paragrafo.setAlignment(ParagraphAlignment.CENTER);
		runner = paragrafo.createRun();

		// 1 - largura 2 - altura
		runner.addPicture(new FileInputStream(caminhoImagem), XWPFDocument.PICTURE_TYPE_PNG, caminhoImagem,
				Units.pixelToEMU(571), Units.pixelToEMU(190));
	}

	private XWPFDocument obterTemplatePadrao() throws InvalidFormatException, IOException {
		return new XWPFDocument(OPCPackage.open(new FileInputStream("src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "armazenador" + File.separator + "template.doc")));
	}

	private void salvarDocumentoEfechar(FileOutputStream out, File filefeature, XWPFDocument document)
			throws IOException, InterruptedException {
		Thread.sleep(1000);
		out = new FileOutputStream(new File("src" + File.separator + "test" + File.separator + "resources"
				+ File.separator + "relatorios" + File.separator + "evidencias" + File.separator
				+ nomeFeatureSemExtensao(filefeature.getName()) + Utilitaria.obterHoraMinutoSegundo() + ".doc"));

		// finaliza a grava��o no documento
		document.write(out);
		out.close();
		document.close();
	}

	private int obterPosicaoDoCaracter(String texto, String caracter) {
		return texto.indexOf(caracter);
	}

	private void criarParagrafo(XWPFDocument document, XWPFParagraph paragraph, XWPFRun run, int quantidade) {
		for (int i = 1; i <= quantidade; i++) {
			paragraph = document.createParagraph();
			run = paragraph.createRun();
		}
	}

	private void alinharTabela(XWPFTable table, STJc.Enum justification) {
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
		jc.setVal(justification);
	}

	private void adicionarBreakNaPagina(XWPFDocument document, XWPFParagraph paragraph, XWPFRun run) {
		paragraph = document.getLastParagraph();
		run = paragraph.createRun();
		run.addBreak(BreakType.PAGE);
		run.addCarriageReturn();
	}

	// valida se o nome da imagem possui "_". Caso possua, vai cortar at� o ponto do
	// underline.
	private String validarNomeImagemUnderline(String nomeImagem) {
		if (obterPosicaoDoCaracter(nomeImagem, "_") > 0)
			return nomeImagem.substring(0, obterPosicaoDoCaracter(nomeImagem, "_"));
		else
			return nomeImagem;
	}

	// validar se o nome da imagem � igual ao nome do cen�rio
	private boolean validarImagemComNomeCenario(String nomeImagem, String nomeCenario) {
		Boolean status = true;
		if (nomeImagem.matches(nomeCenario)) {
			return status;
		} else {
			return false;
		}
	}

	private String nomeFeatureSemExtensao(String nome) {
		return nome.substring(0, nome.length() - 8);
	}
}