/**
 * 
 */
package test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月17日
 */
public class TestX {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(createPerson());// insert语句
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Person> result = entityManager.createQuery("from Person", Person.class).getResultList();// select from 语句
		for ( Person person : result ) {
		    System.out.println( "Person : " + person.getFirstName() + " " + person.getLastName());
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManagerFactory.close();
	}

	private static Person createPerson(){
		Person person = new Person();
		person.setFirstName("Tom");
		person.setLastName("Green");
		person.setSex(1);
		person.setTimestamp(new Date());
		return person;
	}
}
