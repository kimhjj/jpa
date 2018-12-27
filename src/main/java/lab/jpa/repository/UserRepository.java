package lab.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import lab.jpa.LabEntityManagerFactory;
import lab.jpa.model.UserInfo;

public class UserRepository {

    public UserInfo find(String userid) {
        EntityManager em = LabEntityManagerFactory.currentEntityManager();
        return em.find(UserInfo.class, userid);
    }
    
    public void save(UserInfo user) {
        EntityManager em = LabEntityManagerFactory.currentEntityManager();
        em.persist(user);
    }

    public void remove(UserInfo user) {
        EntityManager em = LabEntityManagerFactory.currentEntityManager();
        em.remove(user);
    }
    
    public List<UserInfo> findAll(String userid) {
        EntityManager em = LabEntityManagerFactory.currentEntityManager();
        TypedQuery<UserInfo> query = em.createQuery("select u from Userinfo u order by u.username", UserInfo.class);
        return query.getResultList();
    }
}