package com.hackathon.obs.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.obs.Dao.LicitacaoDao;
import com.hackathon.obs.entidades.Licitacao;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("licitacoes")
public class LicitacaoController {

	private LicitacaoDao dao;

	@Autowired
	public LicitacaoController(LicitacaoDao dao) {
		this.dao = dao;
	}

	@GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {

			List<Licitacao> list = dao.listar();
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CrossOrigin
	public ResponseEntity<?> salvar(@Valid @RequestBody Licitacao l, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {

			try {
				dao.salvar(l);
				return ResponseEntity.created(URI.create("licitacoes/" + l.getId())).build();
			} catch (Exception e) {
				return new ResponseEntity<>("Confira se os campos foram preenchidos corretamente",
						HttpStatus.BAD_REQUEST);
			}
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

	@CrossOrigin
	@GetMapping("{id}")
	public ResponseEntity<?> recuperarPeloId(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (JWTUtil.verificaToken(request, response)) {
			Licitacao licitacao = dao.buscarPeloId(id);
			if (licitacao == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(licitacao);

		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@CrossOrigin
	@PutMapping("{id}")
	public ResponseEntity<Object> atualizarLicitacao(@PathVariable("id") Long id,
			@Valid @RequestBody Licitacao licitacao, HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException, IOException {

		if (JWTUtil.verificaToken(request, response)) {
			Licitacao licitacaoEncontrada = dao.buscarPeloId(id);
			BeanUtils.copyProperties(licitacaoEncontrada, licitacao);
			dao.salvar(licitacaoEncontrada);
			if (licitacaoEncontrada == null) {
				return new ResponseEntity<>("Usuário passado não encontrado", HttpStatus.NOT_FOUND);
			}

			dao.salvar(licitacaoEncontrada);
			return new ResponseEntity<>(licitacao, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
