package com.study.common.springbootv1.mapper;

import com.study.common.springbootv1.domain.User;
import com.study.common.springbootv1.service.CommonService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends CommonService<User, Long> {
    void updateMember(User user);
}
