/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * ȫ������(full join)��ѯʾ��
 * <p>
 * �������Ӻ��������ӵĲ����������á�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class FullJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("��full join��");
		String hql = "from Employee employee full join employee.cards";

		Query query = session.createQuery(hql);
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
	}
}
