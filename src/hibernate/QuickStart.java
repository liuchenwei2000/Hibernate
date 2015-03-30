/**
 * 
 */
package hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate��������ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��17��
 */
public class QuickStart {
	
	private static SessionFactory createSessionFactory() {
		/*
		 * Configuration �������Hibernate��������Ϣ���ؼ����԰������ݿ�URL���û��������롢JDBC������ȵȡ�
		 * ��Щ���Կ�����Hibernate�����ļ���hibernate.cfg.xml��hibernate.properties���м����趨��
		 * Hibernate���Զ��ڵ�ǰ��classpath����Ѱhibernate.cfg.xml�ļ���������ص��ڴ��У���Ϊ���������Ļ������á�
		 */
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*
		 * The org.hibernate.SessionFactory is a thread-safe global object that is instantiated once.
		 */
		SessionFactory sessionFactory = createSessionFactory();
		
		/*
		 * ��Hibernate�У�Session������ɶ���ĳ־û�������Hibernate Session֮��Hibernate���൱��JDBC Connection ֮�� JDBC��
		 * Session�����ṩ���ڶ�־û�����������͸������ɶ����CRUD������
		 * Session �Ƿ��̰߳�ȫ�ģ�Ҳ����˵һ��Sessionʵ��ͬʱֻ����һ���߳�ʹ�á�
		 */
		/** ������� */
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(createPerson());// insert���
		session.getTransaction().commit();
		session.close();
		
		/**
		 * Query��Criteria��ΪHibernate���ݲ�ѯ�ӿڣ��ṩ�˶Բ�ѯ�����ķ�װ���ơ�
		 * ���߲�֮ͬ�����ڣ�Query����HQL��Native SQL����Criteria���ṩ���������Ĳ�ѯģʽ�� 
		 */
		/** ʹ��Query����Ĳ�ѯ���� */
		session = sessionFactory.openSession();
		//  using a Hibernate Query Language (HQL) query to load all existing Person objects from the database. 
		Query query = session.createQuery( "from PersonVO where sex=?");
		query.setParameter(0, 1);
		List<PersonVO> result = query.list();// select from ���
		for (PersonVO person : result) {
			System.out.println("Person : " + person.getFirstName() + " " + person.getLastName());
		}
		session.close();
		
		/** ʹ��Criteria����Ĳ�ѯ���� */
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PersonVO.class);
		criteria.add(Restrictions.eq("sex", 1));
		result = criteria.list();// select from ���
		for (PersonVO person : result) {
			System.out.println("Person : " + person.getFirstName() + " " + person.getLastName());
		}
		session.close();
		
		sessionFactory.close();
	}

	private static PersonVO createPerson(){
		PersonVO person = new PersonVO();
		person.setFirstName("Vic");
		person.setLastName("Liu");
		person.setSex(1);
		person.setTimestamp(new Date());
		return person;
	}
}
