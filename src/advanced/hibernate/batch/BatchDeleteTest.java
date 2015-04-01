/**
 * 
 */
package hibernate.batch;

import hibernate.lifecycle.Entity;
import hibernate.util.AbstractHibernateTestCase;

/**
 * 批量删除示例
 * <p>
 * bulk delete/update 操作的原理，即通过一条独立的SQL语句完成数据的批量删除/更新操作。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月17日
 */
public class BatchDeleteTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		long begin = System.currentTimeMillis();
		session.beginTransaction();
		session.createQuery("delete Entity").executeUpdate();
		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("【Total time】: " + (end - begin)/1000.0 + "s");
		
		// bulk delete/update 只是提供了面向高性能批量操作的一种实现途径，但无法保证缓存数据的有效性和一致性。
		// 下面的对象仍然能够查询出来（来自内部缓存），虽然它在数据库中已经被删除了。
		Entity entity = (Entity) session.load(Entity.class, 1L);
		System.out.println(entity);
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		for (int i = 0; i < 1000; i++) {
			session.save(new Entity("Entity" + i));
		}
		session.getTransaction().commit();
	}
}
