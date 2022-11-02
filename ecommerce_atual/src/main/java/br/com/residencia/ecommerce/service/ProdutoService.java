package br.com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.residencia.ecommerce.ImgBB.ImgBBDTO;
import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.dto.ProdutoResumoDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	UniversalService utilService;

	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;

	@Value("${imgbb.host.key}")
	private String imgBBHostKey;

	public ProdutoResumoDTO saveProdutoFoto(String produtoTxt, MultipartFile file) throws IOException {

		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = imgBBHostUrl + imgBBHostKey;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();

		ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("image")
				.filename(file.getOriginalFilename()).build();

		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

		HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", fileEntity);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<ImgBBDTO> response = null;
		ImgBBDTO imgDTO = new ImgBBDTO();
		Produto novaProduto = new Produto();

		try {

			response = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, ImgBBDTO.class);

			imgDTO = response.getBody();
			System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}

		if (null != imgDTO) {
			Produto produtoFromJson = convertProdutoFromStringJson(produtoTxt);
			produtoFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
			produtoFromJson.setImagemNome(imgDTO.getData().getTitle());
			produtoFromJson.setImagemUrl(imgDTO.getData().getUrl());
			novaProduto = produtoRepository.save(produtoFromJson);
		}
		return utilService.toProdutoDTO(novaProduto);
	}

	private Produto convertProdutoFromStringJson(String obj) {
		Produto produto = new Produto();

		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			objectMapper.registerModule(new JavaTimeModule());
			produto = objectMapper.readValue(obj, Produto.class);
		} catch (Exception err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Produto",
					err.toString());
		}
		return produto;
	}

	public List<ProdutoResumoDTO> getAllDTO() {
		List<ProdutoResumoDTO> listaObjDTO = new ArrayList<>();
		List<Produto> listaObj = produtoRepository.findAll();
		for (Produto obj : listaObj) {
			ProdutoResumoDTO objDTO = new ProdutoResumoDTO();
			objDTO = utilService.toProdutoDTO(obj);
			listaObjDTO.add(objDTO);
		}
		return listaObjDTO;
	}

	public ProdutoResumoDTO getByIdDTO(Integer id) {
		Produto obj = produtoRepository.findById(id).orElse(null);
		return utilService.toProdutoDTO(obj);
	}

	public ProdutoResumoDTO saveDTO(Produto obj) {
		Produto objFinal = new Produto();
		if (obj != null) {
			Produto objNovo = utilService.saveProduto(obj);
			objFinal = produtoRepository.save(objNovo);
		}
		return utilService.toProdutoDTO(objFinal);
	}

	public ProdutoResumoDTO updateDTO(ProdutoDTO objDTO, Integer id) {
		Produto objExistente = produtoRepository.findById(id).get();

		if (objDTO != null && objExistente != null) {
			Produto objNovo = utilService.toProdutoEntity(objDTO);

			objExistente.setCategoria(objNovo.getCategoria());
			objExistente.setDataCadastro(objNovo.getDataCadastro());
			objExistente.setDescricao(objNovo.getDescricao());
			objExistente.setImagemFileName(objNovo.getImagemFileName());
			objExistente.setImagemNome(objNovo.getImagemNome());
			objExistente.setImagemUrl(objNovo.getImagemUrl());
			objExistente.setNome(objNovo.getNome());
			objExistente.setQntdEstoque(objNovo.getQntdEstoque());
			objExistente.setValorUnitario(objNovo.getValorUnitario());
			objExistente.setIdProduto(id);

			// POPULATE ITEMPEDIDO
			objExistente.setItemPedido(objNovo.getItemPedido());
		} else {

		}
		Produto objAtualizado = produtoRepository.save(objExistente);
		return utilService.toProdutoDTO(objAtualizado);
	}

	public Boolean deleteDTO(Integer id) {
		Produto obj = produtoRepository.findById(id).orElse(null);

		if (obj != null) {

			produtoRepository.deleteById(id);

			Produto objTeste = produtoRepository.findById(id).orElse(null);

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
