package br.com.livraria.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.livraria.entity.Livro;

@Service
public class LivroService {
	
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

}
