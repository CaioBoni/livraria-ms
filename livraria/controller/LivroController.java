package aula03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aula03.entity.Livro;
import aula03.service.LivroService;
import aula03.service.ValidadorTokenService;

@RestController
public class LivroController {
	
	@Autowired LivroService livroService;
	@Autowired ValidadorTokenService validadorTokenService;
	
	@GetMapping(value = "/livros", produces = "application/json")
	public ResponseEntity<List<Livro>> buscarLivros(@RequestBody Livro livro) {
		if (validadorTokenService.validar()) {
			return new ResponseEntity<>(livroService.buscarLivros(livro), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
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
		if (validadorTokenService.validar()) {
			return new ResponseEntity<>(livroService.registrarAuditoria(livro), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
