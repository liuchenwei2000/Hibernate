/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Query;

/**
 * �ⲿHQL��ѯʾ��
 * <p>
 * ��ʱ������в��������SQL��䣬SQL�������ڴ���֮�佫�ƻ�����Ŀɶ��ԣ���ʹ��ϵͳ�Ŀ�ά���Խ��͡�
 * ��ˣ�ͨ����ȡ��SQL���û��ķ�ʽ�����������ļ��ļ��У���Ҫ��ʱ���ٶ�ȡ��
 * <p>
 * Hibernate�ṩ��HQL�����û�������֧�֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class ExternalHQLTest extends AbstractQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		int age = 18;
		
		// �������ļ��У��������Ƶ������õ�HQL����������ļ� hibernate\query\Person4.hbm.xml
		Query query = session.getNamedQuery("queryByAge");
		query.setParameter("age", age);// ���в�����
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
