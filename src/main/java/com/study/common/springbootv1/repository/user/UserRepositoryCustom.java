package com.study.common.springbootv1.repository.user;


import com.study.common.springbootv1.domain.entity.UserEntity;

public interface UserRepositoryCustom {
    UserEntity getMemberByUid(String uid);
}
