/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * DetachedCriteria示例
 * <p>
 * Criteria生命周期位于其宿主Session生命周期之内。也就是说，
 * 由某个Session创建的Criteria实例，一旦Session销毁，此Criteria实例也随之失效。
 * 这很大程度上限制了Criteria的重用，对于相同的Criteria条件，每次都必须由当前Session构造其实例非常繁琐。
 * <p>
 * DetachedCriteria可以脱离Session实例独立存在，这样就可以将某些通用的Criteria
 * 查询条件进行抽离，每次使用时再与当前Session实例绑定以获得重用效果。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class DetachedCriteriaTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// 独立于Session创建一个DetachedCriteria，并添加查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Person4.class);
		detachedCriteria.add(Restrictions.lt("age", 20));
		
		// 需要使用时与Session相绑定，从而获得运行期Criteria实例
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
