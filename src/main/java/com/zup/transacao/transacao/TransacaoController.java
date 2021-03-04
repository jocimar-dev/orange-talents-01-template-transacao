package com.zup.transacao.transacao;

import com.zup.transacao.cartao.Cartao;
import com.zup.transacao.cartao.CartaoRepository;
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
public class TransacaoController {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    @Autowired
    public TransacaoController(TransacaoRepository transacaoRepository,
                               CartaoRepository cartaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @GetMapping("/cartao/{id}")
    public ResponseEntity<List<TransacaoResponse>> consultaTransacao(@PathVariable UUID id) {
        Cartao cartao = cartaoRepository.findByBuscaId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoOrderByEmitidoEm(cartao);

        List<TransacaoResponse> response = transacoes
                .stream()
                .map(TransacaoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
