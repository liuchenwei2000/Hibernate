/**
 * 
 */
package hibernate.concurrency;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

/**
 * 好的Session管理示例
 * <p>
 * 有效的Session管理机制，是Hibernate应用设计的关键。
 * Session管理的目标聚焦于通过合理的设计，避免Session的频繁创建和销毁，
 * 从而避免大量的内存开销和频繁的JVM垃圾回收，保证系统高效平稳运行。
 * <p>
 * 在各种Session管理方案中，ThreadLocal模式得到了大量使用。ThreadLocal是Java中一种较为特殊的线程绑定机制。
 * 通过ThreadLocal存取的数据，总是与当前线程相关，也就是说，JVM为每个运行的线程绑定了私有的本地实例存取空间，
 * 从而为多线程环境常出现的并发访问问题提供了一种隔离机制。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月19日
 */
public class GoodSessionManageServlet {

	// 线程独有
	private ThreadLocal<Session> localSession = new ThreadLocal<Session>();

	public void init() {
		localSession.set(createSession());
	}
	
	public void doGet(String request, String response) {
		doSomething();
		getSession().flush();
	}

	private void doSomething() {
		// 基于Session的CRUD操作
//		getSession().save(object);
	}

	private Session createSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	private Session getSession() {
		return localSession.get();
	}
}
