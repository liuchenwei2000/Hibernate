/**
 * 
 */
package hibernate.collection.bag;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Bag示例
 * <p>
 * Bag类型是Hibernate自定义的集合类型，实现了一个允许包含重复元素的Set。
 * 它的底层是借助一个List实现，但屏蔽了List的有序特性，也就是说，Bag中元素的排列顺序将不会被持久化。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class BagTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().add(new Part("tire1"));
		product.getParts().add(new Part("tire2"));
		product.getParts().add(new Part("tire3"));
		product.getParts().add(new Part("tire4"));
		session.save(product);
		session.getTransaction().commit();
		
		Session newSession = sessionFactory.openSession();
		// 查询回来依然是有序集
		Product p = (Product) newSession.load(Product.class, 1L);
		System.out.println(p);
		newSession.close();
	}

	@Override
	protected void prepareData() {
	}
}
