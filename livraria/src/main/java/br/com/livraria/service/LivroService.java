package br.com.livraria.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.livraria.entity.Carrinho;
import br.com.livraria.entity.Livro;

@Service
public class LivroService {
	
	@Value("${url.auditoria}") String urlAuditoria;
	@Value("${url.cartao}") String urlCreditCard;
	
	public List<Livro> buscarLivros(Livro livro) {
		List<Livro> mock = Livro.criarMock();
		return mock.parallelStream()
				.filter(obj -> obj.getId().equals(livro.getId() != null ? livro.getId() : obj.getId()))
				.filter(obj -> obj.getNome().contains(livro.getNome() != null ? livro.getNome() : obj.getNome()))
				.filter(obj -> obj.getComentarios().contains(livro.getComentarios() != null ? livro.getComentarios() : obj.getComentarios()))
				.collect(Collectors.toList());
	}
	
	public List<Livro> gravarLivros(Livro[] livros) {
 		return Arrays.asList(livros).parallelStream()
				.map(livroEach -> { return save(livroEach); } )
				.collect(Collectors.toList());
	}
	
	public Livro gravarLivro(Livro livro) {
 		return save(livro);
	}
	
	private Livro save(Livro livro) {
		return new Livro(livro);
	}
	
	public Livro deletarLivro(Long id) {
		return new Livro();
	}
	
	public ResponseEntity<?> registrarAuditoria(Livro livro) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<?> response = template.postForEntity(urlAuditoria, livro, Object.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		}
		return response;
	}
	
	public ResponseEntity<?> registrarPagamento(Carrinho carrinho) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<?> response = template.postForEntity(urlCreditCard, carrinho, Object.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		}
		return response;
	}

}
