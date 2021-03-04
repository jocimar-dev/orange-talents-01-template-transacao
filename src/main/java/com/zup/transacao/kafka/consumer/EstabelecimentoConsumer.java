package com.zup.transacao.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.transacao.transacao.Estabelecimento;

public class EstabelecimentoConsumer {

    @JsonProperty(value = "nome", required = true)
    private String nome;

    @JsonProperty(value = "cidade", required = true)
    private String cidade;

    @JsonProperty(value = "endereco", required = true)
    private String endereco;

    public EstabelecimentoConsumer() {
    }

    public Estabelecimento paraEstabelecimento() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
