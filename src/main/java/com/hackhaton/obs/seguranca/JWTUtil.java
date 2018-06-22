package com.hackhaton.obs.seguranca;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackathon.obs.entidades.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public abstract class JWTUtil {

	static final long EXPIRATION_TIME = 860_000_000;
	static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	private static String key = "SECRET_TOKEN";

	static Usuario credentials = new Usuario();
	public static final String TOKEN_HEADER = "Authentication";

	public static String create(String subject) {
		String JWT = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		return JWT;

	}

	public static Jws<Claims> decode(String token, HttpServletResponse response) {
		if (token == null) {
			response.setStatus(403);

		} else if (token.equals(credentials.getToken())) {
			try {
				Jws<Claims> result = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
				response.setStatus(200);
				return result;
			} catch (Exception e) {
				response.setStatus(401);
				return null;
			}

		}
		return null;
	}

	public static boolean verificaToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if(request.getHeader(HEADER_STRING)==null) {
			response.sendError(401, "TOKEN NULLO OU INV�LIDO");
			return false;
		}
		String bearer = request.getHeader(HEADER_STRING).substring(0, 7);
		if (!bearer.equals("Bearer ") || bearer == null) {
			response.sendError(401, "TOKEN NULLO OU INV�LIDO");
			return false;
		}

		String token = request.getHeader(HEADER_STRING).substring(7);
		credentials.setToken(token);
		if (token != null) {
			Jws<Claims> result = decode(token, response);
			if(result == null) {
				return false;
			}
			return true;
		} else {
			response.sendError(401, "TOKEN NULLO OU INV�LIDO");
			return false;
		}

	}

}
