package org.goldratio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


/** 
 * ClassName: ZenTaskRepository <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 4:03:07 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@NoRepositoryBean
@Transactional
public interface ZenTaskRepository <T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}

