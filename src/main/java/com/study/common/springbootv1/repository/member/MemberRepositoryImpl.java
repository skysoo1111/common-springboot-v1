package com.study.common.springbootv1.repository.member;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.common.springbootv1.domain.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import static com.study.common.springbootv1.domain.entity.QMemberEntity.memberEntity;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public MemberEntity getMemberByUid(String uid) {
        return jpaQueryFactory.selectFrom(memberEntity)
                .where(memberEntity.uid.eq(uid))
                .fetchOne();
    }

    @Transactional
    @Override
    public void deleteMemberByUid(String uid) {
        jpaQueryFactory.delete(memberEntity)
                .where(memberEntity.uid.eq(uid))
                .execute();
    }
}
