package br.com.caique.desafiozup.repository;

import br.com.caique.desafiozup.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository <Fabricante, Long> { }
