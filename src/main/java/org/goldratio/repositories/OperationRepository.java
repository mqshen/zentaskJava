package org.goldratio.repositories;

import java.util.List;

import org.goldratio.models.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/** 
 * ClassName: OperationRepository <br/> 
 * Function: TODO <br/> 
 * Reason: TODO <br/> 
 * date: Mar 27, 2013 2:53:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public interface OperationRepository extends CrudRepository<Operation, Long> {
	
	Operation findById(long id);
	
	List<Operation> findByAuthorId(long authorId);
	
	
	@Query(value = "from Operation op where " +
			" op.teamId = :teamId" +
			" and (:userId is null or op.authorId = :userId)" +
			" order by op.createTime Desc")
	Page<Operation> findByTeamIdAndUserId(@Param("teamId") long teamId, @Param("userId") Long userId, Pageable pageable);
	
}
