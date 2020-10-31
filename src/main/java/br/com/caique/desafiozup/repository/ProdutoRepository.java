package br.com.caique.desafiozup.repository;

import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> { }
