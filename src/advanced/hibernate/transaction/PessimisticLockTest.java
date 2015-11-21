/**
 * 
 */
package hibernate.transaction;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 悲观锁示例
 * <p>
 * 悲观锁，指的是数据被外界（包括当前系统以及其他系统）修改时持保守态度，因此在整个数据处理过程中，将数据处于锁定状态。
 * 悲观锁的实现，往往依靠数据库提供的锁机制（也只有数据库层提供的锁机制才能真正保证数据访问的排他性，
 * 否则即使在本系统中实现了加锁机制，也无法保证外部系统不会修改数据）。
 * <p>
 * 一个典型的依赖数据库实现的悲观锁调用：
 * select * from tb_person where name='tom' for update
 * 
 * 通过 for update 子句，这条SQL锁定了 tb_person 表中所有符合检索条件（name='tom'）的记录。
 * 在本次事务提交之前（事务提交时会释放事务过程中的锁），外界无法修改这些记录。
 * <p>
 * Hibernate的悲观锁，也是基于数据库的锁机制实现。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月7日
 */
public class PessimisticLockTest extends AbstractHibernateTestCase  {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		Session querySession = sessionFactory.openSession();
		
		System.out.println("【QueryTask】Transaction begins");
		querySession.beginTransaction();
		Query query = querySession.createQuery(" from DVD dvd where dvd.id=1");
		/*
		 * Hibernate通过使用数据库的for update子句实现了悲观锁机制，加锁模式有：
		 * 
		 * LockMode.NONE：无锁机制
		 * LockMode.WRITE：Hibernate在Insert和Update记录的时候会自动获取
		 * LockMode.READ：Hibernate在读取记录的时候会自动获取
		 * 
		 * 以上3种锁机制一般由Hibernate内部使用，如为了保证Update过程中对象不会被外界修改，会在save方法实现中自动为目标对象加上WRITE锁，
		 * 这些都是Hibernate内部对数据的锁定机制，与数据库无关。
		 * 
		 * LockMode.PESSIMISTIC_WRITE：利用数据库的for update子句加悲观锁
		 * 加锁一般通过以下方法实现：
		 * Query.setLockMode
		 * Criteria.setLockMode
		 * 
		 * 只有在查询之前（也就是生成SQL之前）进行加锁才会真正通过数据库的锁机制进行加锁处理，
		 * 否则数据已经通过不包含for update子句的Select SQL加载进来，所谓数据库加锁也就失效了。
		 */
		// 对特定别名所对应的记录进行加锁，这里就是对返回的所有DVD记录进行加锁。
		query.setLockMode("dvd", LockMode.PESSIMISTIC_WRITE);
		System.out.println("【QueryTask】Query starts");
		List<DVD> result = query.list();
		System.out.println(result);
		System.out.println("【QueryTask】Query finished");
		
		new Thread(new UpdateTask()).start();
		Thread.sleep(1000);
		// 直到事务提交之后才会释放锁，也就是说UpdateTask中的事务提交永远会在本事务提交之后
		querySession.getTransaction().commit();
		System.out.println("【QueryTask】Transaction commits");
		Thread.sleep(1000);
		querySession.close();
	}
	
	private class UpdateTask implements Runnable {

		@Override
		public void run() {
			Session updateSession = sessionFactory.openSession();
			updateSession.beginTransaction();
			System.out.println("【UpdateTask】Transaction begins");
			// 这里会等待主线程的事务提交后（释放了锁）才能执行 pk_dvd=1 数据的更新操作
			updateSession.createSQLQuery("update tb_dvds set name='New name' where pk_dvd=1").executeUpdate();
			// 如果执行的是下面更新  pk_dvd=2的数据则完全不受主线程加锁的影响，因为它加锁的数据是 pk_dvd=1
//			updateSession.createSQLQuery("update tb_dvds set name='New name' where pk_dvd=1").executeUpdate();
			updateSession.getTransaction().commit();
			System.out.println("【UpdateTask】Transaction commits");
			updateSession.close();
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new DVD("Sing a Song"));
		session.save(new DVD("Number 2"));
		session.getTransaction().commit();
	}
}
