package com.akhp.kakfa.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.akhp.kakfa.entity.User;

@EnableScan
//@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
