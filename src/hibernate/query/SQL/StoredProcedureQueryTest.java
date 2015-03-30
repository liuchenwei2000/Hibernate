/**
 * 
 */
package hibernate.query.SQL;

import java.util.List;

import org.hibernate.Query;

/**
 * 调用存储过程示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class StoredProcedureQueryTest extends AbstractSQLQueryTestCase {

	protected void doTest() {
		callByNativeSQL();
		callByNamedSQL();
	}

	@SuppressWarnings("unchecked")
	private void callByNativeSQL() {
		System.out.println("【callByNativeSQL】");
		Query query = session.createSQLQuery("call getDogByName(:name)").addEntity(Dog.class);
		query.setParameter("name", "Bobby");
		
		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
	}

	@SuppressWarnings("unchecked")
	private void callByNamedSQL() {
		System.out.println("【callByNamedSQL】");
		Query query = session.getNamedQuery("getDogByName");
		query.setParameter("name", "Bobby");
		
		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
	}
}
