package com.despesas.model;

public class DespesasInfo {

	private Long id;
	private String nome;
	private String status;
	private String criado;
	private String aprovado;
	
	
	public DespesasInfo(Long id, String nome, String status, String criado,
			String aprovado) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.criado = criado;
		this.aprovado = aprovado;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCriado() {
		return criado;
	}
	public void setCriado(String criado) {
		this.criado = criado;
	}
	public String getAprovado() {
		return aprovado;
	}
	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}


	@Override
	public String toString() {
		return "DespesasInfo [id=" + id + ", nome=" + nome + ", status="
				+ status + ", criado=" + criado + ", aprovado=" + aprovado
				+ "]";
	}
	
	
}
