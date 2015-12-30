/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.HQL.join.AbstractJoinQueryTestCase;
import hibernate.query.HQL.join.Employee;

import java.util.List;

import org.hibernate.Query;

/**
 * 子查询示例
 * <p>
 * 子查询可以在SQL中利用另外一条SQL的查询结果，HQL支持此机制。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class SubQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// 查出有借记卡的雇员
		// 子查询必须出现在where子句中，且必须以一对圆括号包围。
		String hql = "from Employee employee where (select count(*) from employee.cards)>0";
		Query query = session.createQuery(hql);

		List<Employee> result = query.list();
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}
}
