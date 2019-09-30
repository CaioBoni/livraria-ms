package br.com.livraria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidadorTokenService {
	
	@Value("${url.token}") String urlAuthToken;
	
	public Boolean validarToken(String token) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response;
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("token", token);

		HttpEntity<?> entity = new HttpEntity<Object>(headers);

		try {
			response = restTemplate.exchange(urlAuthToken, HttpMethod.GET, entity, Boolean.class);
		}catch (Exception e) {
			return false;
		}
		return response!=null ? true : false;
	}

}
