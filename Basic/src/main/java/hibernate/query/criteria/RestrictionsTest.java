/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

/**
 * Restrictions示例
 * <p>
 * Restrictions对象具体描述了查询条件，提供了与SQL对应的查询限定机制。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月28日
 */
public class RestrictionsTest extends AbstractQueryTestCase {

	@SuppressWarnings("rawtypes")
	protected void doTest() {
		/** 属性(列)与值比较：这里的属性名是POJO中对应实际库表字段的属性名(大小写敏感)，而非库表中的实际字段名称。 */
		// SQL：name='Tom'
		Criterion criteria = Restrictions.eq("name", "Tom");

		Map<String, Object> name_value_map = new HashMap<String, Object>();
		name_value_map.put("name", "Tome");
		name_value_map.put("age", 19);
		// SQL：name='Tom' and age=19
		criteria = Restrictions.allEq(name_value_map);
		
		// SQL：age>19
		criteria = Restrictions.gt("age", 19);
		// SQL：age>=19
		criteria = Restrictions.ge("age", 19);
		// SQL：age<19
		criteria = Restrictions.lt("age", 19);
		// SQL：age<=19
		criteria = Restrictions.le("age", 19);
		
		// SQL：age>19 and age<22
		criteria = Restrictions.between("age", 19, 22);
		// SQL：name like 'T%'
		criteria = Restrictions.like("name", "T%");
		// SQL：name in ('Tom', 'Ann')
		criteria = Restrictions.in("name", new String[] { "Tome", "Ann" });
		
		/** 属性(列)与属性(列)比较 */
		// SQL：age=id
		criteria = Restrictions.eqProperty("age", "id");
		// SQL：age>id
		criteria = Restrictions.gtProperty("age", "id");
		// SQL：age>=id
		criteria = Restrictions.geProperty("age", "id");
		// SQL：age<id
		criteria = Restrictions.ltProperty("age", "id");
		// SQL：age<=id
		criteria = Restrictions.leProperty("age", "id");
		
		/** 关系组合 */
		// AND关系组合，SQL：name='Tom' and age=19
		criteria = Restrictions.and(Restrictions.eq("name", "Tom"),
				Restrictions.gt("age", 19));
		// OR关系组合，SQL：name='Tom' or age=19
		criteria = Restrictions.or(Restrictions.eq("name", "Tom"),
				Restrictions.gt("age", 19));
		// 提供了对原生SQL的支持，其中“{alias}”将由Hibernate在运行期使用当前关联的POJO别名替换
		criteria = Restrictions.sqlRestriction("{alias}.name like 'A%'");
		criteria = Restrictions.sqlRestriction("{alias}.name like ?", "A%", StringType.INSTANCE);
		
		// 使用Criteria对象进行查询
		Criteria totalCriteria = session.createCriteria(Person4.class);
		totalCriteria.add(criteria);
		List result = totalCriteria.list();
		System.out.println(result.size());
	}
}
