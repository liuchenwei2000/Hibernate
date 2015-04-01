/**
 * 
 */
package hibernate.pagination;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * ��ѯ�����ҳʾ��
 * <p>
 * Hibernate�У�ͨ���Բ�ͬ���ݿ��ͳһ�ӿ���ƣ�ʵ����͸������ͨ�û��ķ�ҳʵ�ֻ��ơ�
 * <p>
 * ������ org.hibernate.dialect.Dialect ָ�������еײ����ݿ�Ķ���ͳһ�ӿڣ�ͨ����Բ�ͬ���ݿ�
 * �ṩ��Ӧ��dialectʵ�֣����ݿ�֮��Ĳ����Ե����������Ӷ�Ϊ�ϲ�����ṩ��͸���ġ����ݿ��޹صĴ洢�������
 * ��hibernate.cfg.cml����Ҫ���� <property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��19��
 */
public class PaginationTest extends AbstractHibernateTestCase {

	/**
	 * �����е����ݽ��з�ҳ��ÿҳ20��
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doTest() throws Exception {
		int numberPerPage = 20;// ÿҳ��������
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			// Criteria �� Query���󶼿�����ɷ�ҳ�Ĺ��ܣ�ʹ�÷�ʽ��һ����
			
			// ͨ�� setMaxResults ��  setFirstResult ���ʹ�ôﵽ��ҳ��ȡ���ݵ�Ŀ��
			
			Criteria criteria = newSession.createCriteria(Record.class);
//			Query query = newSession.createQuery(" from Record");
			// ���ò�ѯ��������Ŀ��������Ϊÿ�λ�ȡ20������һҳ
			criteria.setMaxResults(numberPerPage);

			int counter = 0;
			while (true) {
				// ���ôӽ�����ĵ� firstResult ����ʼȡ������0��ʼ��
				criteria.setFirstResult((counter++) * numberPerPage);
				// MySQL�µ�SQL�磺select * from tb_records limit 21, 20
				List<Record> result = criteria.list();
				if (!result.isEmpty()) {
					System.out.println("�� " + counter + "����");
					for (Record record : result) {
						System.out.println(record);
					}
				}
				if (result.size() < numberPerPage) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		for (int i = 0; i < 100; i++) {
			session.save(new Record("record" + i));
		}
		session.getTransaction().commit();
	}
}
