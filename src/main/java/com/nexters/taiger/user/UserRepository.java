package com.nexters.taiger.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByKakaoId(String id);
}
