package br.com.caique.desafiozup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import br.com.caique.desafiozup.service.ProdutoService;

@SpringBootTest
@Transactional
class ProdutoTest {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	FabricanteRepository fabricanteRepository;

	ProdutoService produtoService;

	public static final String nomeFabricante = "Teste LTDA";

	@BeforeEach
	void initUseCase() {
		produtoService = new ProdutoService(produtoRepository, fabricanteRepository);
	}

	@DisplayName("Verificar listagem de produto")
	@Test
	public void testListar() {
		Page<ProdutoDto> produtoDtolist = produtoService.listar(ObjectUtilTest.pagina, ObjectUtilTest.quantidade);
		assertEquals(ObjectUtilTest.quantidade, produtoDtolist.getSize());
	}

	@DisplayName("Verificar cadastro de produto")
	@Test
	public void testCadastrar() {
		ProdutoForm produtoForm = new ProdutoForm(ObjectUtilTest.descricao, ObjectUtilTest.sku, ObjectUtilTest.peso,
				ObjectUtilTest.getDimensoes(), ObjectUtilTest.getFabricante(), ObjectUtilTest.preco);

		Optional<ProdutoDto> produtoOptional = produtoService.cadastrar(produtoForm);

		verificarDadosProduto(produtoOptional);
	}

	@DisplayName("Verificar detalhamento de produto")
	@Test
	public void testDetalhar() {
		Long id = cadastrarProduto();

		Optional<ProdutoDto> produtoOptional = produtoService.detalhar(id);

		verificarDadosProduto(produtoOptional);
	}

	@DisplayName("Verificar alteração de produto")
	@Test
	public void testAtualizar() {
		Long id = cadastrarProduto();

		Optional<ProdutoDto> produtoOptionalAntesAlteracao = produtoService.detalhar(id);
		verificarDadosProduto(produtoOptionalAntesAlteracao);

		ProdutoAtualizacaoForm produtoAtualizacaoForm = new ProdutoAtualizacaoForm();
		produtoAtualizacaoForm.setDescricao("Teste2");
		produtoAtualizacaoForm.setPeso(5.0);
		produtoAtualizacaoForm.setPreco(new BigDecimal("4.00"));

		Optional<ProdutoDto> produtoOptionalDepoisAlteracao = produtoService.atualizar(id, produtoAtualizacaoForm);

		assertNotEquals(produtoOptionalAntesAlteracao.get().getDescricao(),
				produtoOptionalDepoisAlteracao.get().getDescricao());
		assertNotEquals(produtoOptionalAntesAlteracao.get().getPreco(),
				produtoOptionalDepoisAlteracao.get().getPreco());

		assertEquals(produtoOptionalAntesAlteracao.get().getId(), produtoOptionalDepoisAlteracao.get().getId());
		assertEquals(produtoOptionalAntesAlteracao.get().getSku(), produtoOptionalDepoisAlteracao.get().getSku());
		assertEquals(produtoOptionalAntesAlteracao.get().getFabricante(),
				produtoOptionalDepoisAlteracao.get().getFabricante());
	}

	@DisplayName("Verificar alteração de produto inexistente")
	@Test
	public void testAtualizarProdutoInexistente() {
		Long id = 0L;

		ProdutoAtualizacaoForm produtoAtualizacaoForm = new ProdutoAtualizacaoForm();
		produtoAtualizacaoForm.setDescricao("Teste2");
		produtoAtualizacaoForm.setPeso(5.0);
		produtoAtualizacaoForm.setPreco(new BigDecimal("4.00"));

		Optional<ProdutoDto> produtoOptional = produtoService.atualizar(id, produtoAtualizacaoForm);

		assertTrue(produtoOptional.isEmpty());
	}

	@DisplayName("Testar remoção produto")
	@Test
	public void testRemover() {
		Long id = cadastrarProduto();

		Optional<ProdutoDto> produtoOptional = produtoService.detalhar(id);

		verificarDadosProduto(produtoOptional);

		Boolean retorno = produtoService.remover(produtoOptional.get().getId());

		assertTrue(retorno);

		produtoOptional = produtoService.detalhar(id);

		assertTrue(produtoOptional.isEmpty());
	}

	@DisplayName("Testar remoção de produto inexistente")
	@Test
	public void testRemoverProdutoInexistente() {
		Long id = 0L;

		Optional<ProdutoDto> produtoOptional = produtoService.detalhar(id);

		assertTrue(produtoOptional.isEmpty());

		Boolean retorno = produtoService.remover(id);

		assertFalse(retorno);

	}

	private Long cadastrarProduto() {
		ProdutoForm produtoForm = new ProdutoForm(ObjectUtilTest.descricao, ObjectUtilTest.sku, ObjectUtilTest.peso,
				ObjectUtilTest.getDimensoes(), ObjectUtilTest.getFabricante(), ObjectUtilTest.preco);
		Optional<ProdutoDto> produtoOptional = produtoService.cadastrar(produtoForm);
		return produtoOptional.get().getId();
	}

	private void verificarDadosProduto(Optional<ProdutoDto> produtoOptional) {
		assertNotNull(produtoOptional.get().getId());
		assertEquals(ObjectUtilTest.descricao, produtoOptional.get().getDescricao());
		assertEquals(ObjectUtilTest.sku, produtoOptional.get().getSku());
		assertNotNull(produtoOptional.get().getFabricante().getId());
		assertEquals(nomeFabricante, produtoOptional.get().getFabricante().getNome());
		assertEquals(ObjectUtilTest.preco, produtoOptional.get().getPreco());
	}

}
