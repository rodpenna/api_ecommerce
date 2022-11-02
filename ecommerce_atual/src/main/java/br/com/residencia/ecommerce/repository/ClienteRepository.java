package br.com.residencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.entity.Pedido;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	public Cliente findByEndereco(Endereco endereco);
	
	public Cliente findByPedido(Pedido pedido);
}
