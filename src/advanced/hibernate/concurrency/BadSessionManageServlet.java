/**
 * 
 */
package hibernate.concurrency;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

/**
 * ���õ�Session����ʾ��
 * <p>
 * ����ģ��һ��Servlet��
 * <p>
 * Ϊ�˱���Session��Ƶ�����������٣�Servlet��������һ��Session��
 * ��session�����̰߳�ȫ��Ҳ����˵���������߳�ͬʱʹ��һ��Sessionʵ����������CRUD���������ᵼ��Session�������߼����ҡ�
 * ��servlet����Web������֮�󣬺ܿ�ͻ���ִ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��19��
 */
public class BadSessionManageServlet {

	private Session session;

	public void doGet(String request, String response) {
		doSomething();
		getSession().flush();
	}

	private void doSomething() {
		// ����Session��CRUD����
	}

	private Session getSession() {
		if(session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}
}
