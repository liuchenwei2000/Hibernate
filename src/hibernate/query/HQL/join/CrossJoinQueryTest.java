/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * ��������(cross join)��ѯʾ��
 * <p>
 * ���ѿ���������ָ������ϵ������Ԫ���������ϡ�
 * һ������£������ѯ��û��ʵ������ġ�
 * <p>
 * �������Ӷ������ϵû��Ҫ�󣬶�����������Ҫ�������������������ϵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class CrossJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("��cross join��");
		String hql = "from Employee, DebitCard";

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
