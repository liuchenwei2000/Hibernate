/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * 外部HQL查询示例
 * <p>
 * 有时候代码中不允许出现SQL语句，SQL语句混杂在代码之间将破坏代码的可读性，并使得系统的可维护性降低。
 * 因此，通常采取将SQL配置化的方式保存在配置文件文件中，需要的时候再读取。
 * <p>
 * Hibernate提供了HQL可配置化的内置支持。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class ExternalHQLTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		int age = 18;
		
		// 从配置文件中，根据名称调用配置的HQL，详见配置文件 hibernate\query\Person4.hbm.xml
		Query query = session.getNamedQuery("queryByAge");
		query.setParameter("age", age);// 进行参数绑定
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
