package planilha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

public class LeitorDeDadosExcel {
	PoijiOptions options = null;
	InputStream stream = null;
	List<?> object = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	public List<?> getDadosExcel(Class clazz, String caminhoDoArquivoExcel, int aba) {
		options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(aba).build();
		if (verificarExtensaoDoArquivo(caminhoDoArquivoExcel)) {
			lerArquivoExcel(caminhoDoArquivoExcel);
			converterXLSXparaObjeto(clazz);
		} else {
			lerArquivoExcel(caminhoDoArquivoExcel);
			converterXLSparaObjeto(clazz);
		}
		return object;
	}

	private boolean verificarExtensaoDoArquivo(String caminhoDoArquivoExcel) {
		if (caminhoDoArquivoExcel.contains(".xlsx")) {
			return true;
		} else {
			return false;
		}
	}

	private void lerArquivoExcel(String caminhoDoArquivoExcel) {
		try {
			stream = new FileInputStream(new File(caminhoDoArquivoExcel));
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void converterXLSXparaObjeto(Class clazz) {
		object = Poiji.fromExcel(stream, PoijiExcelType.XLSX, clazz, options);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void converterXLSparaObjeto(Class clazz) {
		object = Poiji.fromExcel(stream, PoijiExcelType.XLS, clazz, options);
	}
}