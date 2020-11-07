package br.com.caique.desafiozup.service;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.form.PedidoAtualizacaoForm;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PedidoService {

    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    public PedidoService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Page<PedidoDto> listar(int pagina, int quantidade) {
        Pageable pageable = PageRequest.of(pagina, quantidade);
        return new PedidoDto().converter(pedidoRepository.findAll(pageable));
    }

    public PedidoDto cadastrar(PedidoForm pedidoForm) {
        Pedido pedido = pedidoForm.converter(produtoRepository);
        pedidoRepository.save(pedido);
        return new PedidoDto(pedido);
    }

    public ResponseEntity<?> remover(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if(pedidoOptional.isPresent()) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<PedidoDto> atualizar(Long id, PedidoAtualizacaoForm pedidoAtualizacaoForm) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            Pedido pedido = pedidoAtualizacaoForm.atualizar(id, pedidoRepository, produtoRepository);
            return ResponseEntity.ok(new PedidoDto(pedido));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> detalhar(@PathVariable Long id) {
        //TODO mover implementação para o service e criar o detalhar de produto.
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()){
            return ResponseEntity.ok(new PedidoDto(pedidoOptional.get()));
        }

        return ResponseEntity.notFound().build();
    }

}
