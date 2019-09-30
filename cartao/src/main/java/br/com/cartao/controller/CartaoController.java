package br.com.cartao.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartao.entity.Cartao;

@RestController
public class CartaoController {
	
	@PostMapping(value="/entrada", produces = "application/json")
    public ResponseEntity<?> gravarEntrada(@RequestBody Object object) {
    	Cartao cartao = new Cartao();
    	cartao.setOperacao(Cartao.OperacaoCartao.ENTRADA);
    	cartao.setData((new Date()));
    	return new ResponseEntity<>(cartao, HttpStatus.OK);
    }
	
	@PostMapping(value="/saida", produces = "application/json")
    public ResponseEntity<?> saidaEntrada(@RequestBody Object object) {
    	Cartao cartao = new Cartao();
    	cartao.setOperacao(Cartao.OperacaoCartao.SAIDA);
    	cartao.setData((new Date()));
    	return new ResponseEntity<>(cartao, HttpStatus.OK);
    }
	
	@GetMapping(value="/buscar", produces = "application/json")
    public ResponseEntity<?> buscarCartao(@RequestBody Cartao cartao) {
    	Cartao cartaoRetorno = new Cartao();
    	cartaoRetorno.setId(cartao.getId());
    	cartaoRetorno.setOperacao(cartao.getOperacao());
    	cartaoRetorno.setData(cartao.getData());
    	return new ResponseEntity<>(cartaoRetorno, HttpStatus.OK);
    }

}
