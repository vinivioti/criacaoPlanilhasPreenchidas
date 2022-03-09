package geradordeevidenciaword;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ObterImagens {
	Map<String, String> lista_de_imagens = new HashMap<>();

	public Map<String, String> obterImagens(String caminho_diretorio_principal) {
		File file = new File(caminho_diretorio_principal);
		File[] todas_as_imagens = file.listFiles(); // pega todas as imagens
		try {
			for (File imagem : todas_as_imagens) {
				lista_de_imagens.put(imagem.getAbsolutePath(), imagem.getName());// obt�m o caminho de cada imagem separadamente
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista_de_imagens;
	}
}
