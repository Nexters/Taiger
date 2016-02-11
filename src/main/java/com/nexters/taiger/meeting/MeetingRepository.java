package com.nexters.taiger.meeting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexters.taiger.common.constant.UserSortType;
import com.nexters.taiger.departure.DepartureEntity;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface MeetingRepository extends CrudRepository<MeetingEntity, Integer> {



	  //기본정렬

	  @Query("SELECT m.id FROM MeetingEntity m WHERE m.departure.id =?1 order by m.createdAt ASC")
	  List<MeetingEntity> findAllByDepartureIdAndDestName(int departureId);

	  
	  //임박순
	  @Query(value="SELECT * from meeting where departure_id=?1 order by created_at asc;",nativeQuery=true)
	  List<MeetingEntity> findAllByDepartureId(int departureId);

	

	
}
