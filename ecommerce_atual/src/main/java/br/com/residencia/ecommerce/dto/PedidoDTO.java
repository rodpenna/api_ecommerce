package br.com.residencia.ecommerce.dto;

import java.time.Instant;
import java.util.List;

public class PedidoDTO {

	private Integer idPedido;

	private Instant dataPedido;

	private Instant dataEntrega;

	private Instant dataEnvio;

	private Boolean status;

	private Double valorTotal;

	// Relacionamento
	private List<ItemPedidoDTO> itemPedido;

	private ClienteDTO cliente;

	// Construtores
	public PedidoDTO() {

	}

	// Get e Set
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

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Instant getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Instant dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedidoDTO> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedidoDTO> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

}
