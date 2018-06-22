package com.hackathon.obs.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.obs.Dao.UsuarioDao;
import com.hackathon.obs.entidades.Usuario;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("login")
public class LoginController {

	private UsuarioDao dao;

	@Autowired
	public LoginController(UsuarioDao dao) {
		this.dao = dao;
	}

	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> efetuarLogin(@RequestBody Usuario usuario, HttpServletResponse response) {
		try {

			Usuario usuarioModel = this.dao.buscarPorEmaileSenha(usuario.getEmail(), usuario.getSenha());
			if (usuarioModel == null) {
				return ResponseEntity.notFound().build();
			}

			@SuppressWarnings("unused")
			String token = JWTUtil.create(usuario.getEmail());
			usuarioModel.setEmail(usuario.getEmail());
			usuarioModel.setNome(usuario.getNome());
			usuarioModel.setToken(token);
			usuarioModel.getId();

			return ResponseEntity.ok(usuarioModel);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
