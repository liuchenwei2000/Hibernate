/**
 * 
 */
package hibernate.collection.map;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Map示例
 * <p>
 * Map是一个无序集合类型，提供了键值对应关系。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class MapTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().put("左前轮", new Part("tire1"));
		product.getParts().put("右前轮", new Part("tire2"));
		product.getParts().put("左后轮", new Part("tire3"));
		product.getParts().put("右后轮", new Part("tire4"));
		session.save(product);
		// 注意这里的Map实例已经被Hibernate替换为其自身实现，而不再是HashMap
		System.out.println("【Map class is】" + product.getParts().getClass());
		session.getTransaction().commit();
		
		Session newSession = sessionFactory.openSession();
		Product p = (Product) newSession.load(Product.class, 1L);
		// 这里可以直接根据key值获取对应的对象
		System.out.println(p.getParts().get("左前轮"));
		newSession.close();
	}

	@Override
	protected void prepareData() {
	}
}
