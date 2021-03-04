package com.zup.transacao.cartao;

import com.zup.transacao.transacao.Transacao;
import com.zup.transacao.transacao.TransacaoRepository;
import com.zup.transacao.transacao.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CartaoConsultaController {

    private final CartaoRepository cartaoRepository;
    private final TransacaoRepository transacaoRepository;

    @Autowired
    public CartaoConsultaController(CartaoRepository cartaoRepository, TransacaoRepository transacaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/cartao/{id}")
    public ResponseEntity<List<TransacaoResponse>> consult(@PathVariable UUID id) {
        Cartao cartao = cartaoRepository.findByBuscaId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontra-se cadastrado."));

        List<Transacao> transactions = transacaoRepository.findTop10ByCartaoOrderByEmitidoEm(cartao);

        List<TransacaoResponse> response = transactions.stream()
                .map(TransacaoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
