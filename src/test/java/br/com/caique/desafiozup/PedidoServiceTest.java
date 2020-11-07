package br.com.caique.desafiozup;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.form.PedidoAtualizacaoForm;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import br.com.caique.desafiozup.service.PedidoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoServiceTest {

//    @Autowired
//    ProdutoRepository produtoRepository;
//    @Autowired
//    PedidoRepository pedidoRepository;
//
//    PedidoService pedidoService;
//
//    @BeforeEach
//    void initService() {
//        pedidoService = new PedidoService(produtoRepository, pedidoRepository);
//    }
//
//    @DisplayName("Teste cadastro pedido")
//    @Test
//    @Order(1)
//    void verificaCadastroPedidoIdGerado() {
//        //Dado
//        PedidoForm pedidoForm = getPedidoForm();
//        //Quando
//        PedidoDto pedidoDto = pedidoService.cadastrar(pedidoForm);
//        //Então
//        Assertions.assertThat(pedidoDto.getId()).isNotNull();
//    }
//
//    @DisplayName("Teste remover pedido")
//    @Test
//    @Order(2)
//    void verificaRemover() {
//        //dado
//        Pedido pedido = pedidoRepository.getOne(Long.valueOf(19));
//        //quando
//        ResponseEntity responseEntity = pedidoService.remover(pedido.getId());
//        //então
//        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
//    }
//
//    @DisplayName("Teste atualização de pedido")
//    @Test
//    @Order(3)
//    void verificaAtualizar() {
//        //dado
//        PedidoAtualizacaoForm pedidoAtualizacaoForm = new PedidoAtualizacaoForm();
//        pedidoAtualizacaoForm.setDesconto(2.0);
//        List<Long> produtos = new ArrayList<>();
//        produtos.add((long) 12);
//        pedidoAtualizacaoForm.setProdutos(produtos);
//        //quando
//        pedidoService.atualizar(Long.valueOf(19), pedidoAtualizacaoForm);
//        //então
//        Pedido pedido = pedidoRepository.getOne(Long.valueOf(19));
//        Assertions.assertThat(pedido.getDesconto())
//                .isEqualTo(2.0);
//        Assertions.assertThat(pedido.getProdutos().get(0).getId())
//                .isEqualTo(Long.valueOf(12));
//    }
//
//    private PedidoForm getPedidoForm() {
//        PedidoForm pedidoForm = new PedidoForm();
//        pedidoForm.setCliente("Caique");
//        pedidoForm.setDesconto(1.2);
//        List<Long> produtos = new ArrayList<>();
//        produtos.add((long) 15);
//        pedidoForm.setProdutos(produtos);
//        pedidoForm.setTelefone("85999680601");
//        return pedidoForm;
//    }
}
