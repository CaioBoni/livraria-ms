package auditoria.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import auditoria.entity.Auditoria;
import auditoria.service.AuditoriaService;

@RestController
public class AuditoriaController {
	
	@Autowired AuditoriaService auditoriaService;

    @RequestMapping(path="/api/shopping/{userId}", method={RequestMethod.GET})
    public Auditoria greeting(@PathVariable Long userId) {
        return auditoriaService.retornarAuditoria(userId);
    }
    
    @RequestMapping(path="/api/shopping", method={RequestMethod.POST})
    public Auditoria gravarAuditoria(@RequestBody Object object) {
    	Auditoria auditoria = new Auditoria();
    	auditoria.setObjeto(object);
    	auditoria.setData((new Date()).toString());
        return auditoriaService.gravarAuditoria(auditoria);
    }
    
    @RequestMapping(path="/api/shopping", method={RequestMethod.PUT})
    public Auditoria gravarAuditoria(@PathVariable Long userId) {
        return auditoriaService.retornarAuditoria(userId);
    }
    
}
