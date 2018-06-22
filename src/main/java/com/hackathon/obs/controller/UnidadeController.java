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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.obs.Dao.UnidadeDao;
import com.hackathon.obs.entidades.Unidade;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("unidades")
public class UnidadeController {

	private UnidadeDao dao;

	@Autowired
	public UnidadeController(UnidadeDao dao) {
		super();
		this.dao = dao;
	}

	@GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listarUnidades(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			List<Unidade> list = dao.listar();
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CrossOrigin
	public ResponseEntity<?> salvar(@Valid @RequestBody Unidade uni, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			uni = dao.salvar(uni);
			return ResponseEntity.created(URI.create("unidades/" + uni.getId())).build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@DeleteMapping(value = "{id}")
	@CrossOrigin
	public ResponseEntity<Object> removerLicitacao(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (JWTUtil.verificaToken(request, response)) {

			try {
				dao.removerPeloId(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (NullPointerException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


}
