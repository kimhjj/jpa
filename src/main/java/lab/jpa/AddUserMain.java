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
			
			/* 
			// 저장
			UserInfo user = new UserInfo("admin", "관리자", "a1234","서울", "admin@korea.com", new Date());
			entityManager.persist(user);
			transaction.commit();
			*/

			// 1# sqlplus /nolog
			// 2# connect hr
			// 3# pw: oracle
			// 4# desc userinfo
			// 5# select * from userinfo;
			
			/* 
			// 조회1
			// 영속엔티티, 1차 캐시 저장
			UserInfo user = entityManager.find(UserInfo.class, "admin");
			transaction.commit();
			System.out.println(user);
			// UserInfo [userid=admin, username=관리자, userpwd=a1234, address=서울, email=admin@korea.com]
			*/
			
			/* 
			// 수정
			// 영속 엔티티, 1차 캐시 저장
			UserInfo user = entityManager.find(UserInfo.class, "admin");
			// 영속 엔티티 필드값 변경
			user.setUsername("마스터");	// 동일: user.changeName("마스터");
			transaction.commit();
			System.out.println(user);
			// update userinfo set address=?, create_date=?, email=?, username=?, userpwd=? where userid=?
			*/
			
			/* 
			// 조회2
			UserInfo user = entityManager.find(UserInfo.class, "admin");
			transaction.commit();
			System.out.println(user);
			// UserInfo [userid=admin, username=마스터, userpwd=a1234, address=서울, email=admin@korea.com]
			*/
			
			// 삭제
			UserInfo user = entityManager.find(UserInfo.class, "admin");
			entityManager.remove(user);
			transaction.commit();
			// delete from userinfo where userid=?
			
			// 1# sqlplus /nolog
			// 2# connect hr
			// 3# pw: oracle
			// 4# select * from userinfo;
			
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }
		
		emf.close();
	}
}
