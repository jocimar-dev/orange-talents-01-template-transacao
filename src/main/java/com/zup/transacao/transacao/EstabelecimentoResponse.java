package com.zup.transacao.transacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstabelecimentoResponse {

    @JsonProperty
    private String name;

    @JsonProperty
    private String cidade;

    @JsonProperty
    private String endereco;

    public EstabelecimentoResponse(String name,
                                   String cidade,
                                   String endereco) {
        this.name = name;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getName() {
        return name;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
