package com.nttd.service.impl;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.nttd.dto.AuditDto;
import com.nttd.dto.ResponseDto;
import com.nttd.entity.AuditEntity;
import com.nttd.repository.AuditRepository;
import com.nttd.service.AuditService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuditServiceImpl implements AuditService {

    @Inject
    AuditRepository auditRepository;

    @ConfigProperty(name = "message.001")
    String message001;

    @ConfigProperty(name = "error.generic")
    String errorgeneric;

    public Uni<ResponseDto> add(AuditDto auditDto) {
        try {
            return auditRepository.add(toAudit(auditDto))
                    .map(audit -> new ResponseDto(201, message001, audit));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    AuditEntity toAudit(AuditDto auditDto) {
        AuditEntity audit = new AuditEntity();
        audit.setApplication(auditDto.getAplicacion());
        audit.setApplicationUser(auditDto.getUsuarioAplicacion());
        audit.setSessionUser(auditDto.getUsuarioSesion());
        audit.setTransactionCode(auditDto.getCodigoTransaccion());
        audit.setTransactionDate(auditDto.getFechaTransaccion());
        audit.setMessage(auditDto.getMensaje());
        audit.setRequest(auditDto.getRequest());
        audit.setResponse(auditDto.getResponse());

        return audit;
    }

}
