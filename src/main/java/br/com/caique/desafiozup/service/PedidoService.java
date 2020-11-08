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
import java.util.OptionalInt;

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

    public Boolean remover(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if(pedidoOptional.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PedidoDto> atualizar(Long id, PedidoAtualizacaoForm pedidoAtualizacaoForm) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            Pedido pedido = pedidoAtualizacaoForm.atualizar(id, pedidoRepository, produtoRepository);
            return Optional.of(new PedidoDto(pedido));
        }
        return Optional.empty();
    }

    @GetMapping("/{id}")
    public Optional<PedidoDto> detalhar(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()){
            return Optional.of(new PedidoDto(pedidoOptional.get()));
        }

        return Optional.empty();
    }

}
