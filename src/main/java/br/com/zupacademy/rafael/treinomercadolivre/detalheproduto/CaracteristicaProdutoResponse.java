package br.com.zupacademy.rafael.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.CaracteristicaProduto;

public class CaracteristicaProdutoResponse {

    public String nome;
    public String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

