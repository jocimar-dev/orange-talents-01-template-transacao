package com.zup.transacao.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID buscaId;

    @NotBlank
    @Email
    private String email;

    public Cartao(@NotNull UUID buscaId,
                  @NotBlank @Email String email) {
        this.buscaId = buscaId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public UUID getBuscaId() {
        return buscaId;
    }

    public String getEmail() {
        return email;
    }

    @Deprecated
    public Cartao() {

    }
}
