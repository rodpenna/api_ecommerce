package br.com.residencia.ecommerce.dto;

import java.time.Instant;
import java.util.List;

public class RelatorioDTO {

	private Integer idPedido;

	private Instant dataPedido;

	private Double valorTotal;

	private List<ListaRelatorioDTO> listaPedido;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Instant getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Instant dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ListaRelatorioDTO> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<ListaRelatorioDTO> listaPedido) {
		this.listaPedido = listaPedido;
	}

	@Override
	public String toString() {
		return "Relatorio de Pedido: \n" + " Novo pedido recebido ! " + "\nDados do Pedido : " + "\nId do Pedido ="
				+ idPedido + "\nData do Pedido =" + dataPedido + "\nValor Total =" + valorTotal
				+ "\nLista de itens do pedido : \n" + listaPedido;
	}

}
