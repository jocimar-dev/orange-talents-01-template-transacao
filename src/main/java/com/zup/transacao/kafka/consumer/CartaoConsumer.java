package com.zup.transacao.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.transacao.cartao.Cartao;

import java.util.UUID;

public class CartaoConsumer {

    @JsonProperty(required = true)
    private UUID id;

    @JsonProperty(required = true)
    private String email;

    public CartaoConsumer() {
    }

    public Cartao paraCartao() {
        return new Cartao(id, email);
    }

}
