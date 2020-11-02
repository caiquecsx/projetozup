package br.com.caique.desafiozup.repository;

import br.com.caique.desafiozup.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository
        extends JpaRepository<Pedido, Long> { }