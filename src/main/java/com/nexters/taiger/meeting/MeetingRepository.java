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
	  @Query("SELECT new MeetingEntity(m.id, m.departure.id, m.destName, m.createdAt,(CASE WHEN m.departure.id = ?1 AND m.destName = ?2 THEN 1 ELSE 0 END) as search)  FROM MeetingEntity m WHERE m.departure.id =?1 ORDER BY search DESC, m.createdAt ASC")
	  List<MeetingEntity> findAllByDepartureIdAndDestName(int departureId,String destName);

	  
	  //임박순
	  @Query(value="SELECT * from meeting where departure_id=?1 order by created_at asc;",nativeQuery=true)
	  List<MeetingEntity> findAllByDepartureId(int departure_id);
	

	
}
