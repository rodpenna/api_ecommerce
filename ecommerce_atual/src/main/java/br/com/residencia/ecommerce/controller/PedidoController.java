package br.com.residencia.ecommerce.controller;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.residencia.ecommerce.dto.PedidoResumoDTO;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping("/all")
	public ResponseEntity<List<PedidoResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(pedidoService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(pedidoService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PedidoResumoDTO> saveDTO(@RequestBody Pedido obj) {

		Instant data = Instant.now();

		if ((obj.getDataEntrega() != null) && (obj.getDataEnvio() != null)) {
			if (data.compareTo(obj.getDataPedido()) < 0 && (data.compareTo(obj.getDataEntrega()) < 0)
					&& (data.compareTo(obj.getDataEnvio()) < 0)) {

				return new ResponseEntity<>(pedidoService.saveDTO(obj), HttpStatus.CREATED);

			} else {

				return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			if (data.compareTo(obj.getDataPedido()) < 0) {
				return new ResponseEntity<>(pedidoService.saveDTO(obj), HttpStatus.CREATED);
			} else {

				return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
			}
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoResumoDTO> updateDTO(@RequestBody Pedido obj, @PathVariable Integer id) {
		return new ResponseEntity<>(pedidoService.updateDTO(obj, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = pedidoService.deleteDTO(id);
		if (resp == true) {
			return new ResponseEntity<>("Objeto deletado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Falha para deletar objeto", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/item")
	public ResponseEntity<String> deleteItemDTO(@RequestBody Pedido obj) {
		List<ItemPedido> listaPedido = obj.getItemPedido();
		Boolean resp = false;

		for (ItemPedido item : listaPedido) {
			resp = pedidoService.deleteItem(obj, item.getIdItemPedido());
		}
		if (resp == true) {
			return new ResponseEntity<>("Objeto deletado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Falha para deletar objeto", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/debug")
	public String getDebug() {
		return "O link esta funcionando";
	}
}
