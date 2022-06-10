package com.study.common.springbootv1.repository.member;

import com.study.common.springbootv1.domain.entity.MemberEntity;

public interface MemberRepositoryCustom {
    MemberEntity getMemberByUid(String uid);
    void deleteMemberByUid(String uid);
}
