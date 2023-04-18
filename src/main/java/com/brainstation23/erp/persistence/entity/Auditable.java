package com.brainstation23.erp.persistence.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Accessors(chain = true)
public abstract class Auditable<T>{

    @CreatedBy
    protected T createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdDate;

    @LastModifiedBy
    protected T lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastModifiedDate;
}
