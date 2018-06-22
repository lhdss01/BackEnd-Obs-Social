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
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.obs.Dao.UsuarioDao;
import com.hackathon.obs.entidades.Usuario;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	private UsuarioDao dao;

	@Autowired
	public UsuarioController(UsuarioDao dao) {
		this.dao = dao;
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<?> listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			List<Usuario> list = dao.listar();
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@CrossOrigin
	@GetMapping("{id}")
	public ResponseEntity<?> recuperarPeloId(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (JWTUtil.verificaToken(request, response)) {
			Usuario usuario = this.dao.buscarPeloId(id);
			if (usuario == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(usuario);

		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> salvarUsuario(@Valid @RequestBody Usuario u, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (JWTUtil.verificaToken(request, response)) {
			try {
				u = dao.salvar(u);
				return ResponseEntity.created(URI.create("usuarios/" + u.getId())).build();
			} catch (Exception e) {
				return new ResponseEntity<>("Confira se os campos foram preenchidos corretamente",
						HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@DeleteMapping(value = "{id}")
	@CrossOrigin
	public ResponseEntity<Object> removerUsuario(@PathVariable("id") Long id, HttpServletRequest request,
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
	@PutMapping("{id}")
	public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException, IOException {

		if (JWTUtil.verificaToken(request, response)) {
			Usuario usuarioEncontrado = dao.buscarPeloId(id);
			BeanUtils.copyProperties(usuarioEncontrado, usuario);
			dao.salvar(usuarioEncontrado);
			if (usuarioEncontrado == null) {
				return new ResponseEntity<>("Usuário passado não encontrado", HttpStatus.NOT_FOUND);
			}

			dao.salvar(usuarioEncontrado);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
