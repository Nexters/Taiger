package com.nexters.taiger.hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by BoBinLee on 2016. 1. 14..
 */
//CrudRepository<도메인 클래스, id 속성>
@Repository
public interface HelloRepository extends CrudRepository<Hello, Integer> {

}
