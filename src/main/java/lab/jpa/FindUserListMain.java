package lab.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import lab.jpa.model.UserInfo;

public class FindUserListMain {
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
			/*
			 * transaction.begin();
			 * 
			 * TypedQuery<UserInfo> query =
			 * entityManager.createQuery("select u from Userinfo u order by u.username",
			 * UserInfo.class); List<UserInfo> result = query.getResultList();
			 * 
			 * transaction.commit(); for (UserInfo user : result) System.out.println(user);
			 */
            
        } catch (Exception ex) {
             
            transaction.rollback();
            throw ex;
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
