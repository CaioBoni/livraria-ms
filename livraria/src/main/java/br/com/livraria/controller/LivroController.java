package br.com.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<Livro>> buscarLivros(@RequestBody Livro livro, @RequestHeader String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(livroService.buscarLivros(livro), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}

	//TODO - verificar
	@PostMapping(value = "/livros", produces = "application/json")
	public List<Livro> cadastrarLivros(@RequestBody List<Livro> livros) {
		return livroService.gravarLivros(livros);
	}
	
	//TODO - verificar
	@PutMapping(value = "/livros", produces = "application/json")
	public List<Livro> atualizarLivros(@RequestBody List<Livro> livros) {
		return livroService.gravarLivros(livros);
	}
	
	//TODO - verificar
	@DeleteMapping(value = "/livros/{id}", produces = "application/json")
	public Livro deletarLivro(@RequestParam Long id) {
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
