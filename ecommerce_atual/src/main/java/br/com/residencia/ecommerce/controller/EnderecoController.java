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

import br.com.residencia.ecommerce.dto.EnderecoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO2;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping("/all")
	public ResponseEntity<List<EnderecoResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(enderecoService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EnderecoResumoDTO> saveDTO(@RequestBody Endereco obj) {
		return new ResponseEntity<>(enderecoService.saveDTO(obj), HttpStatus.CREATED);
	}

	@PostMapping("/save/cep")
	public ResponseEntity<EnderecoResumoDTO2> saveCEPDTO(@RequestBody Endereco obj) {
		return new ResponseEntity<>(enderecoService.saveCEPDTO(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EnderecoResumoDTO2> updateDTO(@RequestBody EnderecoDTO objDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.updateDTO(objDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = enderecoService.deleteDTO(id);
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
