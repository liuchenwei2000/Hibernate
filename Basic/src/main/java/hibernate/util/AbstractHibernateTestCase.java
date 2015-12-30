/**
 * 
 */
package hibernate.util;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate相关测试用例基类
 * <p>
 * 提供了预置数据的插入和其他基础工作。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public abstract class AbstractHibernateTestCase extends TestCase {

	protected SessionFactory sessionFactory;
	protected Session session;
		
	/**
	 * 负责创建Session，准备预置数据
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		prepareData();
	}

	/**
	 * 负责关闭Session、SessionFactory
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
	 * 用例测试方法
	 */
	public final void testHibernate() {
		try {
			doTest();
		} catch (Exception e) {
			e.printStackTrace();
			// 出错则回滚事务
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
	 * 准备预置数据，比如将测试用例需要的数据插入数据库中
	 */
	protected abstract void prepareData();
	
	/**
	 * 具体测试方法，供子类实现，session已经是可用状态
	 */
	protected abstract void doTest() throws Exception;
}
