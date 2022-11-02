package br.com.residencia.ecommerce.dto;

import java.time.Instant;
import java.util.List;

public class ClienteResumoDTO {

	private Integer idCliente;

	private String email;

	private String nomeCompleto;

	private String cpf;

	private String telefone;

	private Instant dataNascimento;

	// Relacoes
	private EnderecoResumoDTO2 endereco;

	private List<PedidoResumoDTO2> pedido;

	public ClienteResumoDTO() {

	}

	// Get e Set

	public EnderecoResumoDTO2 getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResumoDTO2 endereco) {
		this.endereco = endereco;
	}

	public List<PedidoResumoDTO2> getPedido() {
		return pedido;
	}

	public void setPedido(List<PedidoResumoDTO2> pedido) {
		this.pedido = pedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
