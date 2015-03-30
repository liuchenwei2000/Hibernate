/**
 * 
 */
package hibernate.template;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

/**
 * Hibernate操作模板
 * <p>
 * 将CRUD操作使用的常见代码进行封装，形成代码模板，避免了繁杂的重复代码。
 * <p>
 * 比如事务性操作都是如下步骤：
 * 1，获取session
 * 2，开启事务
 * 3，执行具体操作
 * 4，提交事务/遇到异常回滚事务，抛出异常
 * 5，关闭session
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月20日
 */
public final class HibernateTemplate<T> {
	
	/**
	 * 在事务中执行操作
	 */
	public void runInTransaction(HibernateCallback callback) throws CRUDException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			callback.execute(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null && session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new CRUDException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 使用指定session，在事务中执行操作，操作结束后session不会close
	 */
	public void runInTransaction(Session session, HibernateCallback callback) throws CRUDException {
		try {
			session.beginTransaction();
			callback.execute(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null && session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new CRUDException(e);
		}
	}
}
