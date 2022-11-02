package br.com.residencia.ecommerce.dto;

public class ItemPedidoDTO {

	private Integer idItemPedido;

	private Integer quantidade;

	private Double precoVenda;

	private Double percDesc;

	private Double valorBruto;

	private Double valorLiquido;

	// Relacionamento
	private PedidoDTO pedido;

	private ProdutoDTO produto;

	public ItemPedidoDTO() {

	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercDesc() {
		return percDesc;
	}

	public void setPercDesc(Double percDesc) {
		this.percDesc = percDesc;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
