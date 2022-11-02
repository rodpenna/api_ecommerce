package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.PedidoResumoDTO;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;
import br.com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	UniversalService utilService;

	@Autowired
	EmailService emailService;

	public List<PedidoResumoDTO> getAllDTO() {
		List<PedidoResumoDTO> listaObjDTO = new ArrayList<>();
		listaObjDTO = utilService.getAllPedidoDTO();
		return listaObjDTO;
	}

	public PedidoResumoDTO getByIdDTO(Integer id) {
		Pedido obj = pedidoRepository.findById(id).orElse(null);
		return utilService.toPedidoDTO(obj);
	}

	public PedidoResumoDTO saveDTO(Pedido obj) {
		Pedido objFinal = new Pedido();
		if (obj != null) {

			Pedido objNovo = utilService.savePedido(obj);

			Pedido objSalvo = pedidoRepository.save(objNovo);

			objSalvo.setItemPedido(obj.getItemPedido());
			objSalvo.setCliente(obj.getCliente());
			Pedido objCorrigido = utilService.savePedido(objSalvo);

			objFinal = pedidoRepository.save(objCorrigido);
		}

		utilService.mandarEmail(objFinal);

		return utilService.toPedidoDTO(objFinal);
	}

	public PedidoResumoDTO updateDTO(Pedido obj, Integer id) {
		Pedido objAtualizado = utilService.updatePedido(obj, id);

		return utilService.toPedidoDTO(objAtualizado);
	}

	public Boolean deleteDTO(Integer id) {
		Pedido obj = pedidoRepository.findById(id).orElse(null);

		if (obj != null) {
			List<ItemPedido> listaItem = obj.getItemPedido();
			for (ItemPedido item : listaItem) {
				itemPedidoRepository.delete(item);
			}

			pedidoRepository.deleteById(id);

			Pedido objTeste = pedidoRepository.findById(id).orElse(null);

			if (objTeste == null) {

				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}
	}

	public Boolean deleteItem(Pedido obj, Integer id) {
		Boolean deletado = false;
		Pedido pedido = pedidoRepository.findById(obj.getIdPedido()).orElse(null);
		List<ItemPedido> listaItem = pedido.getItemPedido();

		for (ItemPedido item : listaItem) {
			if (item.getIdItemPedido() == id) {
				itemPedidoRepository.delete(item);
				deletado = true;
			}
		}
		return deletado;
	}

}
