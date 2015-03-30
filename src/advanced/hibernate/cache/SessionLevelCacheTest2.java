/**
 * 
 */
package hibernate.cache;

import org.hibernate.Session;

/**
 * Session Level Cache示例2
 * <p>
 * 事实证明，一级缓存并不能自动更新缓存数据。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月6日
 */
public class SessionLevelCacheTest2 extends SessionLevelCacheTest  {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			System.out.println("【QueryTask】session.load(book) 1st time");
			Book book1 = (Book) newSession.load(Book.class, 1L);
			System.out.println("book1 " + book1);
			
			// 另起一个线程去做对 book1 对应记录的更新操作
			new Thread(new UpdateTask()).start();
			
			Thread.sleep(1000);
			// 这时候 book1 的更新操作已经完成了
			
			System.out.println("【QueryTask】session.load(book) 2nd time");
			// 第二次查询的时候，没有SQL语句输出，直接从缓存中获取了数据，但实际上这数据已经过期了
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
	
	private class UpdateTask implements Runnable {

		@Override
		public void run() {
			Session mySession = null;
			try {
				mySession = sessionFactory.openSession();
				System.out.println("【UpdateTask】session.load(book) 1st time");
				// 修改了主键为1L的Book对象name
				Book book1 = (Book) mySession.load(Book.class, 1L);
				System.out.println("【UpdateTask】book1 " + book1);
				mySession.beginTransaction();
				book1.setName("Hello World");
				mySession.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mySession != null) {
					mySession.close();
				}
			}
		}
	}
}
