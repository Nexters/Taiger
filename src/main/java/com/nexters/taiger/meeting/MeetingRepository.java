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

	//List<MeetingEntity> findAllOrderByCreatedAtDesc(MeetingEntity createdAt,UserSortType sortType);
	
	//List<MeetingEntity> findAllOrderByCreatedAtAndDesc(UserSortType sortType);


	  //기본정렬
	  @Query(value="SELECT id, departure_id, dest_name, created_at,(CASE WHEN departure_id = ?1 AND dest_name = ?2 THEN 1 ELSE 0 END) search  FROM meeting WHERE departure_id =?1 ORDER BY search DESC, created_at ASC", nativeQuery =true)
	  ArrayList<MeetingEntity> findAllByDeparture_idEndDest_name(int departure_id,String dest_name);

	  
	  //임박순
	  @Query(value="SELECT id,dest_name,created_at FROM meeting WHERE departure_id =?1", nativeQuery =true)
	  List<MeetingEntity> findAllByDeparture_id(int departure_id);
	

	
}
