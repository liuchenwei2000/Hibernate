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
 * Exampleʾ��
 * <p>
 * Example��ʵ����Criteria�ӿڣ���������Criteria�Ĳ�ѯ���������������ǣ�
 * �������ж��󣬲���������֮�������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
 */
public class ExampleTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// �½���һ��Example���󣬲��Դ���Ϊ��������ѯ����name������֮��ͬ��Person4��¼��
		Person4 p4 = new Person4();
		p4.setName("Tom");
		p4.setAge(18);
		// Ĭ������£�Hibernate����˵�ʾ�������nullֵ���ԡ�
		Example example = Example.create(p4);
		// ����ͨ����������ķ�������������Խ��е���
		// example.excludeNone();
		// ����ͨ����������ķ�����ĳ�������ų�����
		// example.excludeProperty("id");

		Criteria criteria = session.createCriteria(Person4.class);
		criteria.add(example);// ��ӵ�Criteria

		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}

		/*
		 * ʾ����ѯ��õĳ�������ϲ�ѯ��������Ҫ�ڽ������ṩ���ɲ�ѯѡ�Ȼ������û���ѡ�񷵻ط��������Ľ����
		 * ����ڴ����н����жϣ������û�����Ĳ�ѯ�������յĲ�ѯ������
		 * ������������Ĳ�ȷ�������������߼��ж���������������ʹ��ʾ����ѯ�������ɺܶࡣ
		 */
		criteria = session.createCriteria(Person4.class);

		String name = "";
		Integer age = 0;
		// ��ͳ����ʽ��������ԱȽ϶�Ļ�����
		if (name != null) {
			criteria.add(Restrictions.eq("name", name));
		}
		if (age != null) {
			criteria.add(Restrictions.eq("age", age));
		}
		// Example����ʽ
		Person4 person = new Person4(name, age);
		criteria.add(Example.create(person));
	}
}
