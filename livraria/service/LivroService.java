package aula03.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import aula03.entity.Livro;

@Service
public class LivroService {
	
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
		ResponseEntity<?> response = template.postForEntity("http://localhost:8080/api/shopping", livro, Object.class);
		return response;
	}

}
