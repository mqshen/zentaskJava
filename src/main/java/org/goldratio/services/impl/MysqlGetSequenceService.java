package org.goldratio.services.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.goldratio.services.GetSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: MysqlGetSequenceService <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 8, 2013 6:03:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Service("getSequenceService")
public class MysqlGetSequenceService implements GetSequenceService{
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public Long getNextVal(String name) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = entityManagerFactory.createEntityManager();
			tx = em.getTransaction();
	        tx.begin();
			Query query = em.createNativeQuery("UPDATE counter SET value = LAST_INSERT_ID(value + :quantityOfNumbers) WHERE name = :name");
			query.setParameter("name", name);
			query.setParameter("quantityOfNumbers", 0);
			query.executeUpdate();
			 
			query = em.createNativeQuery("SELECT LAST_INSERT_ID()");
			long value = ((BigInteger) query.getSingleResult()).longValue();
			tx.commit();
			return value;
		}
		catch(Exception e) {
			if(tx != null)
				tx.rollback();
		}
		finally {
			if(em != null) {
				em.close();
			}
		}
		return null;
	}

}
