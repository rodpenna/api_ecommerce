package br.com.residencia.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.dto.ProdutoResumoDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@PostMapping(value = "/cadastro-produto-com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ProdutoResumoDTO> saveProdutoFoto(@RequestPart("produto") String produtoTxt,
			@RequestPart("filename") MultipartFile file) throws IOException {
		ProdutoResumoDTO produtoDTO = produtoService.saveProdutoFoto(produtoTxt, file);
		if (produtoDTO == null)
			return new ResponseEntity<>(produtoDTO, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProdutoResumoDTO>> getAllDTO() {
		return new ResponseEntity<>(produtoService.getAllDTO(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResumoDTO> getByIdDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.getByIdDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProdutoResumoDTO> saveDTO(@RequestBody Produto obj) {
		return new ResponseEntity<>(produtoService.saveDTO(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResumoDTO> updateDTO(@RequestBody ProdutoDTO objDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.updateDTO(objDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDTO(@PathVariable Integer id) {
		Boolean resp = produtoService.deleteDTO(id);
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
