/**
 * 
 */
package hibernate.util;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate��ز�����������
 * <p>
 * �ṩ��Ԥ�����ݵĲ������������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public abstract class AbstractHibernateTestCase extends TestCase {

	protected SessionFactory sessionFactory;
	protected Session session;
		
	/**
	 * ���𴴽�Session��׼��Ԥ������
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		prepareData();
	}

	/**
	 * ����ر�Session��SessionFactory
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (session != null && session.isOpen()) {
			session.close();
		}
		if (sessionFactory != null && (!sessionFactory.isClosed())) {
			sessionFactory.close();
		}
	}
	
	/**
	 * �������Է���
	 */
	public final void testHibernate() {
		try {
			doTest();
		} catch (Exception e) {
			e.printStackTrace();
			// ������ع�����
			if (session != null && session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
	
	/**
	 * ׼��Ԥ�����ݣ����罫����������Ҫ�����ݲ������ݿ���
	 */
	protected abstract void prepareData();
	
	/**
	 * ������Է�����������ʵ�֣�session�Ѿ��ǿ���״̬
	 */
	protected abstract void doTest() throws Exception;
}
