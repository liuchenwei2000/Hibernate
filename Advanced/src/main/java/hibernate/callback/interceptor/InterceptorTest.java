/**
 * 
 */
package hibernate.callback.interceptor;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;

/**
 * 拦截器使用 示例
 * <p>
 * Interceptor（拦截器）接口定义了Hibernate中的通用拦截机制。
 * session创建时即可以指定加载相应的Interceptor，之后此session的持久化操作动作都将首先经由拦截器捕获处理。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class InterceptorTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			// 使用指定Interceptor创建一个session（拦截器是session范围的）
			newSession = sessionFactory.withOptions().interceptor(new LogInterceptor()).openSession();
			// 也可以通过下面的方式设置一个 session factory 范围的 Interceptor，比如用来执行设置 timestamp 标记操作
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
