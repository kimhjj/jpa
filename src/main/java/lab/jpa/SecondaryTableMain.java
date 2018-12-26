package lab.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lab.jpa.model.Address;
import lab.jpa.model.Sight;
import lab.jpa.model.SightDetail;

public class SecondaryTableMain {
	
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
        
        // SecondaryTableMain: 조인 관계인 두 개의 테이블이 생긴다.
        Sight sight = new Sight("경복궁", new Address("03045", "서울시 종로구", "세종로"), new Address("03045", "Jonno-gu, Seoul", "1-1, Sejong-ro"));
        sight.setDetail(new SightDetail("09-17시", "매주 화", "ㅎㅎ"));

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();                    
            entityManager.persist(sight);
            Sight findSight = entityManager.find(Sight.class, 1L);
            findSight.setDetail(new SightDetail("오전9~5", "매주 월", "ㄱㄱ"));
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
