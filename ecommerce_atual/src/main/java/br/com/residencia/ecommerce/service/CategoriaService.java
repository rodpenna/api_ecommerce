package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.residencia.ecommerce.dto.CategoriaDTO;
import br.com.residencia.ecommerce.dto.CategoriaResumoDTO;
import br.com.residencia.ecommerce.entity.Categoria;
import br.com.residencia.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	UniversalService utilService;

	public List<CategoriaResumoDTO> getAllDTO() {
		List<CategoriaResumoDTO> listaObjDTO = new ArrayList<>();
		List<Categoria> listaObj = categoriaRepository.findAll();
		for (Categoria obj : listaObj) {
			CategoriaResumoDTO objDTO = new CategoriaResumoDTO();
			objDTO = utilService.toCategoriaDTO(obj);
			listaObjDTO.add(objDTO);
		}
		return listaObjDTO;
	}

	public CategoriaResumoDTO getByIdDTO(Integer id) {
		Categoria obj = categoriaRepository.findById(id).orElse(null);
		return utilService.toCategoriaDTO(obj);
	}

	public CategoriaResumoDTO saveDTO(CategoriaDTO objDTO) {
		CategoriaResumoDTO objSalvoDTO = new CategoriaResumoDTO();
		if (objDTO != null) {
			Categoria obj = utilService.toCategoriaEntity(objDTO);
			Categoria objSalvo = categoriaRepository.save(obj);
			objSalvoDTO = utilService.toCategoriaDTO(objSalvo);
		}
		return objSalvoDTO;
	}

	public CategoriaResumoDTO updateDTO(CategoriaDTO objDTO, Integer id) {
		Categoria objExistente = categoriaRepository.findById(id).get();

		if (objDTO != null && objExistente != null) {
			Categoria objNovo = utilService.toCategoriaEntity(objDTO);
			objExistente.setDescricao(objNovo.getDescricao());
			objExistente.setIdCategoria(id);
			objExistente.setNome(objNovo.getNome());
			objExistente.setProduto(objNovo.getProduto());
		}
		Categoria objAtualizado = categoriaRepository.save(objExistente);
		return utilService.toCategoriaDTO(objAtualizado);
	}

	public Boolean deleteDTO(Integer id) {
		Categoria obj = categoriaRepository.findById(id).orElse(null);

		if (obj != null) {

			categoriaRepository.deleteById(id);

			Categoria objTeste = categoriaRepository.findById(id).orElse(null);

			if (objTeste == null) {

				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}
	}
}
