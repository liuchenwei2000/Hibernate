/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * 全外连接(full join)查询示例
 * <p>
 * 左外连接和右外连接的并集，不常用。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class FullJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("【full join】");
		String hql = "from Employee employee full join employee.cards";

		Query query = session.createQuery(hql);
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
	}
}
