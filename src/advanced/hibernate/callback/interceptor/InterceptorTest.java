/**
 * 
 */
package hibernate.callback.interceptor;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;

/**
 * ������ʹ�� ʾ��
 * <p>
 * Interceptor�����������ӿڶ�����Hibernate�е�ͨ�����ػ��ơ�
 * session����ʱ������ָ��������Ӧ��Interceptor��֮���session�ĳ־û����������������Ⱦ���������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class InterceptorTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			// ʹ��ָ��Interceptor����һ��session����������session��Χ�ģ�
			newSession = sessionFactory.withOptions().interceptor(new LogInterceptor()).openSession();
			// Ҳ����ͨ������ķ�ʽ����һ�� session factory ��Χ�� Interceptor����������ִ������ timestamp ��ǲ���
//			new Configuration().setInterceptor(new LogInterceptor()).configure().buildSessionFactory(serviceRegistry);
			newSession.beginTransaction();
			Account account = (Account) newSession.load(Account.class, 1L);
			account.setAmount(account.getAmount() + 100);
			newSession.getTransaction().commit();
		} catch (Exception e) {
			if (newSession != null && newSession.getTransaction().isActive()) {
				newSession.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Account("ICBC-122987210001", 100));
		session.getTransaction().commit();
	}
}
