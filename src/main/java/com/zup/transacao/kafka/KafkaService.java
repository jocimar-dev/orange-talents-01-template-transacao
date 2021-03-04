package com.zup.transacao.kafka;

import com.zup.transacao.anotation.KafkaListener;
import com.zup.transacao.cartao.Cartao;
import com.zup.transacao.cartao.CartaoRepository;
import com.zup.transacao.kafka.consumer.TransacaoConsumer;
import com.zup.transacao.transacao.Transacao;
import com.zup.transacao.transacao.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    @Autowired
    public KafkaService(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }


    @KafkaListener
//            (topics = "${transactions.kafka.topic.name}", groupId = "${transactions.kafka.consumer.group.id}")
    @Transactional
    public void criaTransacao(TransacaoConsumer transacaoConsumer) {
        Cartao cartaoModel = transacaoConsumer.getCartaoModel();
        Cartao cartao  = cartaoRepository.findByBuscaId(cartaoModel.getBuscaId()).orElse(cartaoModel);

        cartaoRepository.save(cartao);

        Transacao transacao = transacaoConsumer.toModel(cartao);
        transacaoRepository.save(transacao);

        LOGGER.info("Transação {} efetuado com sucesso", transacao.getEmitidoEm());
    }

}
