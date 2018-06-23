package com.hackathon.obs.entidades;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class CredenciaisUsuarios {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private String senha;
	private String email;

	public CredenciaisUsuarios(String token, String senha, String email) {
		this.token = token;
		this.senha = senha;
		this.email = email;
	}

	public CredenciaisUsuarios() {
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
