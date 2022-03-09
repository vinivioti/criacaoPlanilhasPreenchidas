package geradordeevidenciaword;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ObterPastas {
	List<String> lista_de_pastas = new ArrayList<>();

	public List<String> obterPastas(String caminho_diretorio_principal) {
		File file = new File(caminho_diretorio_principal);
		File[] todas_as_pastas_primeira_estrutura = file.listFiles(); // pega todas as pastas na primeira estrutura
		try {
			for (File pasta : todas_as_pastas_primeira_estrutura) {
				lista_de_pastas.add(pasta.getAbsolutePath());// obt�m o caminho de cada pasta separadamente
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista_de_pastas;
	}
}
