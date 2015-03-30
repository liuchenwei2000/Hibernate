/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * 实体查询示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class EntityQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/*
		 * 使用HQL创建一个Query对象，HQL：from 类名 where 属性名=值
		 * 
		 * 需要注意的是，Hibernate中查询的目标实体存在着继承关系的判定： 
		 * from Person4 将返回所有Person4及其子类的记录，即便其子类会对应不同的库表。 
		 * 如 from java.lang.Object 将返回数据库中所有库表的记录。
		 */
		Query query = session.createQuery(" from Person4 where age=19");

		// from 中可以使用表别名；where 子句中可以使用 and、or这类的运算符
		// Query query = session.createQuery(" from Person4 T0 where T0.age=19 and T0.name='Ann'");

		// 可以在HQL中使用全路径类名，尤其是有同名类的时候
		// HQL子句本身对大小写不敏感，但出现的类名和属性名必须注意区分大小写。
		// Query query = session.createQuery(" from hibernate.query.Person4 where age=19");

		// query.list() 根据条件执行查询
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
