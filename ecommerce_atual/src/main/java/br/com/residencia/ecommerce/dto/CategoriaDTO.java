package br.com.residencia.ecommerce.dto;

import java.util.List;

public class CategoriaDTO {

	private Integer idCategoria;

	private String nome;

	private String descricao;

	// Relacionamentos
	private List<ProdutoDTO> produto;

	// Construtores
	public CategoriaDTO() {

	}

	// Get e Set
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProdutoDTO> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoDTO> produto) {
		this.produto = produto;
	}

}
