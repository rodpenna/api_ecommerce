package br.com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.ecommerce.entity.Categoria;
import br.com.residencia.ecommerce.entity.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	public List<Produto> findByCategoria(Categoria categoria);
}
