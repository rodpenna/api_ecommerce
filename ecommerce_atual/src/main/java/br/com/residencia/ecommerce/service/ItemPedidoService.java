package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.ItemPedidoDTO;
import br.com.residencia.ecommerce.dto.ItemPedidoResumoDTO;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	UniversalService utilService;

	public List<ItemPedidoResumoDTO> getAllDTO() {
		List<ItemPedidoResumoDTO> listaItemPedidoDTO = new ArrayList<>();
		List<ItemPedido> listaItemPedido = itemPedidoRepository.findAll();

		for (ItemPedido obj : listaItemPedido) {
			ItemPedidoResumoDTO objDTO = new ItemPedidoResumoDTO();
			objDTO = utilService.toItemPedidoDTO(obj);
			listaItemPedidoDTO.add(objDTO);
		}
		return listaItemPedidoDTO;
	}

	public ItemPedidoResumoDTO getByIdDTO(Integer id) {
		ItemPedido obj = itemPedidoRepository.findById(id).orElse(null);
		return utilService.toItemPedidoDTO(obj);
	}

	public ItemPedidoResumoDTO saveDTO(ItemPedidoDTO objDTO) {
		ItemPedido objSalvo = new ItemPedido();
		if (objDTO != null) {
			ItemPedido obj = utilService.toItemPedidoEntity(objDTO);
			objSalvo = itemPedidoRepository.save(obj);
		}
		return utilService.toItemPedidoDTO(objSalvo);
	}

	public ItemPedidoResumoDTO updateDTO(ItemPedidoDTO objDTO, Integer id) {
		ItemPedido objExistente = itemPedidoRepository.findById(id).orElse(null);
		if (objDTO != null && objExistente != null) {
			ItemPedido objNovo = utilService.toItemPedidoEntity(objDTO);

			objExistente.setIdItemPedido(id);
			objExistente.setPedido(objNovo.getPedido());
			objExistente.setPercDesc(objNovo.getPercDesc());
			objExistente.setPrecoVenda(objNovo.getPrecoVenda());
			objExistente.setProduto(objNovo.getProduto());
			objExistente.setQuantidade(objNovo.getQuantidade());
			objExistente.setValorBruto(objNovo.getValorBruto());
			objExistente.setValorLiquido(objNovo.getValorLiquido());
		}
		ItemPedido objAtualizado = itemPedidoRepository.save(objExistente);

		return utilService.toItemPedidoDTO(objAtualizado);
	}

	public Boolean deleteDTO(Integer id) {
		ItemPedido obj = itemPedidoRepository.findById(id).orElse(null);

		if (obj != null) {

			itemPedidoRepository.deleteById(id);

			ItemPedido objTeste = itemPedidoRepository.findById(id).orElse(null);

			if (objTeste == null) {

				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}
	}
	// ---------------------
}
