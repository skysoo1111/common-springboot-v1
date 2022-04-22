package com.study.common.springbootv1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
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
