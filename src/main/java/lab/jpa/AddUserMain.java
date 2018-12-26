package lab.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lab.jpa.model.UserInfo;

public class AddUserMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
		
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			UserInfo user = new UserInfo("admin", "관리자", "a1234","서울",  "admin@korea.com", new Date());
			entityManager.persist(user);
			transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }
		
		emf.close();
		
		// 1# sqlplus /nolog
		// 2# connect hr
		// 3# pw: oracle
		// 4# desc userinfo
	}
}
