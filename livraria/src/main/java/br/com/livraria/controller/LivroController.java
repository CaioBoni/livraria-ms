package br.com.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.entity.Livro;
import br.com.livraria.service.LivroService;
import br.com.livraria.service.ValidadorTokenService;

@RestController
public class LivroController {
	
	@Autowired LivroService livroService;
	@Autowired ValidadorTokenService validadorTokenService;
	
	@GetMapping(value = "/livros", produces = "application/json")
	public @ResponseBody ResponseEntity<List<Livro>> buscarLivros(@RequestBody(required = false) Livro livro, 
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.buscarLivros(livro != null ? livro : new Livro()), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping(value = "/livros/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<List<Livro>> buscarLivro(@PathVariable Long id, 
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			Livro livro = new Livro(id);
			return new ResponseEntity<>(livroService.buscarLivros(livro), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/livros", produces = "application/json")
	public @ResponseBody ResponseEntity<List<Livro>> cadastrarLivros(
			@RequestHeader(required = false, defaultValue = "not-valid") String token,
			@RequestBody Livro ...livros) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.gravarLivros(livros), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}

	@PutMapping(value = "/livros/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro,
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		livro.setId(id);
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.gravarLivro(livro), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping(value = "/livros/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Livro> deletarLivro(@PathVariable Long id,
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.deletarLivro(id), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	private MultiValueMap<String, String> header(String msg) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.set("mensagem", msg);
		return headers;
	}
}
