/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * ʵ���ѯʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class EntityQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/*
		 * ʹ��HQL����һ��Query����HQL��from ���� where ������=ֵ
		 * 
		 * ��Ҫע����ǣ�Hibernate�в�ѯ��Ŀ��ʵ������ż̳й�ϵ���ж��� 
		 * from Person4 ����������Person4��������ļ�¼��������������Ӧ��ͬ�Ŀ�� 
		 * �� from java.lang.Object ���������ݿ������п��ļ�¼��
		 */
		Query query = session.createQuery(" from Person4 where age=19");

		// from �п���ʹ�ñ������where �Ӿ��п���ʹ�� and��or����������
		// Query query = session.createQuery(" from Person4 T0 where T0.age=19 and T0.name='Ann'");

		// ������HQL��ʹ��ȫ·����������������ͬ�����ʱ��
		// HQL�Ӿ䱾��Դ�Сд�����У������ֵ�����������������ע�����ִ�Сд��
		// Query query = session.createQuery(" from hibernate.query.Person4 where age=19");

		// query.list() ��������ִ�в�ѯ
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
