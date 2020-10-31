package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping
    public Produto lista() {
        Produto produto = new Produto("Arroz 101",
                12.2,
                new Dimensoes(1.0, 1.0, 1.0),
                new Fabricante("Fabrica Fortaleza"));

        return produto;
    }
}
