/**
 * 
 */
package test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * ʾ��
 * <p>
 * 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��17��
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(createPerson());// insert���
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Person> result = session.createQuery( "from Person" ).list();// select from ���
		for ( Person person : result ) {
		    System.out.println( "Person : " + person.getFirstName() + " " + person.getLastName());
		}
		session.getTransaction().commit();
		session.close();
		
		sessionFactory.close();
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
