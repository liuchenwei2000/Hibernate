/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * ���Բ�ѯʾ��
 * <p>
 * ��ʱ��ֻ��Ҫ��ѯʵ������ĳ�����ԣ�����¼�е�ĳ������Ϣ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class PropertyQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		/** ֻ��ѯ name ���� */
		Query query = session.createQuery("select name from Person4");
		List<String> result = query.list();
		for (String name : result) {// ÿ��Ԫ�ض���String����
			System.out.println(name);
		}

		/** ֻ��ѯ name,age ���� */
		query = session.createQuery("select name,age from Person4");
		List<Object> result2 = query.list();
		for (Object data : result2) {
			// ÿ��Ԫ�ض���Object[]����
			Object[] values = (Object[]) data;
			System.out.println(values[0] + "-" + values[1]);
		}

		/**
		 * ֻ��ѯ name,age ���� 
		 * ����ķ�ʽ���������������ķ�񣬿���ͨ����HQL�ж�̬�������ʵ���ķ�������Щƽ�滯�����ݽ��з�װ
		 */
		query = session.createQuery("select new Person4(name,age) from Person4");
		List<Person4> result3 = query.list();
		for (Person4 person : result3) {
			// ��Ϊֻ��ѯ�� name��age ���ԣ�����id ������û��ֵ��
			System.out.println(person.getName() + "-" + person.getAge() + "-" + person.getId());
		}

		/** Ҳ����ʹ��ͳ�ƺ���������sql���� */
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
