/**
 * 
 */
package hibernate.query.HQL;

import java.util.List;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import org.hibernate.Query;

/**
 * 排序、分组示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class OrderByGroupByTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/** 排序，order by */
		String orderBy = "from Person4 person order by person.age desc";// 按age倒序排列
		Query query = session.createQuery(orderBy);
		List<Person4> result = query.list();
		for (Person4 perosn : result) {
			System.out.println(perosn);
		}
		
		/** 分组，group by */
		// 按age进行分组，只取数据条数大于1的组
		String groupBy = "select count(*), age from Person4 group by age having count(age)>1";
		query = session.createQuery(groupBy);
		List<Object[]> result2 = query.list();
		for (Object[] data : result2) {
			System.out.println("There are " + data[0] + " user's age is " + data[1]);
		}
	}
}
