package com.study.common.springbootv1.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String uid;
    private String name;
    private String password;
    private String number;
}
