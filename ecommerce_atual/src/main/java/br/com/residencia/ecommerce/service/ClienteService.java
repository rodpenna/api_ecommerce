package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.ClienteResumoDTO;
import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	UniversalService utilService;

	public List<ClienteResumoDTO> getAllDTO() {
		List<ClienteResumoDTO> listaObjDTO = new ArrayList<>();
		List<Cliente> listaObj = clienteRepository.findAll();
		for (Cliente obj : listaObj) {
			ClienteResumoDTO objDTO = new ClienteResumoDTO();
			objDTO = utilService.toClienteDTO(obj);
			listaObjDTO.add(objDTO);
		}
		return listaObjDTO;
	}

	public ClienteResumoDTO getByIdDTO(Integer id) {
		Cliente obj = clienteRepository.findById(id).orElse(null);
		return utilService.toClienteDTO(obj);
	}

	public ClienteResumoDTO saveDTO(Cliente obj) {

		Cliente objFinal = new Cliente();
		if (obj != null) {

			Cliente objNovo = utilService.saveCliente(obj);

			objFinal = clienteRepository.save(objNovo);

		}
		return utilService.toClienteDTO(objFinal);
	}

	public ClienteResumoDTO updateDTO(Cliente obj, Integer id) {
		Cliente objAtualizado = utilService.updateCliente(obj, id);
		Cliente objFinal = clienteRepository.save(objAtualizado);
		return utilService.toClienteDTO(objFinal);
	}

	public Boolean deleteDTO(Integer id) {
		Cliente obj = clienteRepository.findById(id).orElse(null);

		if (obj != null) {

			clienteRepository.deleteById(id);

			Cliente objTeste = clienteRepository.findById(id).orElse(null);

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
