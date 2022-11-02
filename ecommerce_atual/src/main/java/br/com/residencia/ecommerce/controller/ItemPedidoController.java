package br.com.residencia.ecommerce.controller;

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

import br.com.residencia.ecommerce.dto.ItemPedidoDTO;
import br.com.residencia.ecommerce.dto.ItemPedidoResumoDTO;
import br.com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping("/all")
	public ResponseEntity<List<ItemPedidoResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(itemPedidoService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ItemPedidoResumoDTO> saveDTO(@RequestBody ItemPedidoDTO objDTO) {
		return new ResponseEntity<>(itemPedidoService.saveDTO(objDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoResumoDTO> updateDTO(@RequestBody ItemPedidoDTO objDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.updateDTO(objDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = itemPedidoService.deleteDTO(id);
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
