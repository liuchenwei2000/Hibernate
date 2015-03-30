/**
 * 
 */
package hibernate.query.SQL;

import hibernate.util.AbstractHibernateTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL测试用例基类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public abstract class AbstractSQLQueryTestCase extends AbstractHibernateTestCase {

	protected void prepareData() {
		session.beginTransaction();
		List<Dog> dogs = createDogs();
		Long parentid = null;
		for (Dog dog : dogs) {
			if (parentid != null) {
				dog.setParentid(parentid);
			}
			parentid = (Long) session.save(dog);
		}
		session.getTransaction().commit();
	}

	private static List<Dog> createDogs() {
		List<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog("Bobby ", 3));
		dogs.add(new Dog("Lucky", 4));
		dogs.add(new Dog("Pipo", 4));
		dogs.add(new Dog("Gogo", 3));
		dogs.add(new Dog("Bruce", 2));
		return dogs;
	}
}
