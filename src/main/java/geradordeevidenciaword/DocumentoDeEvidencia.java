package geradordeevidenciaword;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import enums.TipoRetorno;
import planilha.Planilha;

public class DocumentoDeEvidencia {
	private static Planilha busca = new Planilha();
	private static String caminhoImagens = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "relatorios" + File.separator + "screenshot";
	private static Map<String, String> imagens = null; 
	private static List<String> features =  null; 

	public void gerarEvidencias(String identificadorConfiguracaoCucumberPlanilha) {
		obterMassas(identificadorConfiguracaoCucumberPlanilha);
		try { gerarDocumentoWord();} 
		catch (InvalidFormatException | IOException | InterruptedException | AWTException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private static void obterMassas(String identificadorConfiguracaoCucumberPlanilha) {
		imagens = new HashMap<>();
		features = new ArrayList<>();
		List<String> pastas;
		ObterPastas obterPastas = new ObterPastas();
		ObterImagens obterImagens = new ObterImagens();
		ObterFeatures obterFeatures = new ObterFeatures();
		
		pastas = obterPastas.obterPastas(busca.retornarElementoDaPlanilha(identificadorConfiguracaoCucumberPlanilha, TipoRetorno.CAMINHODASFEATURESWEBMOBILE));
		features = obterFeatures.obterFeatures(pastas);
		imagens = obterImagens.obterImagens(caminhoImagens);
	}

	private static void gerarDocumentoWord() throws InvalidFormatException, IOException, InterruptedException, AWTException {
		EscreverNoDocumento escreverNoDocumento = new EscreverNoDocumento();
		escreverNoDocumento.organizarTextoImagens(features, imagens);
	}
}