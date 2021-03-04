package com.zup.transacao.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.transacao.cartao.Cartao;
import com.zup.transacao.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoConsumer {

    @JsonProperty(required = true)
    private UUID id;

    @JsonProperty(value = "valor", required = true)
    private BigDecimal valor;

    @JsonProperty(value = "estabelecimento", required = true)
    private EstabelecimentoConsumer estabelecimento;

    @JsonProperty(value = "cartao", required = true)
    private CartaoConsumer cartao;

    @JsonProperty(value = "emitidaEm", required = true)
    private LocalDateTime emitidaEm;

    public Transacao toModel(Cartao cartao) {
        return new Transacao(id, valor, estabelecimento.paraEstabelecimento(), cartao, emitidaEm);
    }

    public Cartao getCartaoModel() {
        return cartao.paraCartao();
    }

    public UUID getId() {
        return id;
    }

}
