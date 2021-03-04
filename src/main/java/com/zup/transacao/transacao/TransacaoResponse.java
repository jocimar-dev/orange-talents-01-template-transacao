package com.zup.transacao.transacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.transacao.cartao.Cartao;

import java.math.BigDecimal;
import java.util.UUID;

public class TransacaoResponse {

    @JsonProperty
    private UUID buscaId;

    @JsonProperty
    private BigDecimal valor;

    @JsonProperty
    private Estabelecimento estabelecimento;

    @JsonProperty
    private Cartao cartao;

    public TransacaoResponse(Transacao transacao) {
        this.buscaId = transacao.getBuscaId();
        this.valor = transacao.getValor();
        this.estabelecimento = transacao.getEstabelecimento();
        this.cartao = transacao.getCartao();
    }
}
