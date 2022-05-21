package com.oliver.toy.budgetmanagementapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 엔티티 공통 모듈
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@MappedSuperclass
public abstract class BaseEntity {

    /** 등록 일시 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="inst_dt")
    @JsonIgnore
    private Date instDt;

    /** 수정 일시 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updt_dt")
    @JsonIgnore
    private Date updtDt;
}
