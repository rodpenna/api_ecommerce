package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.EnderecoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO2;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UniversalService utilService;

	public List<EnderecoResumoDTO> getAllDTO() {
		List<EnderecoResumoDTO> listaObjDTO = new ArrayList<>();
		listaObjDTO = utilService.getAllEndereco();
		return listaObjDTO;
	}

	public EnderecoResumoDTO getByIdDTO(Integer id) {
		Endereco obj = enderecoRepository.findById(id).orElse(null);
		return utilService.toEnderecoDTO(obj);
	}

	public EnderecoResumoDTO saveDTO(Endereco obj) {
		Endereco objFinal = new Endereco();
		if (obj != null) {
			Endereco objNovo = utilService.saveEndereco(obj);
			objFinal = enderecoRepository.save(objNovo);
		}
		return utilService.toEnderecoDTO(objFinal);
	}

	public EnderecoResumoDTO2 saveCEPDTO(Endereco obj) {
		Endereco objFinal = new Endereco();
		if (obj != null) {
			Endereco objNovo = utilService.saveEnderecoCEP(obj);
			objFinal = enderecoRepository.save(objNovo);
		}
		return utilService.exibirEndereco(objFinal);
	}

	public EnderecoResumoDTO2 updateDTO(EnderecoDTO objDTO, Integer id) {
		Endereco objExistente = enderecoRepository.findById(id).get();
		Endereco objAtualizado = new Endereco();
		if (objDTO != null && objExistente != null) {

			Endereco objNovo = utilService.toEnderecoEntity(objDTO);
			objExistente.setBairro(objNovo.getBairro());
			objExistente.setCep(objNovo.getCep());
			objExistente.setCidade(objNovo.getCidade());
			objExistente.setComplemento(objNovo.getComplemento());
			objExistente.setNumero(objNovo.getNumero());
			objExistente.setRua(objNovo.getRua());
			objExistente.setUf(objNovo.getUf());
			objExistente.setIdEndereco(id);

			objAtualizado = enderecoRepository.save(objExistente);
		}
		return utilService.exibirEndereco(objAtualizado);
	}

	public Boolean deleteDTO(Integer id) {
		Endereco obj = enderecoRepository.findById(id).orElse(null);

		if (obj != null) {

			enderecoRepository.deleteById(id);

			Endereco objTeste = enderecoRepository.findById(id).orElse(null);

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
