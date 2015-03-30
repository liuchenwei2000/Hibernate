/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.List;

import org.hibernate.Query;

/**
 * ��������(right outer join)��ѯʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class RightOuterJoinQueryTest extends AbstractJoinQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		System.out.println("��right outer join��");
		String hql = "from Employee employee right outer join employee.cards";

		Query query = session.createQuery(hql);
		List<Object[]> result1 = query.list();
		for (Object[] objects : result1) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
		/*
		 * fetch �ؼ�ֻ�� inner join �� left outer join ��Ч��
		 * ���� right outer join ���ԣ�������Ϊ��������������Employee�������Ϊnull��
		 * ����Ҳ���޷�ͨ��fetch�ؼ���ǿ��Hibernate���м����������ˡ�
		 */
	}
}
