package com.nexters.taiger.departure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface DepartureRepository extends CrudRepository<DepartureEntity, Integer> {

}
