/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Criteria示例
 * <p>
 * Criteria查询通过面向对象的设计，将数据查询条件封装为一个对象，可以看做传统SQL的对象化表示。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月28日
 */
public class CriteriaTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		Criteria criteria = session.createCriteria(Person4.class);
		/*
		 * Criteria本身只是一个查询容器，具体的查询条件要通过criteria.add方法添加到Criteria实例中。
		 * Hibernate在运行期会根据Criteria中指定的查询条件（即通过criteria.add方法添加的查询表达式）生成相应的SQL语句。
		 * 这种方式比较符合Java程序员的编码习惯，并且具备清晰的可读性。
		 * 
		 * 对应的SQL语句： select * from tb_person4 where name='Jimmy' and age=18
		 */
		criteria.add(Restrictions.eq("name", "Jimmy")).add(
				Restrictions.eq("age", 18));

		// criteria.list() 根据条件执行查询
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
