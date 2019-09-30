package br.com.auditoria.service;

import org.springframework.stereotype.Service;

import br.com.auditoria.entity.Auditoria;

@Service
public class AuditoriaService {
	
	public Auditoria retornarAuditoria(Long id) {
		Auditoria audit = new Auditoria(id);
		return audit;
	}
	
	public Auditoria gravarAuditoria(Auditoria auditoria) {
		return save(auditoria);
	}
	
	private Auditoria save(Auditoria auditoria) {
		return auditoria;
	}

}
