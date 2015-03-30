/**
 * 
 */
package hibernate.query.HQL;

import java.util.List;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import org.hibernate.Query;

/**
 * ���򡢷���ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class OrderByGroupByTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/** ����order by */
		String orderBy = "from Person4 person order by person.age desc";// ��age��������
		Query query = session.createQuery(orderBy);
		List<Person4> result = query.list();
		for (Person4 perosn : result) {
			System.out.println(perosn);
		}
		
		/** ���飬group by */
		// ��age���з��飬ֻȡ������������1����
		String groupBy = "select count(*), age from Person4 group by age having count(age)>1";
		query = session.createQuery(groupBy);
		List<Object[]> result2 = query.list();
		for (Object[] data : result2) {
			System.out.println("There are " + data[0] + " user's age is " + data[1]);
		}
	}
}
