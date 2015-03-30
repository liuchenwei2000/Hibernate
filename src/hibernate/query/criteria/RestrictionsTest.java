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
 * Restrictionsʾ��
 * <p>
 * Restrictions������������˲�ѯ�������ṩ����SQL��Ӧ�Ĳ�ѯ�޶����ơ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
 */
public class RestrictionsTest extends AbstractQueryTestCase {

	@SuppressWarnings("rawtypes")
	protected void doTest() {
		/** ����(��)��ֵ�Ƚϣ��������������POJO�ж�Ӧʵ�ʿ���ֶε�������(��Сд����)�����ǿ���е�ʵ���ֶ����ơ� */
		// SQL��name='Tom'
		Criterion criteria = Restrictions.eq("name", "Tom");

		Map<String, Object> name_value_map = new HashMap<String, Object>();
		name_value_map.put("name", "Tome");
		name_value_map.put("age", 19);
		// SQL��name='Tom' and age=19
		criteria = Restrictions.allEq(name_value_map);
		
		// SQL��age>19
		criteria = Restrictions.gt("age", 19);
		// SQL��age>=19
		criteria = Restrictions.ge("age", 19);
		// SQL��age<19
		criteria = Restrictions.lt("age", 19);
		// SQL��age<=19
		criteria = Restrictions.le("age", 19);
		
		// SQL��age>19 and age<22
		criteria = Restrictions.between("age", 19, 22);
		// SQL��name like 'T%'
		criteria = Restrictions.like("name", "T%");
		// SQL��name in ('Tom', 'Ann')
		criteria = Restrictions.in("name", new String[] { "Tome", "Ann" });
		
		/** ����(��)������(��)�Ƚ� */
		// SQL��age=id
		criteria = Restrictions.eqProperty("age", "id");
		// SQL��age>id
		criteria = Restrictions.gtProperty("age", "id");
		// SQL��age>=id
		criteria = Restrictions.geProperty("age", "id");
		// SQL��age<id
		criteria = Restrictions.ltProperty("age", "id");
		// SQL��age<=id
		criteria = Restrictions.leProperty("age", "id");
		
		/** ��ϵ��� */
		// AND��ϵ��ϣ�SQL��name='Tom' and age=19
		criteria = Restrictions.and(Restrictions.eq("name", "Tom"),
				Restrictions.gt("age", 19));
		// OR��ϵ��ϣ�SQL��name='Tom' or age=19
		criteria = Restrictions.or(Restrictions.eq("name", "Tom"),
				Restrictions.gt("age", 19));
		// �ṩ�˶�ԭ��SQL��֧�֣����С�{alias}������Hibernate��������ʹ�õ�ǰ������POJO�����滻
		criteria = Restrictions.sqlRestriction("{alias}.name like 'A%'");
		criteria = Restrictions.sqlRestriction("{alias}.name like ?", "A%", StringType.INSTANCE);
		
		// ʹ��Criteria������в�ѯ
		Criteria totalCriteria = session.createCriteria(Person4.class);
		totalCriteria.add(criteria);
		List result = totalCriteria.list();
		System.out.println(result.size());
	}
}
