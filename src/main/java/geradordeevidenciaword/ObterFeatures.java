package geradordeevidenciaword;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ObterFeatures {
	List<String> caminho_das_funcionalidades = new ArrayList<>();
	String caminho_das_funcionalidas_convertidos_para_txt;

	public List<String> obterFeatures(List<String> lista_de_pastas) {
		try {
			for (String pasta : lista_de_pastas) {
				File file = new File(pasta);
				File[] todas_as_features = file.listFiles();
				for (File feature : todas_as_features) {
					caminho_das_funcionalidades.add(feature.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caminho_das_funcionalidades;
	}
}
