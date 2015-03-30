/**
 * 
 */
package hibernate.dataloading;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

/**
 * 查询时Query.list/iterate方法加载数据区别（Criteria也一样）
 * <p>
 * list/iterate方法实现了相同的功能————根据查询条件从数据库获取符合条件的记录，并返回对应的实体集。
 * 除了返回的集合类型不同，它们还有其他的区别。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class QueryListIterateTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		String hql = "from Car where id <4";
		doTest1(hql);
		doTest2(hql);
		doTest3(hql);
		doTest4(hql);
	}

	/**
	 * list/iterate方法各自使用独立的session进行同样的查询
	 */
	@SuppressWarnings("unchecked")
	private void doTest1(String hql) {
		System.out.println("【【【【【list/iterate方法各自使用独立的session进行同样的查询】】】】】");
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【session1.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// list 方法通过一条select SQL实现了查询操作
			List<Car> cars = session1.createQuery(hql).list();
			System.out.println("【session1.createQuery(hql).list()】");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * 以下是 iterate 方法的输出：
			 * Hibernate: select car0_.pk_car as col_0_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【session2.createQuery(hql).iterate()】
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=BMW]
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=Benz]
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=Porsche]
			 */
			// iterate 方法执行了4次select SQL，第一次获取所有符合条件记录的id，之后再根据各个id从数据库中读取相应的记录
			Iterator<Car> iterator = session2.createQuery(hql).iterate();
			System.out.println("【session2.createQuery(hql).iterate()】");
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
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

	/**
	 * list/iterate方法使用同一个session进行同样的查询
	 */
	@SuppressWarnings("unchecked")
	private void doTest2(String hql) {
		System.out.println("【【【【【list/iterate方法使用同一个session进行同样的查询】】】】】");
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// list方法将读取的数据纳入一级缓存，为之后的iterate方法提供了现成的可用数据。
			List<Car> cars = newSession.createQuery(hql).list();
			System.out.println("【newSession.createQuery(hql).list()】");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * 以下是 iterate 方法的输出：
			 * Hibernate: select car0_.pk_car as col_0_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).iterate()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			/*
			 * iterate 方法只执行了1次select SQL，然后从一级缓存中获取相应数据。
			 * 
			 * 这与doTest1()的差异就在于Hibernate缓存机制：
			 * list方法将执行select SQL从数据库中获得所有符合条件的记录并构造相应的实体对象，对象构建完毕之后，就将其纳入缓存。
			 * 之后iterate方法执行时，它首先会执行一条select SQL以获得所有符合查询条件的数据id，
			 * 随即iterate方法会先在缓存中根据id查找对应的实体对象是否存在（类似session.load方法），
			 * 如果缓存中已经存在对应的数据，则直接以此数据对象作为查询结果。
			 * 如果没找到，再执行相应的select 语句以获得对应的库表记录（iterate方法如果执行了数据库读取操作并构建了数据对象，也会将其纳入缓存）。
			 */
			Iterator<Car> iterator = newSession.createQuery(hql).iterate();
			System.out.println("【newSession.createQuery(hql).iterate()】");
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}

	/**
	 * list方法使用同一个session执行两次同样的查询
	 */
	@SuppressWarnings("unchecked")
	private void doTest3(String hql) {
		System.out.println("【【【【【list方法使用同一个session执行两次同样的查询】】】】】");
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			List<Car> cars = newSession.createQuery(hql).list();
			System.out.println("【newSession.createQuery(hql).list()】");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 * 
			 * 两次list方法的重复执行并没有减少SQL的执行数量，缓存机制似乎没有生效。
			 * 
			 * 使用list进行查询时，即使缓存中已经有了一些符合条件的实体对象存在，也无法保证这些数据就是库表中所有符合条件的数据，
			 * 它还是需要执行一次select SQL以保证查询结果的完整性（iterate方法通过首先查询所有符合条件记录的id来保证查询结果的完整性）。
			 * 
			 * list方法实际上无法利用缓存，它对缓存只写不读；而iterate方法则可以充分发挥缓存带来的优势。
			 * 如果目标数据只读或者读取相对较为频繁，通过这种机制可以大大减少性能上的损耗。
			 */
			cars = newSession.createQuery(hql).list();
			System.out.println("【newSession.createQuery(hql).list()】");
			for (Car car : cars) {
				System.out.println(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}
	
	/**
	 * 在内存使用层面比较list和iterate方法
	 * <p>
	 * 假设需要对海量数据进行操作，list方法将一次获得所有的记录并将其读入内存，这将带来极大的内存消耗，很可能触发OutOfMemoryError。
	 * 此时的解决方案之一是结合iterate方法和evict方法逐条对记录进行处理，将内存消耗保持在可以接受的范围内。
	 */
	@SuppressWarnings("unchecked")
	private void doTest4(String hql) {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			Iterator<Car> iterator = newSession.createQuery(hql).iterate();
			while (iterator.hasNext()) {
				Car car = iterator.next();
				// 将对象从一级缓存移除
				newSession.evict(car);
				// 将对象从二级缓存移除
				sessionFactory.getCache().evictEntity(Car.class, car.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}
}
