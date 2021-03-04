package com.zup.transacao.transacao;

import com.zup.transacao.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Object> {

    List<Transacao> findTop10ByCartaoOrderByEmitidoEm(Cartao cartao);


}
