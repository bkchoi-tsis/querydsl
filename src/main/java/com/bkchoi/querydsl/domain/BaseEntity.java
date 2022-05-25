package com.bkchoi.querydsl.domain;

import lombok.Getter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "created_dt", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name ="updated_dt")
    @LastModifiedDate
    private LocalDateTime updatedDate;

}
