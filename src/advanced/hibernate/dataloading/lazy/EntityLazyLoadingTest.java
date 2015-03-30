/**
 * 
 */
package hibernate.dataloading.lazy;

import org.hibernate.Session;

import hibernate.dataloading.Car;
import hibernate.util.AbstractHibernateTestCase;

/**
 * 实体对象的延迟加载示例
 * <p>
 * 在Hibernate4中，延迟加载是默认启用的。
 * 本例用到的Car实体就是默认延迟加载的，而Computer实体不是延迟加载，详见对应的hbm文件。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月17日
 */
public class EntityLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * 加载Car对象的输出如下：
			 * Car class name is : hibernate.dataloading.Car_$$_jvst494_0
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=BMW]
			 * 
			 * hibernate.dataloading.Car_$$_jvst494_0是一个代理对象，动态代理机制。
			 * 它通过一个中间代理，实现了数据延迟加载功能，只有当真正调用实体类的取值方法时，Hibernate才会执行数据库查询操作。
			 */
			Car car = (Car) newSession.load(Car.class, 1L);
			System.out.println("Car class name is : " + car.getClass().getName());
			System.out.println(car);
			
			/*
			 * 加载Computer对象的输出如下：
			 * Hibernate: select computer0_.pk_computer as pk_compu1_1_0_, computer0_.name as name2_1_0_ from tb_computers computer0_ where computer0_.pk_computer=?
			 * Computer class name is : hibernate.dataloading.lazy.Computer
			 * Computer [name=Lenovo]
			 * 
			 * 可见是先发起的SQL查询，而不管实体对象有没有被使用，返回的实体对象是真正的hibernate.dataloading.lazy.Computer
			 */
			Computer computer = (Computer) newSession.load(Computer.class, 1L);
			System.out.println("Computer class name is : " + computer.getClass().getName());
			System.out.println(computer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Computer("Lenovo"));
		session.save(new Car("BMW"));
		session.getTransaction().commit();
	}
}
