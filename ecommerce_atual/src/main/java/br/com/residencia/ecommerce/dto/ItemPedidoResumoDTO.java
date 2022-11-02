package br.com.residencia.ecommerce.dto;

public class ItemPedidoResumoDTO {

	private Integer idItemPedido;

	private Integer quantidade;

	private Double precoVenda;

	private Double percDesc;

	private Double valorBruto;

	private Double valorLiquido;

	// Relacionamento
	private PedidoResumoDTO2 pedido;

	private ProdutoResumoDTO2 produto;

	public ItemPedidoResumoDTO() {

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

	public PedidoResumoDTO2 getPedido() {
		return pedido;
	}

	public void setPedido(PedidoResumoDTO2 pedido) {
		this.pedido = pedido;
	}

	public ProdutoResumoDTO2 getProduto() {
		return produto;
	}

	public void setProduto(ProdutoResumoDTO2 produto) {
		this.produto = produto;
	}

}
