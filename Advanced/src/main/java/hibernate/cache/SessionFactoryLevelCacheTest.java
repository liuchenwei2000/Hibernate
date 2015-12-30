/**
 * 
 */
package hibernate.cache;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;

/**
 * SessionFactory Level Cache示例
 * <p>
 * Hibernate数据缓存分为两个层次：
 * 2，二级缓存：SessionFactory Level Cache，属于应用级缓存。
 * 从属于SessionFactory的所有Session实例会共享二级缓存。
 * Session在进行数据查询操作时，会首先在自身内部的一级缓存中查找，
 * 如果未能命中，则将在二级缓存中查找，如果命中则将缓存数据返回。
 * <p>
 * Hibernate对缓存进行了良好的封装，透明化的缓存机制使得在上层结构的实现中无需面对繁琐的缓存维护细节。
 * Hibernate本身并未提供二级缓存的工业化实现，而是为众多的第三方缓存组件提供了接入接口。
 * 比较常用的第三方缓存包括EHCache、OSCache、JCS、JBoss Cache。
 * <p>
 * Hibernate中启用二级缓存，需要在hibernate.cfg.xml中配置 cache.provider_class 参数。
 * 另外还需要对Cache实现本身进行配置，比如EHCache的配置文件ehcache.xml。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月6日
 */
public class SessionFactoryLevelCacheTest extends AbstractHibernateTestCase  {

	@Override
	protected void prepareData() {
		session.beginTransaction();
		// 在保存的时候，SessionFactory就已经把数据缓存起来了，所以以后不同session实例查询的时候不会发起真正的SQL查询
		session.save(new Worker("Johnny Walker", 20));
		session.getTransaction().commit();
	}
	
	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			// 使用一个新的session实例验证
			session1 = sessionFactory.openSession();
			System.out.println("【session1.load(worker) 1st time.】");
			// 此时不会打印出查询SQL，证明数据是从二级缓存取出来的
			Worker worker1 = (Worker) session1.load(Worker.class, 1L);
			System.out.println("worker1 " + worker1);

			// 使用一个新的session实例验证
			session2 = sessionFactory.openSession();
			System.out.println("【session.load(worker) 2nd time.】");
			// 此时不会打印出查询SQL，证明数据是从二级缓存取出来的
			Worker worker2 = (Worker) session2.load(Worker.class, 1L);
			System.out.println("worker2 " + worker1);
			
			// 虽然都是从二级缓存取出来的数据，但是并不 ==
			System.out.println("worker1 == worker2 ? " + (worker1 == worker2));// false
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session1 != null) {
				session1.close();
			}
			if (session2 != null) {
				session2.close();
			}
		}
	}
}
