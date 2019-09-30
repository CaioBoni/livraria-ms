package br.com.livraria.entity;

import java.util.ArrayList;
import java.util.List;

public class Livro {
	
	private Long id;
	private String nome;
	private String comentarios;
	
	public Livro() {
		super();
	}
	
	public Livro(Long id) {
		super();
		this.id = id;
	}

	public Livro(boolean completo) {
		super();
		this.id = 1l;
		this.nome = "Arquitetura";
		this.comentarios = "coments";
	}
	
	public static Livro comComentario(String comentario) {
		Livro livro = new Livro();
		livro.setComentarios(comentario);
		return livro;
	}
	
	public Livro(Livro liv) {
		super();
		this.id = liv.getId();
		this.nome = liv.getNome();
		this.comentarios = liv.getComentarios();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public static List<Livro> criarMock() {
		List<Livro> mock = new ArrayList<>();
		for (int i=0; i<=10; i++) {
			Livro livro = new Livro();
			livro.setId(Long.valueOf(i));
			livro.setNome("Arquitetura".concat("("+i+")"));
			livro.setComentarios("Livro " + i + " depois do " + (i-1));
			mock.add(livro);
		}
		return mock;
	}

}
