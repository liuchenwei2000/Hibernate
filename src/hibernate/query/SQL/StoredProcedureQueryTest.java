/**
 * 
 */
package hibernate.query.SQL;

import java.util.List;

import org.hibernate.Query;

/**
 * ���ô洢����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class StoredProcedureQueryTest extends AbstractSQLQueryTestCase {

	protected void doTest() {
		callByNativeSQL();
		callByNamedSQL();
	}

	@SuppressWarnings("unchecked")
	private void callByNativeSQL() {
		System.out.println("��callByNativeSQL��");
		Query query = session.createSQLQuery("call getDogByName(:name)").addEntity(Dog.class);
		query.setParameter("name", "Bobby");
		
		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
	}

	@SuppressWarnings("unchecked")
	private void callByNamedSQL() {
		System.out.println("��callByNamedSQL��");
		Query query = session.getNamedQuery("getDogByName");
		query.setParameter("name", "Bobby");
		
		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
	}
}
