package br.com.livraria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidadorTokenService {
	
	@Value("${url.auth}") String urlAuth;
	
	public Boolean validarToken() {
		RestTemplate template = new RestTemplate();
		Object response;
		try {
			response = template.getForObject(urlAuth, Object.class);
		}catch (Exception e) {
			return true;
		}
		return response!=null ? true : false;
	}

}
