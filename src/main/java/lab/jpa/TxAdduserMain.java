package lab.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lab.jpa.model.UserInfo;
import lab.jpa.service.UserService;

public class TxAdduserMain {
	//EntityManager의 영속성 컨텍스트 전파 예제
    public static void main(String[] args) {   
    	LabEntityManagerFactory.init();
    	EntityManager entityManager = LabEntityManagerFactory.createEntityManager(); 
    	
        EntityTransaction transaction = entityManager.getTransaction();
         try {
        	 
             transaction.begin();                 	        
            UserInfo user = new UserInfo("guest", "방문자", "guest1","인천",  "guest@korea.com", new Date( ));
            UserService service = new UserService();
            service.join(user);
            
            transaction.commit();
         } catch (Exception ex) {
             ex.printStackTrace();
             transaction.rollback();
         } finally {
        	LabEntityManagerFactory.closeCurrentEntityManager();
         }
        
    }
}
