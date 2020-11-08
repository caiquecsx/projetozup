package br.com.caique.desafiozup;

import java.math.BigDecimal;

import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;

public class ObjectUtilTest {
	
	public static final String descricao = "Teste";
	public static final Double peso = 3.0;
	public static final BigDecimal preco = new BigDecimal("10.00");
	public static final String sku = "123456";
	public static final int pagina = 1;
	public static final int quantidade = 2;

	public static Dimensoes getDimensoes() {
		Dimensoes dimensoes = new Dimensoes();
		dimensoes.setAltura(2.0);
		dimensoes.setLargura(1.0);
		dimensoes.setProfundidade(1.0);
		return dimensoes;
	}
	
	public static Fabricante getFabricante() {
		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Teste LTDA");
		return fabricante;
	}
}
