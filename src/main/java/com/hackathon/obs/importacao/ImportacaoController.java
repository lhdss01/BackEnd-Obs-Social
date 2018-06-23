package com.hackathon.obs.importacao;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hackathon.obs.entidades.Usuario;
import com.hackathon.obs.repository.UsuarioRepository;

@RestController
@RequestMapping("importacao")
public class ImportacaoController {

	private ImportacaoService service;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public ImportacaoController(ImportacaoService service, UsuarioRepository usuarioRepository) {
		this.service = service;
		this.usuarioRepository = usuarioRepository;
	}

	@CrossOrigin
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void importar(@RequestParam("arquivo") MultipartFile[] arquivos, @Valid @RequestBody ImportacaoRequest importacaoRequest) throws IllegalStateException, IOException {
		if (arquivos.length == 0) {
			System.out.println("arquivo vazio");
		}
		Usuario user = this.usuarioRepository.getOne(importacaoRequest.getUsuarioId());
		if (user == null)
			throw new EntityNotFoundException();
		File arquivo = null;
		for (MultipartFile multipartFile : arquivos) {
			arquivo = new File(System.getProperty("user.home") + File.separator + "ArquivosImportados" + File.separator
					+ multipartFile.getOriginalFilename());
			multipartFile.transferTo(arquivo);
		}
		this.service.importar(importacaoRequest.getDescricao(), user, arquivo);
	}

}
