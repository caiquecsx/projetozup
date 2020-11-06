package br.com.caique.desafiozup.repository;

import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository
        extends JpaRepository<Produto, Long> { }
