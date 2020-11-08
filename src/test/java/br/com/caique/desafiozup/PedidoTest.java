package br.com.caique.desafiozup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.PedidoAtualizacaoForm;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import br.com.caique.desafiozup.service.PedidoService;
import br.com.caique.desafiozup.service.ProdutoService;

@SpringBootTest
@Transactional
public class PedidoTest {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	FabricanteRepository fabricanteRepository;

	PedidoService pedidoService;
	ProdutoService produtoService;

	public static final String nomeCliente = "Caique";
	public static final Double desconto = 0.2;
	public static final String telefone = "85999680601";
	public static final BigDecimal valorTotalComDescontoEsperado = new BigDecimal("8.000");
	public static final BigDecimal preco = new BigDecimal("20.00");

	@BeforeEach
	void initUseCase() {
		pedidoService = new PedidoService(produtoRepository, pedidoRepository);
		produtoService = new ProdutoService(produtoRepository, fabricanteRepository);
	}

	@DisplayName("Testar listagem de pedido")
	@Test
	public void testListar() {
		Page<PedidoDto> pedidoDtoList = pedidoService.listar(ObjectUtilTest.pagina, ObjectUtilTest.quantidade);
		assertEquals(ObjectUtilTest.quantidade, pedidoDtoList.getSize());
	}

	@DisplayName("Testar cadastro de pedido")
	@Test
	void testCadastrar() {
		PedidoForm pedidoForm = getPedidoForm();

		PedidoDto pedidoDto = pedidoService.cadastrar(pedidoForm);

		verificarDadosPedido(pedidoDto);
	}

	@DisplayName("Testar remoção de pedido")
	@Test
	void testRemover() {
		Long id = cadastrarPedido();

		assertNotNull(id);

		Optional<PedidoDto> pedidoOptional = pedidoService.detalhar(id);

		verificarDadosPedido(pedidoOptional.get());

		Boolean retorno = pedidoService.remover(id);

		assertTrue(retorno);
	}

	@DisplayName("Testar remoção de pedido inexistente")
	@Test
	void testRemoverPedidoInexistente() {
		Long id = 0L;

		Optional<PedidoDto> pedidoOptional = pedidoService.detalhar(id);

		assertTrue(pedidoOptional.isEmpty());

		Boolean retorno = pedidoService.remover(id);

		assertFalse(retorno);
	}

	@DisplayName("Testar atualização de pedido")
	@Test
	void testAtualizar() {

		Long id = cadastrarPedido();

		assertNotNull(id);

		Optional<PedidoDto> pedidoOptionalAntesAlteracao = pedidoService.detalhar(id);

		verificarDadosPedido(pedidoOptionalAntesAlteracao.get());

		PedidoAtualizacaoForm pedidoAtualizacaoForm = new PedidoAtualizacaoForm();
		pedidoAtualizacaoForm.setDesconto(0.3);
		List<Long> produtos = new ArrayList<>();
		produtos.add(cadastrarProduto(preco));
		pedidoAtualizacaoForm.setProdutos(produtos);

		Optional<PedidoDto> pedidoOptionalDepoisAlteracao = pedidoService.atualizar(id, pedidoAtualizacaoForm);

		assertNotEquals(pedidoOptionalAntesAlteracao.get().getDesconto(),
				pedidoOptionalDepoisAlteracao.get().getDesconto());
		assertNotEquals(pedidoOptionalAntesAlteracao.get().getProdutos().get(0).getPreco(),
				pedidoOptionalDepoisAlteracao.get().getProdutos().get(0).getPreco());
		assertEquals(pedidoOptionalAntesAlteracao.get().getId(), pedidoOptionalDepoisAlteracao.get().getId());

	}

	@DisplayName("Testar atualização de pedido inexistente")
	@Test
	void testAtualizarInexistente() {

		Long id = 0L;

		Optional<PedidoDto> pedidoOptionalAntesAlteracao = pedidoService.detalhar(id);

		assertTrue(pedidoOptionalAntesAlteracao.isEmpty());

		PedidoAtualizacaoForm pedidoAtualizacaoForm = new PedidoAtualizacaoForm();
		pedidoAtualizacaoForm.setDesconto(0.3);
		List<Long> produtos = new ArrayList<>();
		produtos.add(cadastrarProduto(preco));
		pedidoAtualizacaoForm.setProdutos(produtos);

		Optional<PedidoDto> pedidoOptionalDepoisAlteracao = pedidoService.atualizar(id, pedidoAtualizacaoForm);

		assertTrue(pedidoOptionalDepoisAlteracao.isEmpty());

	}

	@DisplayName("Testar detalhes de pedido")
	@Test
	public void testDetalhar() {
		Long id = cadastrarPedido();

		Optional<PedidoDto> pedidoOptional = pedidoService.detalhar(id);

		verificarDadosPedido(pedidoOptional.get());

	}

	private PedidoForm getPedidoForm() {
		PedidoForm pedidoForm = new PedidoForm();
		pedidoForm.setCliente(nomeCliente);
		pedidoForm.setTelefone(telefone);
		pedidoForm.setDesconto(desconto);
		List<Long> produtos = new ArrayList<>();
		Long id = cadastrarProduto(ObjectUtilTest.preco);
		produtos.add(id);
		pedidoForm.setProdutos(produtos);
		return pedidoForm;
	}

	private void verificarDadosPedido(PedidoDto pedidoDto) {
		assertNotNull(pedidoDto.getId());
		assertEquals(nomeCliente, pedidoDto.getCliente());
		assertEquals(telefone, pedidoDto.getTelefone());
		assertEquals(desconto, pedidoDto.getDesconto());
		assertNotNull(pedidoDto.getProdutos().get(0).getId());
		assertEquals(ObjectUtilTest.preco, pedidoDto.getProdutos().get(0).getPreco());
		assertEquals(valorTotalComDescontoEsperado, pedidoDto.getValorTotal());
	}

	private Long cadastrarProduto(BigDecimal preco) {
		ProdutoForm produtoForm = new ProdutoForm(ObjectUtilTest.descricao, ObjectUtilTest.sku, ObjectUtilTest.peso,
				ObjectUtilTest.getDimensoes(), ObjectUtilTest.getFabricante(), preco);
		Optional<ProdutoDto> produtoOptional = produtoService.cadastrar(produtoForm);
		return produtoOptional.get().getId();
	}

	private Long cadastrarPedido() {
		PedidoForm pedidoForm = getPedidoForm();
		PedidoDto pedidoDto = pedidoService.cadastrar(pedidoForm);
		return pedidoDto.getId();
	}

}
