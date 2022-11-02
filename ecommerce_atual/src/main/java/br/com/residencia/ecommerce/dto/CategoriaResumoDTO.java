package br.com.residencia.ecommerce.dto;

import java.util.List;

public class CategoriaResumoDTO {

	private Integer idCategoria;

	private String nome;

	private String descricao;

	private List<ProdutoResumoDTO2> produto;

	public CategoriaResumoDTO() {

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

	public List<ProdutoResumoDTO2> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoResumoDTO2> produto) {
		this.produto = produto;
	}

}
