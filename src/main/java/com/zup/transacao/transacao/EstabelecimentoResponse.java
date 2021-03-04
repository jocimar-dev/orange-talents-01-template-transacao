package com.zup.transacao.transacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstabelecimentoResponse {

    @JsonProperty
    private String nome;

    @JsonProperty
    private String cidade;

    @JsonProperty
    private String endereco;

    public EstabelecimentoResponse(String nome,
                                   String cidade,
                                   String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
