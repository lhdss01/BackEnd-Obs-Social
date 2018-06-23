package com.hackathon.obs.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.obs.entidades.Historico;
import com.hackathon.obs.repository.HistoricoRepository;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("historicos")
public class HistoricoController {

	private HistoricoRepository historicoRepository;

	@Autowired
	public HistoricoController(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
	}

	@GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listarStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			List<Historico> list = historicoRepository.findAll();
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CrossOrigin
	public ResponseEntity<?> salvar(@Valid @RequestBody Historico uni, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			uni = historicoRepository.save(uni);
			return ResponseEntity.created(URI.create("historicos/" + uni.getId())).build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
