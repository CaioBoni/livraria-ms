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

import br.com.livraria.entity.Carrinho;
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
	public @ResponseBody List<Livro> cadastrarLivros(@RequestBody Livro ...livros) {
		return livroService.gravarLivros(livros);
	}

	@PutMapping(value = "/livros/{id}", produces = "application/json")
	public @ResponseBody Livro atualizarLivros(@PathVariable Long id, @RequestBody Livro livro) {
		livro.setId(id);
		return livroService.gravarLivro(livro);
	}
	
	@DeleteMapping(value = "/livros/{id}", produces = "application/json")
	public @ResponseBody Livro deletarLivro(@PathVariable Long id) {
		return livroService.deletarLivro(id);
	}
	
	//TODO - verificar
	@PostMapping(value = "/livros/audit", produces = "application/json")
	public ResponseEntity<?> cadastrarLivros(@RequestBody Livro livro, @RequestHeader String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.registrarAuditoria(livro), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	//TODO - verificar
	@PostMapping(value = "/livros/carrinho", produces = "application/json")
	public ResponseEntity<?> registrarPagamento(@RequestBody Carrinho carrinho, @RequestHeader String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.registrarPagamento(carrinho), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	private MultiValueMap<String, String> header(String msg) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.set("mensagem", msg);
		return headers;
	}
}
