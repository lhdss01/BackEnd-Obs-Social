package com.hackathon.obs.importacao;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hackathon.obs.Dao.UsuarioDao;
import com.hackathon.obs.entidades.Usuario;

@RestController
@RequestMapping("importacao")
public class ImportacaoController {

	private ImportacaoService service;
	private UsuarioDao daoUsuario;

	@Autowired
	public ImportacaoController(ImportacaoService service, UsuarioDao daoUsuario) {
		this.service = service;
		this.daoUsuario = daoUsuario;
	}

	@CrossOrigin
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void importar(@RequestParam("arquivo") MultipartFile[] arquivos, @RequestParam("descricao") String descricao,
			@RequestParam("usuario") Long usuarioId) throws IllegalStateException, IOException {
		if (arquivos.length == 0) {
			System.out.println("arquivo vazio");
		}
		Usuario user = this.daoUsuario.buscarPeloId(usuarioId);
		if (user == null)
			throw new EntityNotFoundException();
		File arquivo = null;
		for (MultipartFile multipartFile : arquivos) {
			arquivo = new File(System.getProperty("user.home") + File.separator + "ArquivosImportados" + File.separator
					+ multipartFile.getOriginalFilename());
			multipartFile.transferTo(arquivo);
		}
		this.service.importar(descricao, user, arquivo);
	}

}
