/**
 * 
 */
package hibernate.concurrency;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

/**
 * �õ�Session����ʾ��
 * <p>
 * ��Ч��Session������ƣ���HibernateӦ����ƵĹؼ���
 * Session�����Ŀ��۽���ͨ���������ƣ�����Session��Ƶ�����������٣�
 * �Ӷ�����������ڴ濪����Ƶ����JVM�������գ���֤ϵͳ��Чƽ�����С�
 * <p>
 * �ڸ���Session�������У�ThreadLocalģʽ�õ��˴���ʹ�á�ThreadLocal��Java��һ�ֽ�Ϊ������̰߳󶨻��ơ�
 * ͨ��ThreadLocal��ȡ�����ݣ������뵱ǰ�߳���أ�Ҳ����˵��JVMΪÿ�����е��̰߳���˽�еı���ʵ����ȡ�ռ䣬
 * �Ӷ�Ϊ���̻߳��������ֵĲ������������ṩ��һ�ָ�����ơ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��19��
 */
public class GoodSessionManageServlet {

	// �̶߳���
	private ThreadLocal<Session> localSession = new ThreadLocal<Session>();

	public void init() {
		localSession.set(createSession());
	}
	
	public void doGet(String request, String response) {
		doSomething();
		getSession().flush();
	}

	private void doSomething() {
		// ����Session��CRUD����
//		getSession().save(object);
	}

	private Session createSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	private Session getSession() {
		return localSession.get();
	}
}
