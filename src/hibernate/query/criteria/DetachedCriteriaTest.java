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
 * DetachedCriteriaʾ��
 * <p>
 * Criteria��������λ��������Session��������֮�ڡ�Ҳ����˵��
 * ��ĳ��Session������Criteriaʵ����һ��Session���٣���Criteriaʵ��Ҳ��֮ʧЧ��
 * ��ܴ�̶���������Criteria�����ã�������ͬ��Criteria������ÿ�ζ������ɵ�ǰSession������ʵ���ǳ�������
 * <p>
 * DetachedCriteria��������Sessionʵ���������ڣ������Ϳ��Խ�ĳЩͨ�õ�Criteria
 * ��ѯ�������г��룬ÿ��ʹ��ʱ���뵱ǰSessionʵ�����Ի������Ч����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class DetachedCriteriaTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// ������Session����һ��DetachedCriteria������Ӳ�ѯ����
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Person4.class);
		detachedCriteria.add(Restrictions.lt("age", 20));
		
		// ��Ҫʹ��ʱ��Session��󶨣��Ӷ����������Criteriaʵ��
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
