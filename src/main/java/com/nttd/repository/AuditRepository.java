package com.nttd.repository;

import com.nttd.entity.AuditEntity;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditRepository {

    public Uni<AuditEntity> add(AuditEntity audit) {
        return audit.persist();
    }

}
