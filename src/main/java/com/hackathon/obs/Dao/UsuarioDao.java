package com.hackathon.obs.Dao;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.Usuario;

@Repository
public class UsuarioDao extends GenericDao<Usuario, Long> {

	public UsuarioDao() {
		super(Usuario.class);

	}

	public Usuario buscarPorEmaileSenha(String email, String senha) {
		TypedQuery<Usuario> typedQuery = this.em.createQuery("from Usuario u where u.email = :email and u.senha = :senha", Usuario.class);
		typedQuery.setParameter("email", email);
		typedQuery.setParameter("senha", senha);
		return typedQuery.getSingleResult();
	}

}
