package lab.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lab.jpa.model.MonChargeId;
import lab.jpa.model.MonthCharge;

public class ComplexKeyMain {
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
        	MonChargeId mc_id = new MonChargeId("H100", "201812");
        	
        	transaction.begin();
	        MonthCharge mc = new MonthCharge();
	        mc.setId(mc_id);
	        mc.setChargeAmount(1000000);
	        mc.setUnpayAmount(800000);
	        entityManager.persist(mc);
	        transaction.commit();
	        
	        transaction.begin(); 
	        mc = new MonthCharge();
	        mc.setId(mc_id);
	        mc.setChargeAmount(1200000);
	        mc.setUnpayAmount(900000);
	        entityManager.persist(mc);
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
