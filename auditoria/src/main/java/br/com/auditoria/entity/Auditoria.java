package br.com.auditoria.entity;

public class Auditoria {

    private long id;
    private Object objeto;
    private String data;
//    private Livro livro;
//    private Pagamentos pagamento;
//    private Date data;
    
    public Auditoria() {
    }
    
    public Auditoria(Long id) {
    	super();
    	this.id = id;
    }
    
    public Auditoria(Long id, Object objeto, String data) {
    	super();
    	this.id = id;
    	this.objeto = objeto;
    	this.data = data;

    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
