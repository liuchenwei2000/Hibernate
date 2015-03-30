/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

/**
 * ������(inner join)��ѯʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class InnerJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("��inner join��");
		// �������Ҫ�� �������֮����û�� on �Ӿ�����Ϊӳ���ϵ�Ѿ���hbm�ļ���ָ���ˡ�
		String hql = "from Employee employee inner join employee.cards";

		Query query = session.createQuery(hql);
		// �õ��Ľ������ÿ����Ŀ����һ��Object����
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			// objects[0] �� Employee��objects[1] �� DebitCard
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}

		// fetch �ؼ��ֱ��� DebitCard ���������������䵽��Ӧ��Employee������
		hql = "from Employee employee inner join fetch employee.cards";

		query = session.createQuery(hql);
		List<Employee> result2 = query.list();
		for (Employee employee : result2) {
			System.out.print(employee + "  ");
			Set<DebitCard> cards = employee.getCards();
			for (DebitCard debitCard : cards) {
				System.out.print(debitCard + "  ");
			}
			System.out.println();
		}
	}
}
