package com.oliver.toy.budgetmanagementapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.oliver.toy.budgetmanagementapi.constants.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Entity(name="user_info")
public class User extends BaseEntity {

    /** 사용자 번호 */
    @Id
    @Column(name="user_no",insertable=false, updatable=false)
    private long userNo;

    /** 사용자 ID */
    @Column(name="user_id")
    @JsonIgnore
    private String userId;

    /** 비밀번호 */
    @Column(name="password")
    @JsonIgnore
    private String password;

    /** 상태 */
    @Column(name="status")
    private String status;

    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
        this.status = UserStatus.NORMAL.getStatus();
        this.setInstDt(new Date());
    }
}
