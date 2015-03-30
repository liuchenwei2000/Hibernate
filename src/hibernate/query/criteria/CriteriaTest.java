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
 * Criteriaʾ��
 * <p>
 * Criteria��ѯͨ������������ƣ������ݲ�ѯ������װΪһ�����󣬿��Կ�����ͳSQL�Ķ��󻯱�ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
 */
public class CriteriaTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		Criteria criteria = session.createCriteria(Person4.class);
		/*
		 * Criteria����ֻ��һ����ѯ����������Ĳ�ѯ����Ҫͨ��criteria.add������ӵ�Criteriaʵ���С�
		 * Hibernate�������ڻ����Criteria��ָ���Ĳ�ѯ��������ͨ��criteria.add������ӵĲ�ѯ���ʽ��������Ӧ��SQL��䡣
		 * ���ַ�ʽ�ȽϷ���Java����Ա�ı���ϰ�ߣ����Ҿ߱������Ŀɶ��ԡ�
		 * 
		 * ��Ӧ��SQL��䣺 select * from tb_person4 where name='Jimmy' and age=18
		 */
		criteria.add(Restrictions.eq("name", "Jimmy")).add(
				Restrictions.eq("age", 18));

		// criteria.list() ��������ִ�в�ѯ
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
