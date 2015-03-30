/**
 * 
 */
package hibernate.batch;

import hibernate.lifecycle.Entity;
import hibernate.util.AbstractHibernateTestCase;

/**
 * 批量插入示例
 * <p>
 * 在hibernate.cfg.xml中添加对批处理的支持，指定Hibernate每次提交SQL的数量
 * <property name="hibernate.jdbc.batch_size">20</property>
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月17日
 */
public class BatchInsertTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		long begin = System.currentTimeMillis();
		session.beginTransaction();
		for (int i = 0; i < 10000; i++) {
			session.save(new Entity("Entity" + i));
			// 每20条数据作为一个单元，刷新session并清空内部缓存，防止内存溢出
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("【Total time】: " + (end - begin)/1000.0 + "s");
	}
	
	@Override
	protected void prepareData() {
	}
}
