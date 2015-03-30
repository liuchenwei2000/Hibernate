/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

/**
 * Projections示例
 * <p>
 * Criteria高级特性：排序、分组、统计等等。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月28日
 */
public class ProjectionsTest extends AbstractQueryTestCase {

	protected void doTest() {
		testOrder(session);
		testGroup(session);
		testStatistics(session);
	}
	
	/**
	 * 对查询的结果集进行排序
	 */
	@SuppressWarnings("unchecked")
	private static void testOrder(Session session) {
		System.out.println("【order】");
		Criteria criteria = session.createCriteria(Person4.class);
		criteria.addOrder(Order.desc("age"));// 按age倒序排列
		criteria.addOrder(Order.asc("name"));// 按name升序排列
		
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * 对查询的结果集进行分组
	 */
	@SuppressWarnings("unchecked")
	private static void testGroup(Session session) {
		System.out.println("【group】");
		Criteria criteria = session.createCriteria(Person4.class);
		// 按age进行分组
		criteria.setProjection(Projections.groupProperty("age"));
		// SQL：select this_.age as y0_ from tb_person4 this_ group by this_.age
		List<Integer> result = criteria.list();
		for (Integer age : result) {
			System.out.println(age);
		}
	}
	
	/**
	 * 对查询的结果集进行统计
	 */
	@SuppressWarnings("unchecked")
	private static void testStatistics(Session session) {
		System.out.println("【statistics】");
		
		Criteria criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.rowCount());// 统计行数
		// SQL：select count(*) as y0_ from tb_person4 this_
		System.out.println("row count:" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.avg("age"));// 统计平均数
		// SQL：select avg(this_.age) as y0_ from tb_person4 this_
		System.out.println("avg(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.max("age"));// 统计最大值
		// SQL：select max(this_.age) as y0_ from tb_person4 this_
		System.out.println("max(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.min("age"));// 统计最小值
		// SQL：select min(this_.age) as y0_ from tb_person4 this_
		System.out.println("min(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.countDistinct("age"));// 统计distinct值
		// SQL：select count(distinct this_.age) as y0_ from tb_person4 this_
		System.out.println("countDistinct(age):" + criteria.list().get(0));
		
		// 对于多条件组合的统计、分组功能，可以使用ProjectionList完成
		// 统计各个年龄段的人数
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("age"));
		projectionList.add(Projections.rowCount());
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(projectionList);
		System.out.println("统计各个年龄段的人数:");
		// SQL：select this_.age as y0_, count(*) as y1_ from tb_person4 this_ group by this_.age
		List<Object> result = criteria.list();
		for (Object row : result) {
			Object[] data = (Object[]) row;
			System.out.println("age=" + data[0] + ", count=" + data[1]);
		}
	}
}
