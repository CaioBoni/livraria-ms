package br.com.autenticacao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

	@Value("${valid.token}") String tokenValido;
	@Value("${valid.user}") String usuarioValido;
	@Value("${valid.pwd}") String passwordValido;
	
	@GetMapping(value="/token", produces = "application/json")
	public ResponseEntity<?> validarToken(@RequestParam String token) {
		return new ResponseEntity<>(null, token.equals(tokenValido) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping(value="/usuario-senha", produces = "application/json")
	public ResponseEntity<?> validarUsuarioSenha(@RequestParam String user, @RequestParam String pwd) {
		return new ResponseEntity<>(null, user.equals(usuarioValido) && pwd.equals(passwordValido) 
				? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping(value="/status", produces = "application/json")
	public int retornarStatusValidacao(@RequestParam String user, @RequestParam String pwd) {
		return new ResponseEntity<>(null, user.equals(usuarioValido) && pwd.equals(passwordValido) 
				? HttpStatus.OK : HttpStatus.UNAUTHORIZED).getStatusCodeValue();
	}
}
