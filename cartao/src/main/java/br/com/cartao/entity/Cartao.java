package br.com.cartao.entity;

import java.util.Date;

public class Cartao {
	
	private Long id;
	private Date data;
	private Cartao.OperacaoCartao operacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cartao.OperacaoCartao getOperacao() {
		return operacao;
	}

	public void setOperacao(Cartao.OperacaoCartao operacao) {
		this.operacao = operacao;
	}

	public enum OperacaoCartao {
		
		SAIDA(0, "Saida"),
		ENTRADA(1, "Entrada");
		
		private int id;
		private String nome;
		
		OperacaoCartao(int id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		public int getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}
	}

}
