/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * Example示例
 * <p>
 * Example类实现了Criteria接口，可以用作Criteria的查询条件，它的作用是：
 * 根据已有对象，查找属性与之相符的其它对象。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月28日
 */
public class ExampleTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// 新建了一个Example对象，并以此作为范本，查询所有name属性与之相同的Person4记录。
		Person4 p4 = new Person4();
		p4.setName("Tom");
		p4.setAge(18);
		// 默认情况下，Hibernate会过滤掉示例对象的null值属性。
		Example example = Example.create(p4);
		// 可以通过调用下面的方法来对这个特性进行调整
		// example.excludeNone();
		// 或者通过调用下面的方法将某个属性排除在外
		// example.excludeProperty("id");

		Criteria criteria = session.createCriteria(Person4.class);
		criteria.add(example);// 添加到Criteria

		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}

		/*
		 * 示例查询最常用的场景是组合查询，比如需要在界面上提供若干查询选项，然后根据用户的选择返回符合条件的结果。
		 * 如果在代码中进行判断，根据用户输入的查询生成最终的查询条件，
		 * 由于组合条件的不确定，往往导致逻辑判断语句拖沓起伏。如果使用示例查询则能轻松很多。
		 */
		criteria = session.createCriteria(Person4.class);

		String name = "";
		Integer age = 0;
		// 传统处理方式，如果属性比较多的话……
		if (name != null) {
			criteria.add(Restrictions.eq("name", name));
		}
		if (age != null) {
			criteria.add(Restrictions.eq("age", age));
		}
		// Example处理方式
		Person4 person = new Person4(name, age);
		criteria.add(Example.create(person));
	}
}
