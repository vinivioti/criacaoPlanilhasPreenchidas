package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import geradordeevidenciaword.DocumentoDeEvidencia;

public class Utilitaria {

	public static String obterHoraMinutoSegundo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
		LocalDateTime agora = LocalDateTime.now(ZoneId.systemDefault());
		String agoraFormatado = agora.format(formatter);
		return agoraFormatado;
	}

	public String obterData() {
		LocalDate lcd = LocalDate.now(ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = lcd.format(formatter);
		return data;
	}

	public String parsePdf(String pdf) throws IOException {
		PdfReader reader = new PdfReader(pdf);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy = null;
		String texto = null;
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			texto = texto + " " + strategy.getResultantText();
		}
		reader.close();
		return texto;
	}

	public void gerarEvidenciaWord(boolean gerar, String identificadorConfiguracaoCucumberPlanilha) {
		DocumentoDeEvidencia documentoDeEvidencia = new DocumentoDeEvidencia();
		if (gerar)
			documentoDeEvidencia.gerarEvidencias(identificadorConfiguracaoCucumberPlanilha);
	}

	public String getProjectProperty(String string) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("Properties/project.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties.getProperty(string);
	}

	/**
	 * Retona um valor aleatorio entre o minimo e maximo
	 * 
	 * @author mquiterio
	 * @param minimo double
	 * @param maximo double
	 * @return double
	 */
	public double aleatorio(double minimo, double maximo) {
		return new Random().nextDouble() * ((maximo - minimo) + 1) + minimo;
	}

	/**
	 * Retona um valor aleatorio entre o minimo e maximo
	 * 
	 * @author mquiterio
	 * @param minimo double
	 * @param maximo double
	 * @return double
	 */
	public int aleatorio(int minimo, int maximo) {
		return new Random().nextInt((maximo - minimo) + 1) + minimo;
	}

	/**
	 * Converte Inteiro para String
	 * 
	 * @author mquiterio
	 * @param s int
	 * @return String
	 */
	public String s(int s) {
		return String.format("%d", s);
	}

	/**
	 * Converte Long para String
	 * 
	 * @author mquiterio
	 * @param s long
	 * @return String
	 */
	public String s(long s) {
		return String.format("%.2f", s);
	}

	/**
	 * Converte Double para String
	 * 
	 * @author mquiterio
	 * @param s double
	 * @return String
	 */
	public String s(double s) {
		return String.format("%.2f", s);
	}

	public String[] spliter(String regex, String texto) {

		String[] newLine = null;

		if (texto != null && !texto.isEmpty())
			newLine = texto.split(regex);

		return newLine;
	}

}