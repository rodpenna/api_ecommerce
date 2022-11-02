package br.com.residencia.ecommerce.dto;

import java.time.Instant;
import java.util.List;

public class ProdutoResumoDTO {

	private Integer idProduto;

	private String nome;

	private String descricao;

	private Integer qntdEstoque;

	private Instant dataCadastro;

	private Double valorUnitario;

	private String imagemNome;

	private String imagemFileName;

	private String imagemUrl;

	public String getImagemNome() {
		return imagemNome;
	}

	public void setImagemNome(String imagemNome) {
		this.imagemNome = imagemNome;
	}

	public String getImagemFileName() {
		return imagemFileName;
	}

	public void setImagemFileName(String imagemFileName) {
		this.imagemFileName = imagemFileName;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	// Relacionamentos
	private CategoriaResumoDTO2 categoria;

	private List<ItemPedidoResumoDTO2> itemPedido;

	// Construtores
	public ProdutoResumoDTO() {

	}

	// Get e Set
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
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

	public Integer getQntdEstoque() {
		return qntdEstoque;
	}

	public void setQntdEstoque(Integer qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public CategoriaResumoDTO2 getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaResumoDTO2 categoria) {
		this.categoria = categoria;
	}

	public List<ItemPedidoResumoDTO2> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedidoResumoDTO2> itemPedido) {
		this.itemPedido = itemPedido;
	}
}
