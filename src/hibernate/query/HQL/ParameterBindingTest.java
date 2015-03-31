/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

/**
 * 参数绑定示例
 * <p>
 * 参数绑定机制可以使得查询语法与具体参数数值相互独立。
 * 这样对于参数不同，查询语法相同的查询操作，数据库即可实时性能优化策略；
 * 同时也杜绝了参数值对查询语法本身的影响，避免了SQL注入的可能。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class ParameterBindingTest extends AbstractQueryTestCase {

	protected void doTest() {
		int age = 18;
		
		System.out.println("【Query by hard code】");
		queryUseHardCode(age);
		
		System.out.println("【Query by Positional Parameters】");
		queryUsePositionalParametersBinding(age);
		
		System.out.println("【Query by Named Parameters】");
		queryUseNamedParametersBinding(age);
		
		System.out.println("【Query by Named Parameters with JavaBean】");
		queryUseNamedParametersBindingWithJavaBean(age);
		
		System.out.println("【Query by Named Parameters with Map】");
		queryUseNamedParametersBindingWithMap(age);
	}

	/**
	 * 硬编码方式的 HQL 查询 
	 * <p>
	 * 硬编码HQL的缺陷：
	 * <p>
	 * 1，编码更加凌乱，可读性差
	 * <p>
	 * 2，难以进行性能优化
	 * JDBC在执行SQL时，数据库将对SQL语法进行解析和优化，并将其处理结果保存在缓存中。
	 * 如果之后有参数不同语法相同的SQL命令，则直接以此缓存结果加以执行，从而避免了SQL解析和优化的开销。
	 * 另外，Hibernate使用了PreparedStatement作为底层数据库访问手段，对于相同的SQL也可以重用已经创建的PreparedStatement对象。
	 * <p>
	 * 3，难以预防SQL Injection（SQL注入）
	 */
	@SuppressWarnings("unchecked")
	private void queryUseHardCode(int age) {
		Query query = session.createQuery(" from Person4 where age=" + age);
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * 顺序占位符形式的参数绑定
	 * <p>
	 * 参数的动态绑定机制则可以妥善处理好以上问题，类似JDBC中的SQL操作，
	 * 可以通过顺序占位符"?"对参数进行标识，并在之后对参数内容进行填充。
	 */
	@SuppressWarnings("unchecked")
	private void queryUsePositionalParametersBinding(int age) {
		Query query = session.createQuery(" from Person4 where age=?");
		query.setInteger(0, age);// 进行参数绑定
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * 名称占位符形式的参数绑定
	 * <p>
	 * 相对于顺序占位符，JPA更推荐使用名称占位符的形式进行查询。
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBinding(int age) {
		// :age 即是名称占位符，它标识了一个名为"age"的查询参数
		Query query = session.createQuery(" from Person4 where age=:age");
		query.setParameter("age", age);// 进行参数绑定
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * 名称占位符形式的参数绑定，同时使用 JavaBean 封装参数
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBindingWithJavaBean(int age) {
		Query query = session.createQuery(" from Person4 where age=:age and name=:name");
		// 查询使用的JavaBean，只要具有 HQL 用到的参数属性即可，不一定非要是Person4
		Person4 queryBean = new Person4();
		queryBean.setAge(age);
		queryBean.setName("Tom");
		
		query.setProperties(queryBean);// 进行参数绑定
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * 名称占位符形式的参数绑定，同时使用 Map 封装参数
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBindingWithMap(int age) {
		Query query = session.createQuery(" from Person4 where age=:age and name=:name");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("age", age);
		queryMap.put("name", "Tom");
		
		query.setProperties(queryMap);// 进行参数绑定
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
