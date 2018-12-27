package lab.jpa.service;

import javax.persistence.EntityManager;

import lab.jpa.LabEntityManagerFactory;
import lab.jpa.model.UserInfo;
import lab.jpa.repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	
    public void join(UserInfo user) {
        EntityManager em = LabEntityManagerFactory.createEntityManager(); 
        try {
             em.getTransaction().begin();
       
             UserInfo found = em.find(UserInfo.class, user.getUserid());
            if (found != null) {
                //throw new DuplicateUseridException();
            }
            userRepository.save(user);
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
        	LabEntityManagerFactory.closeCurrentEntityManager();
        }
    }
}
