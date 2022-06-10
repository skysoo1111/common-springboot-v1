package com.study.common.springbootv1.mapper;

import com.study.common.springbootv1.domain.Member;
import com.study.common.springbootv1.sample.service.CommonService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper extends CommonService<Member, Long> {
    void updateMember(Member member);
}
