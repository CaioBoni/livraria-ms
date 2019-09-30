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

	@GetMapping(value="/api/shopping/{userId}")
    public ResponseEntity<?> greeting(@PathVariable Long userId) {
		return new ResponseEntity<>(auditoriaService.retornarAuditoria(userId), HttpStatus.OK);
    }
    
    @PostMapping(value="/api/shopping")
    public ResponseEntity<?> gravarAuditoria(@RequestBody Object object) {
    	Auditoria auditoria = new Auditoria();
    	auditoria.setObjeto(object);
    	auditoria.setData((new Date()).toString());
    	return new ResponseEntity<>(auditoriaService.gravarAuditoria(auditoria), HttpStatus.OK);
    }
    
    @PutMapping(value="/api/shopping")
    public ResponseEntity<?> gravarAuditoria(@PathVariable Long userId) {
    	return new ResponseEntity<>(auditoriaService.retornarAuditoria(userId), HttpStatus.OK);
    }
    
}
