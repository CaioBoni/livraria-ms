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
	public ResponseEntity<List<Livro>> buscarLivros(@RequestBody Livro livro) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		if (validadorTokenService.validarToken()) {
			headers.set("mensagem", "Retornado com sucesso");
			return new ResponseEntity<>(livroService.buscarLivros(livro), headers, HttpStatus.OK);
		}
		headers.set("mensagem", "Não autorizado");
		return new ResponseEntity<>(headers, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/livros", produces = "application/json")
	public List<Livro> cadastrarLivros(@RequestBody List<Livro> livros) {
		return livroService.gravarLivros(livros);
	}
	
	@PutMapping(value = "/livros", produces = "application/json")
	public List<Livro> atualizarLivros(@RequestBody List<Livro> livros) {
		return livroService.gravarLivros(livros);
	}
	
	@DeleteMapping(value = "/livros/{id}", produces = "application/json")
	public Livro deletarLivro(@RequestParam Long id) {
		return livroService.deletarLivro(id);
	}
	
	@PostMapping(value = "/livros/audit", produces = "application/json")
	public ResponseEntity<?> cadastrarLivros(@RequestBody Livro livro) {
		if (validadorTokenService.validarToken()) {
			return new ResponseEntity<>(livroService.registrarAuditoria(livro), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping(value = "/livros/carrinho", produces = "application/json")
	public ResponseEntity<?> registrarPagamento(@RequestBody Carrinho carrinho) {
		if (validadorTokenService.validarToken()) {
			return new ResponseEntity<>(livroService.registrarPagamento(carrinho), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
