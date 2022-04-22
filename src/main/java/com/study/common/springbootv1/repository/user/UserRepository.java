package com.study.common.springbootv1.repository.user;


import com.study.common.springbootv1.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, UserRepositoryCustom {
}
