package br.com.livraria.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.entity.Carrinho;
import br.com.livraria.service.CarrinhoService;
import br.com.livraria.service.ValidadorTokenService;

@RestController
public class CarrinhoController {
	
	@Autowired ValidadorTokenService validadorTokenService;
	@Autowired CarrinhoService carrinhoService;
	
	@GetMapping(value = "/carrinhos", produces = "application/json")
	public @ResponseBody ResponseEntity<List<Carrinho>> buscarCarrinhos(@RequestBody(required = false) Carrinho carrinho, 
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(new ArrayList<Carrinho>(), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping(value = "/carrinhos/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Carrinho> buscarCarrinho(@PathVariable Long id, 
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			Carrinho carrinho = new Carrinho(id);
			return new ResponseEntity<>(carrinho, header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/carrinhos", produces = "application/json")
	public @ResponseBody ResponseEntity<?> cadastrarCarrinhos(
			@RequestHeader(required = false, defaultValue = "not-valid") String token,
			@RequestBody Carrinho ...carrinhos) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(Arrays.asList(carrinhos), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping(value = "/carrinhos/{id}/pagamento", produces = "application/json")
	public @ResponseBody ResponseEntity<?> registrarPagamento(
			@RequestHeader(required = false, defaultValue = "not-valid") String token,
			@RequestBody Carrinho carrinho) {
		if (validadorTokenService.validarToken(token)) {
			return carrinhoService.registrarPagamento(carrinho);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}

	@PutMapping(value = "/carrinhos/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> atualizarCarrinho(@PathVariable Long id, @RequestBody Carrinho carrinho,
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		carrinho.setId(id);
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(carrinho, header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping(value = "/carrinhos/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> deletarCarrinho(@PathVariable Long id,
			@RequestHeader(required = false, defaultValue = "not-valid") String token) {
		if (validadorTokenService.validarToken(token)) {
			return new ResponseEntity<>(new Carrinho(), header("Retornado com sucesso"), HttpStatus.OK);
		}
		return new ResponseEntity<>(header("Não autorizado"), HttpStatus.UNAUTHORIZED);
	}
	
	private MultiValueMap<String, String> header(String msg) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.set("mensagem", msg);
		return headers;
	}
}
