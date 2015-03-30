/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;

import org.hibernate.Query;

/**
 * update、delete操作示例
 * <p>
 * HQL通过update、delete子句，更灵活的实现了数据的删除和更新操作。
 * <p>
 * HQL功能日益完善，同时越来越接近传统的SQL语言。
 * 与SQL不同的是，SQL面向的是二维的结构化数据，而HQL则面向数据对象。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class UpdateDeleteTest extends AbstractQueryTestCase {

	protected void doTest() {
		session.beginTransaction();
		// update子句：对于批量更新操作，其便捷性及性能的提高相当可观。
		// SQL：update tb_person4 set age=22
		Query query = session.createQuery("update Person4 set age=22");
		int result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("update " + result + " rows.");

		session.beginTransaction();
		// delete子句：对于批量更新操作，其便捷性及性能的提高相当可观。
		// SQL：delete from tb_person4 where name='Tom'
		query = session.createQuery("delete Person4 where name='Tom'");
		result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("delete " + result + " rows.");
	}
}
