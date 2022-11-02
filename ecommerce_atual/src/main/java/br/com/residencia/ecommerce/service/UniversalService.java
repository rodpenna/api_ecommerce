package br.com.residencia.ecommerce.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.ecommerce.dto.CategoriaDTO;
import br.com.residencia.ecommerce.dto.CategoriaResumoDTO;
import br.com.residencia.ecommerce.dto.CategoriaResumoDTO2;
import br.com.residencia.ecommerce.dto.ClienteDTO;
import br.com.residencia.ecommerce.dto.ClienteResumoDTO;
import br.com.residencia.ecommerce.dto.ClienteResumoDTO2;
import br.com.residencia.ecommerce.dto.ConsultaCEPDTO;
import br.com.residencia.ecommerce.dto.EnderecoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO;
import br.com.residencia.ecommerce.dto.EnderecoResumoDTO2;
import br.com.residencia.ecommerce.dto.ItemPedidoDTO;
import br.com.residencia.ecommerce.dto.ItemPedidoResumoDTO;
import br.com.residencia.ecommerce.dto.ItemPedidoResumoDTO2;
import br.com.residencia.ecommerce.dto.ListaRelatorioDTO;
import br.com.residencia.ecommerce.dto.PedidoResumoDTO;
import br.com.residencia.ecommerce.dto.PedidoResumoDTO2;
import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.dto.ProdutoResumoDTO;
import br.com.residencia.ecommerce.dto.ProdutoResumoDTO2;
import br.com.residencia.ecommerce.dto.RelatorioDTO;
import br.com.residencia.ecommerce.entity.Categoria;
import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.CategoriaRepository;
import br.com.residencia.ecommerce.repository.ClienteRepository;
import br.com.residencia.ecommerce.repository.EnderecoRepository;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;
import br.com.residencia.ecommerce.repository.PedidoRepository;
import br.com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class UniversalService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EmailService emailService;

// ------------------------------------------
	public ConsultaCEPDTO consultaCepApiExterna(String cep) {
		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://viacep.com.br/ws/{cep}/json/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		ConsultaCEPDTO consultaCEPjDTO = restTemplate.getForObject(uri, ConsultaCEPDTO.class, params);

		return consultaCEPjDTO;
	}

	public Endereco toEnderecoEntity(EnderecoDTO objDTO) {

		Endereco obj = new Endereco();

		if (objDTO != null) {
			if (objDTO.getIdEndereco() == null || objDTO.getIdEndereco() == 0) {
				obj.setBairro(objDTO.getBairro());
				obj.setCep(objDTO.getCep());
				obj.setCidade(objDTO.getCidade());
				obj.setComplemento(objDTO.getComplemento());
				obj.setNumero(objDTO.getNumero());
				obj.setRua(objDTO.getRua());
				obj.setUf(objDTO.getUf());
			} else {
				obj.setIdEndereco(objDTO.getIdEndereco());
				obj.setBairro(objDTO.getBairro());
				obj.setCep(objDTO.getCep());
				obj.setCidade(objDTO.getCidade());
				obj.setComplemento(objDTO.getComplemento());
				obj.setNumero(objDTO.getNumero());
				obj.setRua(objDTO.getRua());
				obj.setUf(objDTO.getUf());
			}

			obj.setCliente(null);
		}
		return obj;
	}

	public EnderecoResumoDTO toEnderecoDTO(Endereco obj) {
		EnderecoResumoDTO novoDTO = new EnderecoResumoDTO();

		if (obj != null) {
			if (obj.getIdEndereco() == null || obj.getIdEndereco() == 0) {

				novoDTO.setBairro(obj.getBairro());
				novoDTO.setCep(obj.getCep());
				novoDTO.setCidade(obj.getCidade());
				novoDTO.setComplemento(obj.getComplemento());
				novoDTO.setNumero(obj.getNumero());
				novoDTO.setRua(obj.getRua());
				novoDTO.setUf(obj.getUf());
			} else {
				novoDTO.setIdEndereco(obj.getIdEndereco());
				novoDTO.setBairro(obj.getBairro());
				novoDTO.setCep(obj.getCep());
				novoDTO.setCidade(obj.getCidade());
				novoDTO.setComplemento(obj.getComplemento());
				novoDTO.setNumero(obj.getNumero());
				novoDTO.setRua(obj.getRua());
				novoDTO.setUf(obj.getUf());
			}
			Cliente cliente = clienteRepository.findByEndereco(obj);
			ClienteResumoDTO2 clienteResumoDTO = new ClienteResumoDTO2();
			if (cliente != null) {
				clienteResumoDTO.setCpf(cliente.getCpf());
				clienteResumoDTO.setDataNascimento(cliente.getDataNascimento());
				clienteResumoDTO.setEmail(cliente.getEmail());
				clienteResumoDTO.setIdCliente(cliente.getIdCliente());
				clienteResumoDTO.setNomeCompleto(cliente.getNomeCompleto());
				clienteResumoDTO.setTelefone(cliente.getTelefone());
			}
			novoDTO.setCliente(clienteResumoDTO);
		}
		return novoDTO;
	}

	public EnderecoResumoDTO2 exibirEndereco(Endereco obj) {
		EnderecoResumoDTO2 novoDTO = new EnderecoResumoDTO2();

		if (obj != null) {
			if (obj.getIdEndereco() == null || obj.getIdEndereco() == 0) {

				novoDTO.setBairro(obj.getBairro());
				novoDTO.setCep(obj.getCep());
				novoDTO.setCidade(obj.getCidade());
				novoDTO.setComplemento(obj.getComplemento());
				novoDTO.setNumero(obj.getNumero());
				novoDTO.setRua(obj.getRua());
				novoDTO.setUf(obj.getUf());
			} else {
				novoDTO.setIdEndereco(obj.getIdEndereco());
				novoDTO.setBairro(obj.getBairro());
				novoDTO.setCep(obj.getCep());
				novoDTO.setCidade(obj.getCidade());
				novoDTO.setComplemento(obj.getComplemento());
				novoDTO.setNumero(obj.getNumero());
				novoDTO.setRua(obj.getRua());
				novoDTO.setUf(obj.getUf());
			}
		}
		return novoDTO;
	}

	public Endereco saveEndereco(Endereco obj) {

		Endereco novoObj = new Endereco();
		if (obj != null) {
			novoObj.setBairro(obj.getBairro());
			novoObj.setCep(obj.getCep());
			novoObj.setCidade(obj.getCidade());
			novoObj.setComplemento(obj.getComplemento());
			novoObj.setNumero(obj.getNumero());
			novoObj.setRua(obj.getRua());
			novoObj.setUf(obj.getUf());
		}
		return novoObj;
	}

	public List<EnderecoResumoDTO> getAllEndereco() {
		List<EnderecoResumoDTO> listaObjDTO = new ArrayList<>();
		List<Endereco> listaObj = enderecoRepository.findAll();
		for (Endereco obj : listaObj) {
			EnderecoResumoDTO objDTO = new EnderecoResumoDTO();
			objDTO = toEnderecoDTO(obj);
			listaObjDTO.add(objDTO);
		}
		return listaObjDTO;
	}

	public Endereco saveEnderecoCEP(Endereco obj) {
		ConsultaCEPDTO endAP = consultaCepApiExterna(obj.getCep());
		Endereco endereco = new Endereco();

		endereco.setBairro(endAP.getBairro());
		endereco.setCep(endAP.getCep());
		endereco.setCidade(endAP.getLocalidade());
		endereco.setCliente(null);
		endereco.setComplemento(endAP.getComplemento());
		endereco.setIdEndereco(null);
		endereco.setNumero(obj.getNumero());
		endereco.setRua(endAP.getLogradouro());
		endereco.setUf(endAP.getUf());

		return endereco;
	}

// --------------------------------------------------------------------
	public Cliente toClienteEntity(ClienteDTO objDTO) {

		Cliente obj = new Cliente();

		if (objDTO != null) {
			if (objDTO.getIdCliente() == null || objDTO.getIdCliente() == 0) {

				obj.setCpf(objDTO.getCpf());
				obj.setDataNascimento(objDTO.getDataNascimento());
				obj.setEmail(objDTO.getEmail());
				obj.setNomeCompleto(objDTO.getNomeCompleto());
				obj.setTelefone(objDTO.getTelefone());
			} else {
				obj.setIdCliente(objDTO.getIdCliente());
				obj.setCpf(objDTO.getCpf());
				obj.setDataNascimento(objDTO.getDataNascimento());
				obj.setEmail(objDTO.getEmail());
				obj.setNomeCompleto(objDTO.getNomeCompleto());
				obj.setTelefone(objDTO.getTelefone());
			}

			Endereco endereco = enderecoRepository.findById(objDTO.getEndereco().getIdEndereco()).orElse(null);
			obj.setEndereco(endereco);

			List<Pedido> listaPedido = new ArrayList<>();
			listaPedido = pedidoRepository.findByCliente(obj);
			obj.setPedido(listaPedido);
		}
		return obj;
	}

	public ClienteResumoDTO toClienteDTO(Cliente obj) {

		ClienteResumoDTO objDTO = new ClienteResumoDTO();
		if (obj != null) {
			if (obj.getIdCliente() == null || obj.getIdCliente() == 0) {

				objDTO.setCpf(obj.getCpf());
				objDTO.setDataNascimento(obj.getDataNascimento());
				objDTO.setEmail(obj.getEmail());
				objDTO.setNomeCompleto(obj.getNomeCompleto());
				objDTO.setTelefone(obj.getTelefone());
			} else {
				objDTO.setIdCliente(obj.getIdCliente());
				objDTO.setCpf(obj.getCpf());
				objDTO.setDataNascimento(obj.getDataNascimento());
				objDTO.setEmail(obj.getEmail());
				objDTO.setNomeCompleto(obj.getNomeCompleto());
				objDTO.setTelefone(obj.getTelefone());
			}

			Endereco endereco = enderecoRepository.findById(obj.getEndereco().getIdEndereco()).orElse(null);
			EnderecoResumoDTO2 enderecoResumoDTO = new EnderecoResumoDTO2();

			enderecoResumoDTO.setBairro(endereco.getBairro());
			enderecoResumoDTO.setCep(endereco.getCep());
			enderecoResumoDTO.setCidade(endereco.getCidade());
			enderecoResumoDTO.setComplemento(endereco.getComplemento());
			enderecoResumoDTO.setNumero(endereco.getNumero());
			enderecoResumoDTO.setRua(endereco.getRua());
			enderecoResumoDTO.setUf(endereco.getUf());
			enderecoResumoDTO.setIdEndereco(endereco.getIdEndereco());

			objDTO.setEndereco(enderecoResumoDTO);

			List<PedidoResumoDTO2> listaPedidoResumoDTO = new ArrayList<>();
			atualizarPedidos();
			List<Pedido> listaPedido = pedidoRepository.findByCliente(obj);
			if (listaPedido != null) {
				for (Pedido pedido : listaPedido) {
					PedidoResumoDTO2 pedidoResumoDTO = new PedidoResumoDTO2();

					pedidoResumoDTO.setDataEntrega(pedido.getDataEntrega());
					pedidoResumoDTO.setDataEnvio(pedido.getDataEnvio());
					pedidoResumoDTO.setDataPedido(pedido.getDataPedido());
					pedidoResumoDTO.setIdPedido(pedido.getIdPedido());
					pedidoResumoDTO.setStatus(pedido.getStatus());
					pedidoResumoDTO.setValorTotal(pedido.getValorTotal());
					listaPedidoResumoDTO.add(pedidoResumoDTO);
				}
			}
			objDTO.setPedido(listaPedidoResumoDTO);
		}
		return objDTO;
	}

	public Cliente saveCliente(Cliente obj) {
		Cliente objFinal = new Cliente();
		if (obj != null) {
			if (obj.getIdCliente() == null || obj.getIdCliente() == 0) {

				Endereco end = enderecoRepository.findById(obj.getEndereco().getIdEndereco()).orElse(null);
				objFinal.setEndereco(end);

				objFinal.setPedido(null);

				objFinal.setCpf(obj.getCpf());
				objFinal.setDataNascimento(obj.getDataNascimento());
				objFinal.setEmail(obj.getEmail());
				objFinal.setNomeCompleto(obj.getNomeCompleto());
				objFinal.setTelefone(obj.getTelefone());
			}
		}

		return objFinal;
	}

	public Cliente updateCliente(Cliente obj, Integer id) {
		Cliente objFinal = new Cliente();

		Cliente objExistente = clienteRepository.findById(id).get();

		Endereco endereco = enderecoRepository.findById(obj.getEndereco().getIdEndereco()).orElse(null);

		if (obj != null && objExistente != null) {

			objFinal.setCpf(obj.getCpf());
			objFinal.setIdCliente(id);
			objFinal.setDataNascimento(obj.getDataNascimento());
			objFinal.setEmail(obj.getEmail());
			objFinal.setNomeCompleto(obj.getNomeCompleto());
			objFinal.setTelefone(obj.getTelefone());
			objFinal.setEndereco(endereco);
		} else {

		}
		return objFinal;
	}

// ---------------------------------------------------------------------------------
	public List<PedidoResumoDTO> getAllPedidoDTO() {
		List<Pedido> listaObj = pedidoRepository.findAll();
		List<PedidoResumoDTO> listaObjDTO = new ArrayList<>();
		for (Pedido p : listaObj) {
			PedidoResumoDTO objResumo = new PedidoResumoDTO();
			objResumo = toPedidoDTO(p);
			listaObjDTO.add(objResumo);
		}
		return listaObjDTO;
	}

	public Pedido savePedido(Pedido obj) {
		Pedido pedidoFinal = new Pedido();
		if (obj != null) {

			Cliente cliente = clienteRepository.findById(obj.getCliente().getIdCliente()).orElse(null);

			if (obj.getIdPedido() == null || obj.getIdPedido() == 0) {

				List<ItemPedido> listaItemPedido = obj.getItemPedido();
				Double total = 0.0;

				for (ItemPedido item : listaItemPedido) {
					Produto produto = new Produto();
					produto = produtoRepository.findById(item.getProduto().getIdProduto()).orElse(null);
					item.setProduto(produto);

					item.setValorLiquido(produto.getValorUnitario() * item.getQuantidade() * (1 - item.getPercDesc()));
					total += item.getValorLiquido();
				}

				if (obj.getDataEnvio() == null && obj.getDataEntrega() == null) {
					pedidoFinal.setDataPedido(obj.getDataPedido());
					pedidoFinal.setDataEntrega(obj.getDataPedido().plus(30, ChronoUnit.DAYS));
					pedidoFinal.setDataEnvio(obj.getDataPedido().plus(32, ChronoUnit.DAYS));
					pedidoFinal.setStatus(obj.getStatus());
				} else {
					pedidoFinal.setDataPedido(obj.getDataPedido());
					pedidoFinal.setDataEntrega(obj.getDataEntrega());
					pedidoFinal.setDataEnvio(obj.getDataEnvio());
					pedidoFinal.setStatus(obj.getStatus());
				}

				pedidoFinal.setCliente(cliente);
				pedidoFinal.setValorTotal(total);
				pedidoFinal.setItemPedido(null);
			} else {
				List<ItemPedido> listaItemPedido = obj.getItemPedido();
				Double total = 0.0;
				List<ItemPedido> listaItemPedidoFinal = new ArrayList<>();

				Pedido pedido = new Pedido();
				pedido = pedidoRepository.findById(obj.getIdPedido()).orElse(null);

				for (ItemPedido item : listaItemPedido) {

					item.setPedido(pedido);

					Produto produto = new Produto();
					produto = produtoRepository.findById(item.getProduto().getIdProduto()).orElse(null);
					item.setProduto(produto);

					item.setPrecoVenda(produto.getValorUnitario());
					item.setValorBruto(produto.getValorUnitario() * item.getQuantidade());
					item.setValorLiquido(produto.getValorUnitario() * item.getQuantidade() * (1 - item.getPercDesc()));

					total += item.getValorLiquido();

					ItemPedido itemSalvo = itemPedidoRepository.save(item);

					listaItemPedidoFinal.add(itemSalvo);
				}

				pedidoFinal.setIdPedido(obj.getIdPedido());
				pedidoFinal.setCliente(cliente);
				pedidoFinal.setDataEntrega(obj.getDataEntrega());
				pedidoFinal.setDataEnvio(obj.getDataEnvio());
				pedidoFinal.setDataPedido(obj.getDataPedido());
				pedidoFinal.setStatus(obj.getStatus());
				pedidoFinal.setValorTotal(total);

				pedidoFinal.setItemPedido(listaItemPedidoFinal);
			}
		}

		return pedidoFinal;
	}

	public Pedido updatePedido(Pedido obj, Integer id) {
		Pedido pedidoFinal = new Pedido();

		Pedido objExistente = pedidoRepository.findById(id).get();

		Cliente cliente = clienteRepository.findById(obj.getCliente().getIdCliente()).orElse(null);

		if (obj != null && objExistente != null) {

			pedidoFinal.setCliente(cliente);
			pedidoFinal.setDataEntrega(obj.getDataEntrega());
			pedidoFinal.setDataEnvio(obj.getDataEnvio());
			pedidoFinal.setDataPedido(obj.getDataPedido());
			pedidoFinal.setIdPedido(id);
			pedidoFinal.setStatus(obj.getStatus());

			List<ItemPedido> listaItem = objExistente.getItemPedido();
			List<ItemPedido> listaItemNova = obj.getItemPedido();

			if (listaItem != null) {
				for (ItemPedido itemNovo : listaItemNova) {
					Boolean isNew = true;
					ItemPedido itemFinal = new ItemPedido();
					for (ItemPedido item : listaItem) {
						if (item.getProduto().getIdProduto() == itemNovo.getProduto().getIdProduto()) {
							isNew = false;
							Produto produto = produtoRepository.findById(item.getProduto().getIdProduto()).orElse(null);
							itemFinal.setProduto(produto);

							itemFinal.setIdItemPedido(item.getIdItemPedido());
							itemFinal.setQuantidade(itemNovo.getQuantidade());
							itemFinal.setPercDesc(itemNovo.getPercDesc());
							itemFinal.setPrecoVenda(produto.getValorUnitario());
							itemFinal.setValorBruto(itemNovo.getQuantidade() * produto.getValorUnitario());
							itemFinal.setValorLiquido(itemNovo.getQuantidade() * produto.getValorUnitario()
									* (1 - itemNovo.getPercDesc()));
							itemFinal.setPedido(item.getPedido());
						}
					}

					if (isNew == true) {

						Produto produto = produtoRepository.findById(itemNovo.getProduto().getIdProduto()).orElse(null);
						itemFinal.setProduto(produto);

						itemFinal.setIdItemPedido(null);
						itemFinal.setQuantidade(itemNovo.getQuantidade());
						itemFinal.setPercDesc(itemNovo.getPercDesc());
						itemFinal.setPrecoVenda(produto.getValorUnitario());
						itemFinal.setValorBruto(itemNovo.getQuantidade() * produto.getValorUnitario());
						itemFinal.setValorLiquido(
								itemNovo.getQuantidade() * produto.getValorUnitario() * (1 - itemNovo.getPercDesc()));

						Pedido pedido = pedidoRepository.findById(id).orElse(null);
						itemFinal.setPedido(pedido);
					}
					itemPedidoRepository.save(itemFinal);
				}
			} else {

			}

			Pedido pedido = pedidoRepository.findById(id).orElse(null);
			List<ItemPedido> listaItemFinal = itemPedidoRepository.findByPedido(pedido);

			pedidoFinal.setItemPedido(listaItemFinal);
			Double total = 0.0;

			for (ItemPedido item : listaItemFinal) {
				total += item.getValorLiquido();
			}

			pedidoFinal.setValorTotal(total);

		}
		Pedido objAtualizado = pedidoRepository.save(objExistente);

		return objAtualizado;
	}

	public PedidoResumoDTO toPedidoDTO(Pedido obj) {

		PedidoResumoDTO objDTO = new PedidoResumoDTO();
		if (obj != null) {
			if (obj.getIdPedido() == null || obj.getIdPedido() == 0) {

				objDTO.setDataEntrega(obj.getDataEntrega());
				objDTO.setDataEnvio(obj.getDataEnvio());
				objDTO.setStatus(obj.getStatus());
				objDTO.setDataPedido(obj.getDataPedido());
			} else {
				objDTO.setIdPedido(obj.getIdPedido());
				objDTO.setDataEntrega(obj.getDataEntrega());
				objDTO.setDataEnvio(obj.getDataEnvio());
				objDTO.setStatus(obj.getStatus());
				objDTO.setDataPedido(obj.getDataPedido());
			}

			Cliente cliente = clienteRepository.findById(obj.getCliente().getIdCliente()).orElse(null);
			ClienteResumoDTO2 clienteResumoDTO = new ClienteResumoDTO2();

			clienteResumoDTO.setCpf(cliente.getCpf());
			clienteResumoDTO.setDataNascimento(cliente.getDataNascimento());
			clienteResumoDTO.setEmail(cliente.getEmail());
			clienteResumoDTO.setIdCliente(cliente.getIdCliente());
			clienteResumoDTO.setNomeCompleto(cliente.getNomeCompleto());
			clienteResumoDTO.setTelefone(cliente.getTelefone());

			objDTO.setCliente(clienteResumoDTO);

			List<ItemPedido> listaItemPedido = itemPedidoRepository.findByPedido(obj);
			List<ItemPedidoResumoDTO2> listaPedidoDTO = new ArrayList<>();
			Double total = 0.0;

			for (ItemPedido item : listaItemPedido) {
				ItemPedidoResumoDTO2 itemDTO = new ItemPedidoResumoDTO2();

				itemDTO.setIdItemPedido(item.getIdItemPedido());
				itemDTO.setPercDesc(item.getPercDesc());
				itemDTO.setPrecoVenda(item.getPrecoVenda());
				itemDTO.setQuantidade(item.getQuantidade());
				itemDTO.setValorBruto(item.getValorBruto());
				itemDTO.setValorLiquido(item.getValorLiquido());

				itemDTO.setIdProduto(item.getProduto().getIdProduto());

				itemDTO.setIdPedido(item.getPedido().getIdPedido());

				listaPedidoDTO.add(itemDTO);
				total += itemDTO.getValorLiquido();
			}
			objDTO.setValorTotal(total);
			objDTO.setItemPedido(listaPedidoDTO);
		}
		return objDTO;
	}

	public void atualizarPedidos() {
		List<Pedido> listaPedido = pedidoRepository.findAll();

		for (Pedido pedido : listaPedido) {
			List<ItemPedido> listaItem = itemPedidoRepository.findByPedido(pedido);
			Double total = 0.0;

			for (ItemPedido item : listaItem) {
				total += item.getValorLiquido();
			}
			pedido.setItemPedido(listaItem);
			pedido.setValorTotal(total);

			pedidoRepository.save(pedido);
		}
	}

	public void mandarEmail(Pedido ped) {
		RelatorioDTO relatorio = new RelatorioDTO();

		Pedido pedido = pedidoRepository.findById(ped.getIdPedido()).orElse(null);
		List<ItemPedido> listaPedido = itemPedidoRepository.findByPedido(pedido);
		pedido.setItemPedido(listaPedido);

		relatorio.setDataPedido(pedido.getDataPedido());
		relatorio.setIdPedido(pedido.getIdPedido());
		relatorio.setValorTotal(pedido.getValorTotal());

		List<ListaRelatorioDTO> listaRelatorio = new ArrayList<>();

		for (ItemPedido p : listaPedido) {
			ListaRelatorioDTO item = new ListaRelatorioDTO();
			item.setIdProduto(p.getProduto().getIdProduto());
			item.setNomeProduto(p.getProduto().getNome());
			item.setPercDesc(p.getPercDesc());
			item.setPrecoVenda(p.getPrecoVenda());
			item.setValorBruto(p.getValorBruto());
			item.setValorLiquido(p.getValorLiquido());

			listaRelatorio.add(item);
		}
		relatorio.setListaPedido(listaRelatorio);

		emailService.sendEmail("alveslisboa1995@gmail.com", "Teste de email", relatorio.toString());
		emailService.sendEmail("rodlpenna8@gmail.com", "Teste de email", relatorio.toString());
	}

// --------------------------------------------------------------------
	public ItemPedido toItemPedidoEntity(ItemPedidoDTO objDTO) {
		ItemPedido obj = new ItemPedido();

		if (objDTO != null) {

			Produto produto = produtoRepository.findById(objDTO.getProduto().getIdProduto()).orElse(null);
			obj.setProduto(produto);

			if (objDTO.getIdItemPedido() == null || objDTO.getIdItemPedido() == 0) {
				obj.setPercDesc(objDTO.getPercDesc());
				obj.setPrecoVenda(produto.getValorUnitario());
				obj.setQuantidade(objDTO.getQuantidade());
				obj.setValorBruto(produto.getValorUnitario() * objDTO.getQuantidade());
				obj.setValorLiquido((produto.getValorUnitario() * objDTO.getQuantidade()) * (1 - objDTO.getPercDesc()));
			} else {
				obj.setIdItemPedido(objDTO.getIdItemPedido());
				obj.setPercDesc(objDTO.getPercDesc());
				obj.setPrecoVenda(produto.getValorUnitario());
				obj.setQuantidade(objDTO.getQuantidade());
				obj.setValorBruto(produto.getValorUnitario() * objDTO.getQuantidade());
				obj.setValorLiquido((produto.getValorUnitario() * objDTO.getQuantidade()) * (1 - objDTO.getPercDesc()));
			}

			Pedido pedido = pedidoRepository.findById(objDTO.getPedido().getIdPedido()).orElse(null);
			Pedido pedidoNovo = new Pedido();
			if (pedido != null) {
				List<ItemPedido> itemPedido = pedido.getItemPedido();
				if (itemPedido != null) {

				}
			}
			obj.setPedido(pedidoNovo);
		}
		return obj;
	}

	public ItemPedidoResumoDTO toItemPedidoDTO(ItemPedido obj) {
		ItemPedidoResumoDTO objDTO = new ItemPedidoResumoDTO();

		if (obj != null) {

			Produto produto = produtoRepository.findById(obj.getProduto().getIdProduto()).orElse(null);
			ProdutoResumoDTO2 produtoResumoDTO = new ProdutoResumoDTO2();

			produtoResumoDTO.setDataCadastro(produto.getDataCadastro());
			produtoResumoDTO.setDescricao(produto.getDescricao());
			produtoResumoDTO.setIdProduto(produto.getIdProduto());
			produtoResumoDTO.setImagemFileName(produto.getImagemFileName());
			produtoResumoDTO.setImagemNome(produto.getImagemNome());
			produtoResumoDTO.setImagemUrl(produto.getImagemUrl());
			produtoResumoDTO.setNome(produto.getNome());
			produtoResumoDTO.setQntdEstoque(produto.getQntdEstoque());
			produtoResumoDTO.setValorUnitario(produto.getValorUnitario());

			objDTO.setProduto(produtoResumoDTO);

			if (obj.getIdItemPedido() == null || obj.getIdItemPedido() == 0) {
				objDTO.setPercDesc(obj.getPercDesc());
				objDTO.setPrecoVenda(produto.getValorUnitario());
				objDTO.setQuantidade(obj.getQuantidade());
				objDTO.setValorBruto(produto.getValorUnitario() * obj.getQuantidade());
				objDTO.setValorLiquido((produto.getValorUnitario() * obj.getQuantidade()) * (1 - obj.getPercDesc()));
			} else {
				objDTO.setIdItemPedido(obj.getIdItemPedido());
				objDTO.setPercDesc(obj.getPercDesc());
				objDTO.setPrecoVenda(produto.getValorUnitario());
				objDTO.setQuantidade(obj.getQuantidade());
				objDTO.setValorBruto(produto.getValorUnitario() * obj.getQuantidade());
				objDTO.setValorLiquido((produto.getValorUnitario() * obj.getQuantidade()) * (1 - obj.getPercDesc()));
			}

			Pedido pedido = pedidoRepository.findById(obj.getPedido().getIdPedido()).orElse(null);
			PedidoResumoDTO2 pedidoResumoDTO = new PedidoResumoDTO2();

			pedidoResumoDTO.setDataEntrega(pedido.getDataEntrega());
			pedidoResumoDTO.setDataEnvio(pedido.getDataEnvio());
			pedidoResumoDTO.setDataPedido(pedido.getDataPedido());
			pedidoResumoDTO.setIdPedido(pedido.getIdPedido());
			pedidoResumoDTO.setStatus(pedido.getStatus());
			pedidoResumoDTO.setValorTotal(pedido.getValorTotal());

			objDTO.setPedido(pedidoResumoDTO);
		}
		return objDTO;
	}

// --------------------------------------------------------------------
	public Produto toProdutoEntity(ProdutoDTO objDTO) {
		Produto obj = new Produto();

		if (objDTO != null) {
			if (objDTO.getIdProduto() == null || objDTO.getIdProduto() == 0) {
				obj.setDataCadastro(objDTO.getDataCadastro());
				obj.setDescricao(objDTO.getDescricao());
				obj.setImagemFileName(objDTO.getImagemFileName());
				obj.setImagemNome(objDTO.getImagemNome());
				obj.setImagemUrl(objDTO.getImagemUrl());
				obj.setNome(objDTO.getNome());
				obj.setQntdEstoque(objDTO.getQntdEstoque());
				obj.setValorUnitario(objDTO.getValorUnitario());
			} else {
				obj.setIdProduto(objDTO.getIdProduto());
				obj.setDataCadastro(objDTO.getDataCadastro());
				obj.setDescricao(objDTO.getDescricao());
				obj.setImagemFileName(objDTO.getImagemFileName());
				obj.setImagemNome(objDTO.getImagemNome());
				obj.setImagemUrl(objDTO.getImagemUrl());
				obj.setNome(objDTO.getNome());
				obj.setQntdEstoque(objDTO.getQntdEstoque());
				obj.setValorUnitario(objDTO.getValorUnitario());
			}

			Categoria categoria = categoriaRepository.findById(objDTO.getCategoria().getIdCategoria()).orElse(null);

			obj.setCategoria(categoria);

			obj.setItemPedido(null);
		}
		return obj;
	}

	public ProdutoResumoDTO toProdutoDTO(Produto obj) {
		ProdutoResumoDTO objDTO = new ProdutoResumoDTO();
		if (obj != null) {
			if (obj.getIdProduto() == null || obj.getIdProduto() == 0) {
				objDTO.setDataCadastro(obj.getDataCadastro());
				objDTO.setDescricao(obj.getDescricao());
				objDTO.setImagemFileName(obj.getImagemFileName());
				objDTO.setImagemNome(obj.getImagemNome());
				objDTO.setImagemUrl(obj.getImagemUrl());
				objDTO.setNome(obj.getNome());
				objDTO.setQntdEstoque(obj.getQntdEstoque());
				objDTO.setValorUnitario(obj.getValorUnitario());
			} else {
				objDTO.setIdProduto(obj.getIdProduto());
				objDTO.setDataCadastro(obj.getDataCadastro());
				objDTO.setDescricao(obj.getDescricao());
				objDTO.setImagemFileName(obj.getImagemFileName());
				objDTO.setImagemNome(obj.getImagemNome());
				objDTO.setImagemUrl(obj.getImagemUrl());
				objDTO.setNome(obj.getNome());
				objDTO.setQntdEstoque(obj.getQntdEstoque());
				objDTO.setValorUnitario(obj.getValorUnitario());
			}

			Categoria categoria = categoriaRepository.findById(obj.getCategoria().getIdCategoria()).orElse(null);
			CategoriaResumoDTO2 catDTO = new CategoriaResumoDTO2();

			catDTO.setDescricao(categoria.getDescricao());
			catDTO.setNome(categoria.getNome());
			catDTO.setIdCategoria(categoria.getIdCategoria());

			objDTO.setCategoria(catDTO);

			List<ItemPedido> listaItemPedido = itemPedidoRepository.findByProduto(obj);
			List<ItemPedidoResumoDTO2> listaItemPedidoDTO = new ArrayList<>();
			for (ItemPedido item : listaItemPedido) {
				ItemPedidoResumoDTO2 itemResumoDTO = new ItemPedidoResumoDTO2();

				itemResumoDTO.setIdItemPedido(item.getIdItemPedido());
				itemResumoDTO.setPercDesc(item.getPercDesc());
				itemResumoDTO.setPrecoVenda(item.getPrecoVenda());
				itemResumoDTO.setQuantidade(item.getQuantidade());
				itemResumoDTO.setValorBruto(item.getValorBruto());
				itemResumoDTO.setValorLiquido(item.getValorLiquido());

				listaItemPedidoDTO.add(itemResumoDTO);
			}

			objDTO.setItemPedido(listaItemPedidoDTO);
		}
		return objDTO;
	}

	public Produto saveProduto(Produto obj) {
		Produto produtoFinal = new Produto();
		if (obj != null) {
			if (obj.getIdProduto() == null || obj.getIdProduto() == 0) {

				Categoria categoria = categoriaRepository.findById(obj.getCategoria().getIdCategoria()).orElse(null);
				produtoFinal.setCategoria(categoria);

				produtoFinal.setItemPedido(null);

				produtoFinal.setDataCadastro(obj.getDataCadastro());
				produtoFinal.setDescricao(obj.getDescricao());
				produtoFinal.setImagemFileName(obj.getImagemFileName());
				produtoFinal.setImagemNome(obj.getImagemNome());
				produtoFinal.setImagemUrl(obj.getImagemUrl());
				produtoFinal.setNome(obj.getNome());
				produtoFinal.setQntdEstoque(obj.getQntdEstoque());
				produtoFinal.setValorUnitario(obj.getValorUnitario());
			}
		}

		return produtoFinal;
	}

// --------------------------------------------------------------------
	public CategoriaResumoDTO toCategoriaDTO(Categoria obj) {
		CategoriaResumoDTO objDTO = new CategoriaResumoDTO();
		if (obj != null) {
			if (obj.getIdCategoria() == null || obj.getIdCategoria() == 0) {
				objDTO.setDescricao(obj.getDescricao());
				objDTO.setNome(obj.getNome());
			} else {
				objDTO.setDescricao(obj.getDescricao());
				objDTO.setNome(obj.getNome());
				objDTO.setIdCategoria(obj.getIdCategoria());
			}
			List<Produto> listaProduto = produtoRepository.findByCategoria(obj);
			List<ProdutoResumoDTO2> listaProdResumoDTO = new ArrayList<>();
			for (Produto p : listaProduto) {
				ProdutoResumoDTO2 prodResumo = new ProdutoResumoDTO2();

				prodResumo.setDataCadastro(p.getDataCadastro());
				prodResumo.setDescricao(p.getDescricao());
				prodResumo.setIdProduto(p.getIdProduto());
				prodResumo.setImagemFileName(p.getImagemFileName());
				prodResumo.setImagemNome(p.getImagemNome());
				prodResumo.setImagemUrl(p.getImagemUrl());
				prodResumo.setNome(p.getNome());
				prodResumo.setQntdEstoque(p.getQntdEstoque());
				prodResumo.setValorUnitario(p.getValorUnitario());

				listaProdResumoDTO.add(prodResumo);
			}
			objDTO.setProduto(listaProdResumoDTO);
		}
		return objDTO;
	}

	public Categoria toCategoriaEntity(CategoriaDTO objDTO) {
		Categoria obj = new Categoria();
		if (objDTO != null) {
			if (objDTO.getIdCategoria() == null || objDTO.getIdCategoria() == 0) {
				obj.setDescricao(objDTO.getDescricao());
				obj.setNome(objDTO.getNome());
			} else {
				obj.setDescricao(objDTO.getDescricao());
				obj.setNome(objDTO.getNome());
				obj.setIdCategoria(objDTO.getIdCategoria());
			}
			List<Produto> listaProduto = produtoRepository.findAll();
			List<Produto> listaFinal = new ArrayList<>();
			for (Produto p : listaProduto) {
				if (p.getCategoria().getIdCategoria() == objDTO.getIdCategoria()) {
					listaFinal.add(p);
				}
			}
			obj.setProduto(listaFinal);
		}
		return obj;
	}
}
