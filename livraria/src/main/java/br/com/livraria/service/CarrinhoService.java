package br.com.livraria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.livraria.entity.Carrinho;

@Service
public class CarrinhoService {
	
	@Autowired PagamentoService pagamentoService;
	@Autowired AuditoriaService auditoriaService; 

	public ResponseEntity<?> registrarPagamento(Carrinho carrinho) {
		if (!pagamentoService.registrarPagamento(carrinho)) {
			return new ResponseEntity<>(header("Não foi possível registrar o pagamento"), HttpStatus.BAD_REQUEST);
		}
		if (!auditoriaService.registrarAuditoria(carrinho)) {
			return new ResponseEntity<>(header("Não foi possível registrar auditoria"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(carrinho, HttpStatus.OK);
	}
	
	private MultiValueMap<String, String> header(String msg) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.set("mensagem", msg);
		return headers;
	}
	

}
