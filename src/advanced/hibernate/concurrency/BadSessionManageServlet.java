/**
 * 
 */
package hibernate.concurrency;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

/**
 * 不好的Session管理示例
 * <p>
 * 本例模拟一个Servlet。
 * <p>
 * 为了避免Session的频繁创建和销毁，Servlet中重用了一个Session。
 * 但session并非线程安全，也就是说，如果多个线程同时使用一个Session实例进行数据CRUD操作，将会导致Session操作的逻辑混乱。
 * 当servlet部署到Web服务器之后，很快就会出现错误。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月19日
 */
public class BadSessionManageServlet {

	private Session session;

	public void doGet(String request, String response) {
		doSomething();
		getSession().flush();
	}

	private void doSomething() {
		// 基于Session的CRUD操作
	}

	private Session getSession() {
		if(session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}
}
