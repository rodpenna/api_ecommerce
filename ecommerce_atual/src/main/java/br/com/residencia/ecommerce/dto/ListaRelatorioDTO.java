package br.com.residencia.ecommerce.dto;

public class ListaRelatorioDTO {

	private Integer idProduto;

	private String nomeProduto;

	private Double precoVenda;

	private Double valorBruto;

	private Double percDesc;

	private Double valorLiquido;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getPercDesc() {
		return percDesc;
	}

	public void setPercDesc(Double percDesc) {
		this.percDesc = percDesc;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	@Override
	public String toString() {
		return "\nId do Produto =" + idProduto + "\nNome do Produto =" + nomeProduto + "\nPreco de Venda =" + precoVenda
				+ "\nValor Bruto =" + valorBruto + "\nDesconto =" + percDesc + "\nValor Liquido=" + valorLiquido + "\n";
	}

}
