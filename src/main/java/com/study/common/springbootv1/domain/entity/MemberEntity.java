package com.study.common.springbootv1.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Long id;
    @Column(length = 50)
    private String uid;
    @Column(length = 50)
    private String name;
    @Column(length = 11)
    private String number;
    @Column(length = 100)
    private String password;
}
