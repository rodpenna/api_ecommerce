package br.com.residencia.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.residencia.ecommerce.dto.ClienteResumoDTO;
import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping("/all")
	public ResponseEntity<List<ClienteResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(clienteService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteResumoDTO> saveDTO(@Valid @RequestBody Cliente obj) {
		return new ResponseEntity<>(clienteService.saveDTO(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResumoDTO> updateDTO(@RequestBody Cliente obj, @PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.updateDTO(obj, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = clienteService.deleteDTO(id);
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
