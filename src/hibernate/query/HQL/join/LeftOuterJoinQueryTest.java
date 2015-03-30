/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

/**
 * 左外连接(left outer join)查询示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class LeftOuterJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("【left outer join】");
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
