package com.hype360kh.servicecatalog.repository;

import com.hype360kh.servicecatalog.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,
    JpaSpecificationExecutor<UserEntity> {

}