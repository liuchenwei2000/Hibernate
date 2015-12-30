/**
 * 
 */
package hibernate.transaction;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * 乐观锁示例
 * <p>
 * 相对悲观锁而言，乐观锁机制采取了更加宽松的加锁机制。
 * 悲观锁大多数情况下依靠数据库锁机制实现，以保证操作最大程度的独占性。
 * 但随之而来的就是数据库性能的大量开销，特别是对长事务而言，这样的开销无法忍受。
 * <p>
 * 乐观锁大多是基于数据版本（version）记录机制实现。
 * 数据版本，即为数据增加一个版本标识，在基于数据库表的版本解决方案中，一般是通过为数据库表增加一个“version”字段来实现。
 * 读取出数据时，将此版本号一同读出，随后更新时，对此版本号加1。此时，将待提交数据的版本号与数据库表对应记录的版本号作比较，
 * 如果待提交数据的版本号大于库表版本号，则予以更新，否则认为是过期数据。
 * <p>
 * 乐观锁机制避免了长事务中的数据库加锁开销，大大提升了大并发量下的系统整体性能。
 * 需要注意的是，乐观锁机制往往基于系统中的数据存储逻辑，因此具备一定的局限性。
 * 由于乐观锁机制是在当前系统中实现，而来自外系统对数据库的操作是不受当前系统控制的，因此可能会造成非法数据被更新到数据库中。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月7日
 */
public class OptimisticLockTest extends AbstractHibernateTestCase  {

	protected void doTest() throws Exception {
		increaseVersionAutomatically();
		optimisticLoking();
	}

	/**
	 * version字段由Hibernate自动维护
	 */
	private void increaseVersionAutomatically() {
		Session newSession = sessionFactory.openSession();
		
		DVD dvd = (DVD) newSession.load(DVD.class, 1L);
		System.out.println("【init】version=" + dvd.getVersion());
		newSession.beginTransaction();
		dvd.setName("new name1");
		newSession.getTransaction().commit();
		System.out.println("【updated 1】version=" + dvd.getVersion());
		
		newSession.beginTransaction();
		dvd.setName("new name2");
		newSession.getTransaction().commit();
		System.out.println("【updated 2】version=" + dvd.getVersion());
		newSession.close();
	}
	
	/**
	 * 乐观锁测试，两个session都预先把同一条数据查出来，然后一先一后的更新它，后者提交时会出错
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
			// 提交时将抛出StaleObjectStateException，指出版本检查失败，当前事务正在试图提交一个过期数据。
			session2.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// 如果事务还处于激活状态则回滚
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
