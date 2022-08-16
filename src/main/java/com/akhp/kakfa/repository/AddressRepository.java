package com.akhp.kakfa.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.akhp.kakfa.entity.Address;

@EnableScan
@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
