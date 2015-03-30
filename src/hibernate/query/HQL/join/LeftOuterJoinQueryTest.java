/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

/**
 * ��������(left outer join)��ѯʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class LeftOuterJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("��left outer join��");
		String hql = "from Employee employee left outer join employee.cards";

		Query query = session.createQuery(hql);
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}

		hql = "from Employee employee left outer join fetch employee.cards";

		query = session.createQuery(hql);
		List<Employee> result2 = query.list();
		for (Employee employee : result2) {
			System.out.print(employee + "  ");
			Set<DebitCard> cards = employee.getCards();
			if (cards == null || cards.isEmpty()) {
				System.out.print("null");
			} else {
				for (DebitCard debitCard : cards) {
					System.out.print(debitCard + "  ");
				}
			}
			System.out.println();
		}
	}
}
