package com.zup.transacao.transacao;

import com.zup.transacao.cartao.Cartao;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID buscaId;

    @NotNull
    private BigDecimal valor;

    @Embedded
    private Estabelecimento estabelecimento;

    @ManyToOne
    private Cartao cartao;

    @NotNull
    private LocalDateTime emitidoEm;

    public Transacao(@NotNull UUID buscaId,
                     @NotNull BigDecimal valor,
                     Estabelecimento estabelecimento,
                     Cartao cartao,
                     @NotNull LocalDateTime emitidoEm) {
        this.buscaId = buscaId;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.emitidoEm = emitidoEm;
    }

    public Long getId() {
        return id;
    }

    public UUID getBuscaId() {
        return buscaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

}