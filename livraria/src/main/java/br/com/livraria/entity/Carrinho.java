package br.com.livraria.entity;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private Long id;
	private List<Livro> livros;
	private String user;
	
	public Carrinho(){
		super();
		this.livros = new ArrayList<>();
	}
	
	public Carrinho(Long id){
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
