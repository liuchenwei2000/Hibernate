/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.AbstractQueryTestCase;

import org.hibernate.Query;

/**
 * update��delete����ʾ��
 * <p>
 * HQLͨ��update��delete�Ӿ䣬������ʵ�������ݵ�ɾ���͸��²�����
 * <p>
 * HQL�����������ƣ�ͬʱԽ��Խ�ӽ���ͳ��SQL���ԡ�
 * ��SQL��ͬ���ǣ�SQL������Ƕ�ά�Ľṹ�����ݣ���HQL���������ݶ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class UpdateDeleteTest extends AbstractQueryTestCase {

	protected void doTest() {
		session.beginTransaction();
		// update�Ӿ䣺�����������²����������Լ����ܵ�����൱�ɹۡ�
		// SQL��update tb_person4 set age=22
		Query query = session.createQuery("update Person4 set age=22");
		int result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("update " + result + " rows.");

		session.beginTransaction();
		// delete�Ӿ䣺�����������²����������Լ����ܵ�����൱�ɹۡ�
		// SQL��delete from tb_person4 where name='Tom'
		query = session.createQuery("delete Person4 where name='Tom'");
		result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("delete " + result + " rows.");
	}
}
