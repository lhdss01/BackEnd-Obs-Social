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

import com.hackathon.obs.entidades.CredenciaisUsuarios;
import com.hackathon.obs.entidades.Usuario;
import com.hackathon.obs.repository.UsuarioRepository;
import com.hackhaton.obs.seguranca.JWTUtil;

@RestController
@RequestMapping("login")
public class LoginController {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public LoginController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> efetuarLogin(@RequestBody Usuario usuario, HttpServletResponse response) {
		try {

			Usuario usuarioModel = this.usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			if (usuarioModel == null) {
				return ResponseEntity.notFound().build();
			}

			@SuppressWarnings("unused")
			// Usa Spring Security pra isso
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
