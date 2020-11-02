package br.com.caique.desafiozup.helper;

import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoHelper {

    public static List<Produto> AtualizarListaDeProdutos(ProdutoRepository produtoRepository, List<Long> produtos) {
        List<Produto> produtoList = new ArrayList<>();
        produtos.forEach(produtoId -> {
            Optional<Produto> produto = produtoRepository.findById(produtoId);
            if(produto.isPresent()){
                produtoList.add(produto.get());
            }else
                throw new IncorrectResultSizeDataAccessException(1);
        });

        return produtoList;
    }
}
