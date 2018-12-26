package lab.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lab.jpa.model.Address;
import lab.jpa.model.Grade;
import lab.jpa.model.Hotel;

public class EmbededMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
		
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			
			Hotel hotel = new Hotel("A001", "hj", Grade.STAR3, null);
			entityManager.persist(hotel);
			
			Hotel hotel_find = entityManager.find(Hotel.class, "A001");
			Address address = hotel_find.getAddress();
			System.out.println(address);
			System.out.println(hotel_find);
			
			hotel_find.changeAddress(new Address("08342", "서울시 땡땡구", "땡땡건물"));
			address = hotel_find.getAddress();
			System.out.println(address);
			System.out.println(hotel_find);
			transaction.commit();
			
			// null
			// Hotel [id=A001, name=hj, grade=STAR3, address=null]
			// Address{zipcode='08342', address1='서울시 땡땡구', address2='땡땡건물'}
			// Hotel [id=A001, name=hj, grade=STAR3, address=Address{zipcode='08342', address1='서울시 땡땡구', address2='땡땡건물'}]

			
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }
		
		emf.close();
	}
}
