/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

/**
 * 内连接(inner join)查询示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class InnerJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("【inner join】");
		// 这里必须要用 表别名，之所以没有 on 子句是因为映射关系已经在hbm文件中指定了。
		String hql = "from Employee employee inner join employee.cards";

		Query query = session.createQuery(hql);
		// 得到的结果集中每个条目都是一个Object数组
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			// objects[0] 是 Employee，objects[1] 是 DebitCard
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}

		// fetch 关键字表明 DebitCard 对象读出后立即填充到对应的Employee对象中
		hql = "from Employee employee inner join fetch employee.cards";

		query = session.createQuery(hql);
		List<Employee> result2 = query.list();
		for (Employee employee : result2) {
			System.out.print(employee + "  ");
			Set<DebitCard> cards = employee.getCards();
			for (DebitCard debitCard : cards) {
				System.out.print(debitCard + "  ");
			}
			System.out.println();
		}
	}
}
