package com.hackathon.obs.importacao;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.obs.Dao.ImportacaoDao;
import com.hackathon.obs.entidades.Importacao;
import com.hackathon.obs.entidades.Usuario;

@Service
public class ImportacaoService {

	private ImportacaoDao importacaoDao;

	@Autowired
	public ImportacaoService(ImportacaoDao dao) {
		this.importacaoDao = dao;
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
			importacaoDao.salvar(imp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
