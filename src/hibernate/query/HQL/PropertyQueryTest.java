/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * 属性查询示例
 * <p>
 * 有时候只需要查询实体对象的某个属性（库表记录中的某个列信息）。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class PropertyQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/** 只查询 name 属性 */
		Query query = session.createQuery("select name from Person4");
		List<String> result = query.list();
		for (String name : result) {// 每个元素都是String类型
			System.out.println(name);
		}

		/** 只查询 name,age 属性 */
		query = session.createQuery("select name,age from Person4");
		List<Object> result2 = query.list();
		for (Object data : result2) {
			// 每个元素都是Object[]类型
			Object[] values = (Object[]) data;
			System.out.println(values[0] + "-" + values[1]);
		}

		/**
		 * 只查询 name,age 属性 
		 * 上面的方式不够符合面向对象的风格，可以通过在HQL中动态构造对象实例的方法对这些平面化的数据进行封装
		 */
		query = session.createQuery("select new Person4(name,age) from Person4");
		List<Person4> result3 = query.list();
		for (Person4 person : result3) {
			// 因为只查询了 name、age 属性，所以id 属性是没有值的
			System.out.println(person.getName() + "-" + person.getAge() + "-" + person.getId());
		}

		/** 也可以使用统计函数和其他sql特性 */
		query = session.createQuery("select count(*) from Person4");
		List<Integer> result4 = query.list();
		System.out.println(result4.get(0));

		query = session.createQuery("select distinct age from Person4");
		List<Integer> result5 = query.list();
		for (Integer age : result5) {
			System.out.println(age);
		}
	}
}
