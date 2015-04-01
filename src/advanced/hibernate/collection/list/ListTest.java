/**
 * 
 */
package hibernate.collection.list;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * List示例
 * <p>
 * 所谓的有序和无序，是针对Hibernate数据持久过程中，是否保持数据集合中的记录排列顺序加以区分的。
 * 对于被定义为有序集合的数据，Hibernate在持久化过程中，会将集合中元素排列的先后顺序同时固化到数据库中
 * （以某个特定的字段存储顺序号），下次读取的时候，也会返回一个具备同样排列顺序的数据集合。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class ListTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().add(new Part("tire1"));
		product.getParts().add(new Part("tire2"));
		product.getParts().add(new Part("tire3"));
		product.getParts().add(new Part("tire4"));
		// 保存的时候，Hibernate会根据List内元素的顺序自动维护指定的序号列
		session.save(product);
		// 注意这里的List实例已经被Hibernate替换为其自身实现，而不再是 ArrayList
		System.out.println("【List class is】" + product.getParts().getClass());
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
