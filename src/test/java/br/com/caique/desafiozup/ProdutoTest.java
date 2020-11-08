package br.com.caique.desafiozup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import br.com.caique.desafiozup.service.ProdutoService;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdutoTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FabricanteRepository fabricanteRepository;

    ProdutoService produtoService;

    public static final String descricao = "Teste";
    public static final Double peso = 3.0;
    public static final BigDecimal preco = new BigDecimal("1.25");
    public static final String sku = "123456";
    public static final int pagina = 1;
    public static final int quantidade = 2;
    public static final String MSG_ID_FABRICANTE_NAO_ENCONTRADO = "Identificador do fabricante informado não foi encontrado!";
    public static Long idProduto;

    @BeforeEach
    void initUseCase() {
        produtoService = new ProdutoService(produtoRepository, fabricanteRepository);
    }

    @Test
    public void testListar() {
        Page<ProdutoDto> produtoDtoRetornadalist = produtoService.listar(pagina, quantidade);
        assertEquals(quantidade, produtoDtoRetornadalist.getSize());
    }

    @DisplayName("Verificar cadastro de produto")
    @Test
    @Order(1)
    public void testCadastrar() {
        // Dado
        ProdutoForm produtoForm = new ProdutoForm(descricao, sku, peso, setDimensoes(), setFabricante(), preco);

        // Quando
        Optional<ProdutoDto> produtoOptional = produtoService.cadastrar(produtoForm);
        idProduto = produtoOptional.get().getId();

        // Então
        if (produtoOptional.isPresent()) {
            verficicarDadosProduto(produtoOptional);
        } else {
            //assertEquals(MSG_ID_FABRICANTE_NAO_ENCONTRADO, );
        }

    }

    @DisplayName("Verificar detalhamento de produto")
    @Test
    @Order(2)
    public void testDetalhar() {
        //dado
        Long id = cadastrarProduto();

        //quando
        Optional<ProdutoDto> produtoOptional = produtoService.detalhar(id);

        //então
        verficicarDadosProduto(produtoOptional);
    }

    @DisplayName("Verificar alteração de produto")
    @Test
    @Order(3)
    public void testAtualizar() {
        //dado
        Long id = cadastrarProduto();

        //quando
        Optional<ProdutoDto> produtoOptionalAntesAlteracao = produtoService.detalhar(id);
        verficicarDadosProduto(produtoOptionalAntesAlteracao);

        ProdutoAtualizacaoForm produtoAtualizacaoForm = new ProdutoAtualizacaoForm();
        produtoAtualizacaoForm.setDescricao("Teste2");
        produtoAtualizacaoForm.setPeso(5.0);
        produtoAtualizacaoForm.setPreco(new BigDecimal("4.00"));

        Optional<ProdutoDto> produtoOptionalDepoisAlteracao = produtoService.atualizar(id, produtoAtualizacaoForm);

        assertNotEquals(descricao, produtoOptionalDepoisAlteracao.get().getDescricao());
        assertNotEquals(preco, produtoOptionalDepoisAlteracao.get().getPreco());

        assertEquals(id, produtoOptionalDepoisAlteracao.get().getId());
        assertEquals(sku, produtoOptionalDepoisAlteracao.get().getSku());
    }

    @DisplayName("Testar remoção produto")
    @Test
    @Order(4)
    public void testRemover() {
        //dado
        Long id = cadastrarProduto();
        //quando
        Optional<ProdutoDto> produtoOptional = produtoService.detalhar(id);

        verficicarDadosProduto(produtoOptional);

        // Quando
        Boolean retorno = produtoService.remover(produtoOptional.get().getId());

        // Então
        assertTrue(retorno);

        produtoOptional = produtoService.detalhar(idProduto);

        assertTrue(produtoOptional.isEmpty());
    }

    public Long cadastrarProduto() {
        ProdutoForm produtoForm = new ProdutoForm(descricao, sku, peso, setDimensoes(), setFabricante(), preco);
        Optional<ProdutoDto> produtoOptional = produtoService.cadastrar(produtoForm);
        return produtoOptional.get().getId();
    }

    public Dimensoes setDimensoes() {
        Dimensoes dimensoes = new Dimensoes();
        dimensoes.setAltura(2.0);
        dimensoes.setLargura(1.0);
        dimensoes.setProfundidade(1.0);
        return dimensoes;
    }

    public Fabricante setFabricante() {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome("Teste LTDA");
        return fabricante;
    }

    public void verficicarDadosProduto(Optional<ProdutoDto> produtoOptional) {
        assertNotNull(produtoOptional.get().getId());
        assertEquals(descricao, produtoOptional.get().getDescricao());
        assertEquals(sku, produtoOptional.get().getSku());
        assertNotNull(produtoOptional.get().getFabricante().getId());
        assertEquals(setFabricante().getNome(), produtoOptional.get().getFabricante().getNome());
        assertEquals(preco, produtoOptional.get().getPreco());
    }

}
