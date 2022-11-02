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
import br.com.residencia.ecommerce.dto.CategoriaDTO;
import br.com.residencia.ecommerce.dto.CategoriaResumoDTO;
import br.com.residencia.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping("/all")
	public ResponseEntity<List<CategoriaResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(categoriaService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaResumoDTO> saveDTO(@RequestBody CategoriaDTO objDTO) {
		return new ResponseEntity<>(categoriaService.saveDTO(objDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaResumoDTO> updateDTO(@RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.updateDTO(objDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = categoriaService.deleteDTO(id);
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
