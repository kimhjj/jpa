package lab.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lab.jpa.model.Review;

public class SequenceMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
		
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			Review  review = new Review("KR-S-01", 5, "최고입니다.", new Date());
			entityManager.persist(review); //select hotel_review_seq.nextval from dual 쿼리 실행
			Long id = review.getId(); //시퀀스에서 구한 식별자는 엔티티에 할당된
			System.out.println(id);
			transaction.commit();  //insert into hotel_review 쿼리  실행
			review = entityManager.find(Review.class, 1L);
			System.out.println(review);
			
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }
		
		emf.close();
	}
}
