/**
 * 
 */
package hibernate.transaction;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * �ֹ���ʾ��
 * <p>
 * ��Ա��������ԣ��ֹ������Ʋ�ȡ�˸��ӿ��ɵļ������ơ�
 * �����������������������ݿ�������ʵ�֣��Ա�֤�������̶ȵĶ�ռ�ԡ�
 * ����֮�����ľ������ݿ����ܵĴ����������ر��ǶԳ�������ԣ������Ŀ����޷����ܡ�
 * <p>
 * �ֹ�������ǻ������ݰ汾��version����¼����ʵ�֡�
 * ���ݰ汾����Ϊ��������һ���汾��ʶ���ڻ������ݿ��İ汾��������У�һ����ͨ��Ϊ���ݿ������һ����version���ֶ���ʵ�֡�
 * ��ȡ������ʱ�����˰汾��һͬ������������ʱ���Դ˰汾�ż�1����ʱ�������ύ���ݵİ汾�������ݿ���Ӧ��¼�İ汾�����Ƚϣ�
 * ������ύ���ݵİ汾�Ŵ��ڿ��汾�ţ������Ը��£�������Ϊ�ǹ������ݡ�
 * <p>
 * �ֹ������Ʊ����˳������е����ݿ������������������˴󲢷����µ�ϵͳ�������ܡ�
 * ��Ҫע����ǣ��ֹ���������������ϵͳ�е����ݴ洢�߼�����˾߱�һ���ľ����ԡ�
 * �����ֹ����������ڵ�ǰϵͳ��ʵ�֣���������ϵͳ�����ݿ�Ĳ����ǲ��ܵ�ǰϵͳ���Ƶģ���˿��ܻ���ɷǷ����ݱ����µ����ݿ��С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��7��
 */
public class OptimisticLockTest extends AbstractHibernateTestCase  {

	protected void doTest() throws Exception {
		increaseVersionAutomatically();
		optimisticLoking();
	}

	/**
	 * version�ֶ���Hibernate�Զ�ά��
	 */
	private void increaseVersionAutomatically() {
		Session newSession = sessionFactory.openSession();
		
		DVD dvd = (DVD) newSession.load(DVD.class, 1L);
		System.out.println("��init��version=" + dvd.getVersion());
		newSession.beginTransaction();
		dvd.setName("new name1");
		newSession.getTransaction().commit();
		System.out.println("��updated 1��version=" + dvd.getVersion());
		
		newSession.beginTransaction();
		dvd.setName("new name2");
		newSession.getTransaction().commit();
		System.out.println("��updated 2��version=" + dvd.getVersion());
		newSession.close();
	}
	
	/**
	 * �ֹ������ԣ�����session��Ԥ�Ȱ�ͬһ�����ݲ������Ȼ��һ��һ��ĸ������������ύʱ�����
	 */
	private void optimisticLoking() {
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();
			
			DVD dvd1 = (DVD) session1.createCriteria(DVD.class).add(Restrictions.eq("id", 1L)).list().get(0);
			DVD dvd2 = (DVD) session2.createCriteria(DVD.class).add(Restrictions.eq("id", 1L)).list().get(0);
			
			session1.beginTransaction();
			session2.beginTransaction();
			
			dvd1.setName("dvd1");
			session1.getTransaction().commit();
			
			dvd2.setName("dvd2");
			// �ύʱ���׳�StaleObjectStateException��ָ���汾���ʧ�ܣ���ǰ����������ͼ�ύһ���������ݡ�
			session2.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// ������񻹴��ڼ���״̬��ع�
			Transaction tx1 = session1.getTransaction();
			if (tx1 != null && tx1.isActive()) {
				tx1.rollback();
			}
			Transaction tx2 = session2.getTransaction();
			if (tx2 != null && tx2.isActive()) {
				tx2.rollback();
			}
		} finally {
			if (session1 != null) {
				session1.close();
			}
			if (session2 != null) {
				session2.close();
			}
		}
	}

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new DVD("Sing a Song"));
		session.getTransaction().commit();
	}
}
