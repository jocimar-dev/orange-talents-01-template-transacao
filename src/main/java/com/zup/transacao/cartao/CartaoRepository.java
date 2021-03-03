package com.zup.transacao.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Object> {

    Optional<Cartao> findByBuscaId(UUID buscaId);
}
