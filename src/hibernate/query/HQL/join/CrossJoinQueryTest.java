/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * 交叉连接(cross join)查询示例
 * <p>
 * 即笛卡儿积，是指两个关系中所有元组的任意组合。
 * 一般情况下，交叉查询是没有实际意义的。
 * <p>
 * 交叉连接对两表关系没有要求，而内外连接则要求两表有主外键关联关系。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class CrossJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("【cross join】");
		String hql = "from Employee, DebitCard";

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
