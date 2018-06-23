package com.hackathon.obs.importacao;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackathon.obs.entidades.Importacao;
import com.hackathon.obs.entidades.Usuario;
import com.hackathon.obs.repository.ImportacaoRepository;

@Service
public class ImportacaoService {


	private ImportacaoRepository importacaoRepository;

	@Autowired
	public ImportacaoService(ImportacaoRepository importacaoRepository) {
		this.importacaoRepository = importacaoRepository;
	}

	public void importar(String descricao, Usuario user, File arquivos)
			throws IllegalStateException, IOException {
		Importacao imp = new Importacao();
		imp.setDescricao(descricao);
		imp.setUsuario_id(user);

		try {
			File file = new File(System.getProperty("user.home") + File.separator + "ArquivosImportados"
					+ File.separator +  arquivos.getAbsolutePath());
			imp.setLicitacao(file);
			importacaoRepository.save(imp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
