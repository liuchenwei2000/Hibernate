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
 * Hibernate快速入门示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月17日
 */
public class QuickStart {
	
	private static SessionFactory createSessionFactory() {
		/*
		 * Configuration 负责管理Hibernate的配置信息，关键属性包括数据库URL、用户名、密码、JDBC驱动类等等。
		 * 这些属性可以在Hibernate配置文件（hibernate.cfg.xml或hibernate.properties）中加以设定。
		 * Hibernate会自动在当前的classpath中搜寻hibernate.cfg.xml文件并将其加载到内存中，作为后续操作的基础配置。
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
		 * 在Hibernate中，Session负责完成对象的持久化操作，Hibernate Session之于Hibernate，相当于JDBC Connection 之于 JDBC。
		 * Session 对象提供了众多持久化方法，可以透明的完成对象的CRUD操作。
		 * Session 是非线程安全的，也就是说一个Session实例同时只可由一个线程使用。
		 */
		/** 保存操作 */
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(createPerson());// insert语句
		session.getTransaction().commit();
		session.close();
		
		/**
		 * Query和Criteria作为Hibernate数据查询接口，提供了对查询条件的封装机制。
		 * 两者不同之处在于，Query面向HQL和Native SQL，而Criteria则提供了面向对象的查询模式。 
		 */
		/** 使用Query对象的查询操作 */
		session = sessionFactory.openSession();
		//  using a Hibernate Query Language (HQL) query to load all existing Person objects from the database. 
		Query query = session.createQuery( "from PersonVO where sex=?");
		query.setParameter(0, 1);
		List<PersonVO> result = query.list();// select from 语句
		for (PersonVO person : result) {
			System.out.println("Person : " + person.getFirstName() + " " + person.getLastName());
		}
		session.close();
		
		/** 使用Criteria对象的查询操作 */
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PersonVO.class);
		criteria.add(Restrictions.eq("sex", 1));
		result = criteria.list();// select from 语句
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
