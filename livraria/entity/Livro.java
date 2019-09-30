package aula03.entity;

public class Livro {
	
	private Long id;
	private String nome;
	private String comentarios;

	public Livro() {
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
	
	

}
