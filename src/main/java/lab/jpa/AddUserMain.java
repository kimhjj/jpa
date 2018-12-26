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
			/** 영속성 컨텍스트와 관련한 엔티티의 4가지 상태
			 * # 비영속 상태(new/transient), java 객체
			 * # 영속 상태(managed), 1차 캐시 저장
			 * # 준영속 상태(detached), 1차 캐시에서 삭제, db에는 존재
			 * # 삭제(removed), 영속성 컨텍스트와 db에서 삭제
			 */
			
			/** sqlplus
			 * # sqlplus /nolog
			 * # connect hr
			 * # pw: oracle
			 * # desc userinfo
			 * # select * from userinfo;
			 */
			
			/* 
			// 저장
			UserInfo user = new UserInfo("admin", "관리자", "a1234","서울", "admin@korea.com", new Date());	// 비영속 상태, java 객체
			entityManager.persist(user);	// 영속 상태(managed), 1차 캐시 저장
			transaction.commit();
			// insert into userinfo (address, create_date, email, username, userpwd, userid) values (?, ?, ?, ?, ?, ?)
			*/
			
			/* 
			// 조회1
			UserInfo user = entityManager.find(UserInfo.class, "admin");	// 영속 상태(managed), 1차 캐시 저장
			transaction.commit();
			System.out.println(user);
			// UserInfo [userid=admin, username=관리자, userpwd=a1234, address=서울, email=admin@korea.com]
			*/
			
			/* 
			// 수정
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
			
			/* 
			// 삭제
			UserInfo user = entityManager.find(UserInfo.class, "admin");
			entityManager.remove(user);		// 준영속 상태(detached), 1차 캐시에서 삭제, db에는 존재
			transaction.commit();
			// delete from userinfo where userid=?
			*/
			
			/* 
			// 조회: 1차 캐시와 동일성 보장
			// TEST: 삭제->생성->조회 확인
			entityManager.remove(entityManager.find(UserInfo.class, "admin"));
			UserInfo user = new UserInfo("admin", "관리자", "a1234","서울", "admin@korea.com", new Date());
			entityManager.persist(user);
			user = entityManager.find(UserInfo.class, "admin");
			UserInfo user2 = entityManager.find(UserInfo.class, "admin");
			System.out.println(user==user2);
			transaction.commit();
			// true
			*/
			
			///*
			//entityManager.remove(entityManager.find(UserInfo.class, "admin"));
			// 1. 비영속 상태
			UserInfo user = new UserInfo("admin", "관리자", "a1234","서울", "admin@korea.com", new Date());
			//entityManager.persist(user);
			user = entityManager.find(UserInfo.class, "admin");
			System.out.println(">>>>>>>>>>>>1 " + user.hashCode());
			// 2. 준영속 상태
			entityManager.detach(user);
			// 3. name 속성 변경
			user.changeName("kimhj");
			// 4. 영속 상태
			entityManager.merge(user);
			user = entityManager.find(UserInfo.class, "admin");
			System.out.println(">>>>>>>>>>>>2 " + user.hashCode());
			transaction.commit();
			//*/
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }
		
		emf.close();
	}
}
