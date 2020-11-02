package br.com.caique.desafiozup.repository;

import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository
        extends JpaRepository<Produto, Long> { }
