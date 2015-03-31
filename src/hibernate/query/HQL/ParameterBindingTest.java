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
 * ������ʾ��
 * <p>
 * �����󶨻��ƿ���ʹ�ò�ѯ�﷨����������ֵ�໥������
 * �������ڲ�����ͬ����ѯ�﷨��ͬ�Ĳ�ѯ���������ݿ⼴��ʵʱ�����Ż����ԣ�
 * ͬʱҲ�ž��˲���ֵ�Բ�ѯ�﷨�����Ӱ�죬������SQLע��Ŀ��ܡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class ParameterBindingTest extends AbstractQueryTestCase {

	protected void doTest() {
		int age = 18;
		
		System.out.println("��Query by hard code��");
		queryUseHardCode(age);
		
		System.out.println("��Query by Positional Parameters��");
		queryUsePositionalParametersBinding(age);
		
		System.out.println("��Query by Named Parameters��");
		queryUseNamedParametersBinding(age);
		
		System.out.println("��Query by Named Parameters with JavaBean��");
		queryUseNamedParametersBindingWithJavaBean(age);
		
		System.out.println("��Query by Named Parameters with Map��");
		queryUseNamedParametersBindingWithMap(age);
	}

	/**
	 * Ӳ���뷽ʽ�� HQL ��ѯ 
	 * <p>
	 * Ӳ����HQL��ȱ�ݣ�
	 * <p>
	 * 1������������ң��ɶ��Բ�
	 * <p>
	 * 2�����Խ��������Ż�
	 * JDBC��ִ��SQLʱ�����ݿ⽫��SQL�﷨���н������Ż��������䴦���������ڻ����С�
	 * ���֮���в�����ͬ�﷨��ͬ��SQL�����ֱ���Դ˻���������ִ�У��Ӷ�������SQL�������Ż��Ŀ�����
	 * ���⣬Hibernateʹ����PreparedStatement��Ϊ�ײ����ݿ�����ֶΣ�������ͬ��SQLҲ���������Ѿ�������PreparedStatement����
	 * <p>
	 * 3������Ԥ��SQL Injection��SQLע�룩
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
	 * ˳��ռλ����ʽ�Ĳ�����
	 * <p>
	 * �����Ķ�̬�󶨻�����������ƴ�����������⣬����JDBC�е�SQL������
	 * ����ͨ��˳��ռλ��"?"�Բ������б�ʶ������֮��Բ������ݽ�����䡣
	 */
	@SuppressWarnings("unchecked")
	private void queryUsePositionalParametersBinding(int age) {
		Query query = session.createQuery(" from Person4 where age=?");
		query.setInteger(0, age);// ���в�����
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * ����ռλ����ʽ�Ĳ�����
	 * <p>
	 * �����˳��ռλ����JPA���Ƽ�ʹ������ռλ������ʽ���в�ѯ��
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBinding(int age) {
		// :age ��������ռλ��������ʶ��һ����Ϊ"age"�Ĳ�ѯ����
		Query query = session.createQuery(" from Person4 where age=:age");
		query.setParameter("age", age);// ���в�����
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * ����ռλ����ʽ�Ĳ����󶨣�ͬʱʹ�� JavaBean ��װ����
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBindingWithJavaBean(int age) {
		Query query = session.createQuery(" from Person4 where age=:age and name=:name");
		// ��ѯʹ�õ�JavaBean��ֻҪ���� HQL �õ��Ĳ������Լ��ɣ���һ����Ҫ��Person4
		Person4 queryBean = new Person4();
		queryBean.setAge(age);
		queryBean.setName("Tom");
		
		query.setProperties(queryBean);// ���в�����
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * ����ռλ����ʽ�Ĳ����󶨣�ͬʱʹ�� Map ��װ����
	 */
	@SuppressWarnings("unchecked")
	private void queryUseNamedParametersBindingWithMap(int age) {
		Query query = session.createQuery(" from Person4 where age=:age and name=:name");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("age", age);
		queryMap.put("name", "Tom");
		
		query.setProperties(queryMap);// ���в�����
		
		List<Person4> result = query.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
}
