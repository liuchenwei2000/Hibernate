/**
 * 
 */
package hibernate.cache;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Session Level Cache示例
 * <p>
 * Hibernate数据缓存分为两个层次：
 * 1，内部缓存，也叫一级缓存：Session Level Cache，属于事务级缓存。
 * Session在内部维护了一个Map数据类型，此数据类型中保持了所有的与当前Session相关联的数据对象。
 * 如果需要通过Session加载某个数据对象，Session首先会根据要加载的类和id，在Map中查找是否已缓存此数据的实例，
 * 如果存在且其状态为有效，则返回此实例。同样的，如果Session从数据库中加载了数据，也会将其纳入Map中缓存起来。
 * <p>
 * 内部缓存正常情况下由Hibernate自动维护。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月6日
 */
public class SessionLevelCacheTest extends AbstractHibernateTestCase  {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			// 为了避免干扰，这里使用一个新的session实例
			newSession = sessionFactory.openSession();
			System.out.println("【session.load(book) 1st time.】");
			/*
			 * 第一次查询的时候，会有SQL语句输出，会调用数据库查询
			 * 
			 * 缓存将在以下情况中发挥作用：
			 * 1，通过主键加载数据时
			 * 包括根据id查询数据的session.load方法，以及session.iterate等批量查询方法。
			 * 2，延迟加载
			 */
			Book book1 = (Book) newSession.load(Book.class, 1L);
			System.out.println("book1 " + book1);

			System.out.println("【session.load(book) 2nd time.】");
			// 第二次查询的时候，就不会有SQL语句输出，因为直接从缓存中获取了数据，并没有调用数据库查询
			Book book2 = (Book) newSession.load(Book.class, 1L);
			System.out.println("book2 " + book1);

			System.out.println("book1 == book2 ? " + (book1 == book2));// true
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
		List<Book> books = createBooks();
		for (Book book : books) {
			session.save(book);
		}
		session.getTransaction().commit();
	}

	private List<Book> createBooks() {
		Book book1 = new Book("Hibernate in Action",
				"Christian Bauer / Gavin King", "9781932394153");
		Book book2 = new Book("Thinking in Java", "Bruce Eckel ",
				"9780131872486");
		return Arrays.asList(book1, book2);
	}
}
