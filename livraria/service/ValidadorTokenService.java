package aula03.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidadorTokenService {
	
	public Boolean validar() {
		RestTemplate template = new RestTemplate();
		Object response = template.getForObject("http://localhost:5000/api/auth/valid-token", Object.class);
		return response!=null ? true : false;
	}

}
