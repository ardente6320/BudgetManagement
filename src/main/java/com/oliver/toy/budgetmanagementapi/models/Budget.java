package com.oliver.toy.budgetmanagementapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 예산 정보
 */
@Getter
@Setter
@ToString
@Entity(name="budget_info")
public class Budget extends BaseEntity{
 
    /** 예산 ID */
    @Id
    @Column(name="budget_id",insertable=false, updatable=false)
    private long budgetId;

    /** 사용자 번호 */
    @Column(name="user_no")
    private long userNo;

    /** 예산 명 */
    @Column(name="title")
    private String title;

    /** 예산 금액 */
    @Column(name="budget_amount")
    private long budgetAmount;

    /** 현재 잔액 */
    @Column(name="cur_amount")
    private long curAmount;

    /** 시작 일시 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="started_at")
    private Date startedAt;
    
    /** 종료 일시 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="finished_at")
    private Date finishedAt;
}
