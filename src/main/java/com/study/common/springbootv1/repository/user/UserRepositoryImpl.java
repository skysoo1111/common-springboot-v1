package com.study.common.springbootv1.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.common.springbootv1.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import static com.study.common.springbootv1.domain.entity.QUserEntity.userEntity;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public UserEntity getMemberByUid(String uid) {
        return jpaQueryFactory.selectFrom(userEntity)
                .where(userEntity.uid.eq(uid))
                .fetchOne();
    }
}
