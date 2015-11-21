/**
 * 
 */
package hibernate.query;

import hibernate.util.AbstractHibernateTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Query包测试用例基类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public abstract class AbstractQueryTestCase extends AbstractHibernateTestCase {

	protected void prepareData() {
		session.beginTransaction();
		List<Person4> users = createPersons();
		for (Person4 user : users) {
			session.save(user);
		}
		session.getTransaction().commit();
	}
	
	private static List<Person4> createPersons() {
		List<Person4> users = new ArrayList<Person4>();
		users.add(new Person4("Tom", 18));
		users.add(new Person4("Ann", 19));
		users.add(new Person4("Jimmy", 18));
		users.add(new Person4("Peter", 20));
		users.add(new Person4("Kitty", 21));
		users.add(new Person4("Lucy", 21));
		users.add(new Person4("Lily", 21));
		return users;
	}
}
