package br.com.caique.desafiozup;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.exception.FabricanteInvalidoException;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import br.com.caique.desafiozup.service.ProdutoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoServiceTest {

//    @Autowired
//    ProdutoRepository produtoRepository;
//
//    @Autowired
//    FabricanteRepository fabricanteRepository;
//
//    ProdutoService produtoService;
//
//    @BeforeEach
//    void initService() {
//        produtoService = new ProdutoService(produtoRepository, fabricanteRepository);
//    }
//
//    @DisplayName("Teste cadastro pedido")
//    @Test
//    @Order(1)
//    void cadatrar() throws FabricanteInvalidoException {
//        //dado
//        ProdutoForm produtoForm = this.getProdutoForm();
//        //quando
//        ProdutoDto produtoDto = produtoService.cadastrar(produtoForm);
//        //entao
//        Assertions.assertThat(produtoDto.getId()).isNotNull();
//    }
//
//    private ProdutoForm getProdutoForm() {
//        return new ProdutoForm(
//                "Teste Produto",
//                2.0,
//                new Dimensoes(2.0, 1.0, 1.0),
//                new Fabricante("Fabrica Teste"),
//                new BigDecimal(10)
//        );
//    }
}
