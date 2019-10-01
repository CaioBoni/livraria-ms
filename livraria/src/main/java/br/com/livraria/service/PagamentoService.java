package br.com.livraria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PagamentoService {

	@Value("${url.cartao}") String urlCreditCard;

	public Boolean registrarPagamento(Object obj) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response;
		HttpEntity<?> entity = new HttpEntity<Object>(obj);
		try {
			response = restTemplate.exchange(urlCreditCard+"/entrada", HttpMethod.POST, entity, Object.class);
		} catch (Exception e) {
			return false;
		}
		return response != null ? true : false;
	}
}
