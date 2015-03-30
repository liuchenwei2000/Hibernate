/**
 * 
 */
package hibernate.collection.set;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Set示例
 * <p>
 * 由于JDK自带的Set、Map、List实现不能满足需求，Hibernate基于这些接口提供了自己的实现。
 * 这里所说的Collection均指Hibernate中的实现版本。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class SetTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		// 只会保存一条Part记录，这完全取决于Part类的 equals 实现方式
		Product product = new Product("car");
		product.getParts().add(new Part("tire"));
		product.getParts().add(new Part("tire"));
		session.save(product);
		// 注意这里的Set实例已经被Hibernate替换为其自身实现，而不再是HashSet
		System.out.println("【Set class is】" + product.getParts().getClass());
		session.getTransaction().commit();
	}

	@Override
	protected void prepareData() {
	}
}
