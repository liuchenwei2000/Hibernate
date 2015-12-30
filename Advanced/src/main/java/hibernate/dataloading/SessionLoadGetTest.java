/**
 * 
 */
package hibernate.dataloading;

import org.hibernate.Session;

/**
 * Session.get/load方法加载数据区别
 * <p>
 * get/load方法均可以根据指定的实体类和ID从数据库读取数据，并返回与之对应的实体对象。
 * 但它们是有区别的。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class SessionLoadGetTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();
			
			// 如果未能发现符合条件的记录，get方法返回null
			Car car = (Car) session1.get(Car.class, 100L);
			System.out.println("【session1.get(Car.class, 100L)】 = " + car);
			
			try {
				// 如果未能发现符合条件的记录，load方法抛出一个ObjectNotFoundException
				car = (Car) session2.load(Car.class, 100L);
				System.out.println("【session2.load(Car.class, 100L)】 = " + car);
			} catch (Exception e) {
				System.out.println("【session2.load(Car.class, 100L)】 = " + e.getMessage());
				e.printStackTrace();
			}
			
			// load方法可返回实体的代理类实例，而get方法永远直接返回实体类。
			car = (Car) session1.get(Car.class, 1L);
			System.out.println("【session1.get(Car.class, 1L)】 = " + car.getClass().getName());
			
			car = (Car) session2.load(Car.class, 1L);
			System.out.println("【session2.load(Car.class, 1L)】 = " + car.getClass().getName());
			
			// load方法可以充分利用内部缓存和二级缓存中的现有数据；
			// 而get方法则仅仅在内部缓存中进行查找，如果没找到则将越过二级缓存，直接调用SQL完成数据读取。
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session1 != null) {
				session1.close();
			}
			if(session2 != null) {
				session2.close();
			}
		}
	}
}
