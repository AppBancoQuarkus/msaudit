package com.nttd.service;

import java.util.List;

import com.nttd.dto.AuditDto;
import com.nttd.dto.ResponseDto;
import com.nttd.entity.AuditEntity;

import io.smallrye.mutiny.Uni;

public interface AuditService {

    Uni<ResponseDto> add(AuditDto audit);

    Uni<ResponseDto> listAll();

}
