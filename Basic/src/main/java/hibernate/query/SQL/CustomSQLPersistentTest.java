/**
 * 
 */
package hibernate.query.SQL;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 自定义SQL持久化示例
 * <p>
 * 可以对实体的insert、update、delete操作进行定义，而无需完全依赖Hibernate提供的自动化操作接口。
 * 配置文件里的设置详见 Dog.hbm.xml。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class CustomSQLPersistentTest extends AbstractHibernateTestCase {

	protected void doTest() {
		insert();
		update();
		delete();
	}
	
	private void insert() {
		System.out.println("【Custom Insert】");
		session.beginTransaction();
		session.save(new Dog("Lulu", 4));
		session.save(new Dog("Kaf", 2, 1L));
		session.getTransaction().commit();
	}
	
	private void update() {
		System.out.println("【Custom Update】");
		session.beginTransaction();
		Dog dog = (Dog) session.load(Dog.class, 2L);
		dog.setName("new name");
		session.update(dog);
		session.getTransaction().commit();
	}
	
	private void delete() {
		System.out.println("【Custom Delete】");
		session.beginTransaction();
		Dog dog = (Dog) session.get(Dog.class, 1L);
		session.delete(dog);
		session.getTransaction().commit();
	}

	protected void prepareData() {
	}
}
