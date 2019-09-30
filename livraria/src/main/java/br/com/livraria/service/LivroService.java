package br.com.livraria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import br.com.livraria.entity.Carrinho;
import br.com.livraria.entity.Livro;

@Service
public class LivroService {
	
	@Value("${url.auditoria}") String urlAuditoria;
	@Value("${url.credit.card}") String urlCreditCard;
	
	public List<Livro> buscarLivros(@RequestBody Livro livro) {
		return null;
	}
	
	public List<Livro> gravarLivros(List<Livro> livros) {
 		return livros.parallelStream()
				.map(l -> { return save(l); } )
				.collect(Collectors.toList());
	}
	
	private Livro save(Livro livro) {
		return new Livro();
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
