/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * 右外连接(right outer join)查询示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class RightOuterJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("【right outer join】");
		String hql = "from Employee employee right outer join employee.cards";

		Query query = session.createQuery(hql);
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
		/*
		 * fetch 关键只对 inner join 和 left outer join 有效。
		 * 对于 right outer join 而言，由于作为关联对象容器的Employee对象可能为null，
		 * 所以也就无法通过fetch关键字强制Hibernate进行集合填充操作了。
		 */
	}
}
