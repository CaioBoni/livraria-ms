package br.com.auditoria.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.auditoria.entity.Auditoria;
import br.com.auditoria.service.AuditoriaService;

@RestController
public class AuditoriaController {
	
	@Autowired AuditoriaService auditoriaService;

	@GetMapping(path = "/api/{id}")
    public ResponseEntity<?> buscarAuditoria(@PathVariable String id) {
		return new ResponseEntity<>(auditoriaService.retornarAuditoria(Long.valueOf(id)), HttpStatus.OK);
    }
    
    @PostMapping(path="/api")
    public ResponseEntity<?> gravarAuditoria(@RequestBody Object object) {
    	Auditoria auditoria = new Auditoria();
    	auditoria.setObjeto(object);
    	auditoria.setData((new Date()).toString());
    	return new ResponseEntity<>(auditoriaService.gravarAuditoria(auditoria), HttpStatus.OK);
    }
    
    @PutMapping(path="/api/{id}")
    public ResponseEntity<?> atualizarAuditoria(@PathVariable String id) {
    	return new ResponseEntity<>(auditoriaService.retornarAuditoria(Long.valueOf(id)), HttpStatus.OK);
    }
    
}
