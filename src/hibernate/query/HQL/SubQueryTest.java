/**
 * 
 */
package hibernate.query.HQL;

import hibernate.query.HQL.join.AbstractJoinQueryTestCase;
import hibernate.query.HQL.join.Employee;

import java.util.List;

import org.hibernate.Query;

/**
 * �Ӳ�ѯʾ��
 * <p>
 * �Ӳ�ѯ������SQL����������һ��SQL�Ĳ�ѯ�����HQL֧�ִ˻��ơ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class SubQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// ����н�ǿ��Ĺ�Ա
		// �Ӳ�ѯ���������where�Ӿ��У��ұ�����һ��Բ���Ű�Χ��
		String hql = "from Employee employee where (select count(*) from employee.cards)>0";
		Query query = session.createQuery(hql);

		List<Employee> result = query.list();
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}
}
