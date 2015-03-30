/**
 * 
 */
package hibernate.cache;

import org.hibernate.Session;

/**
 * SessionFactory Level Cache示例2
 * <p>
 * 事实证明，二级缓存能自动更新缓存数据。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月6日
 */
public class SessionFactoryLevelCacheTest2 extends SessionFactoryLevelCacheTest  {

	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			System.out.println("【QueryTask】session1.load(worker) 1st time");
			Worker worker = (Worker) session1.load(Worker.class, 1L);
			System.out.println("worker " + worker);
			
			// 另起一个线程去做对 worker 对应记录的更新操作
			new Thread(new UpdateTask()).start();
			
			Thread.sleep(1000);
			// 这时候 worker 的更新操作已经完成了
			
			session2 = sessionFactory.openSession();
			System.out.println("【QueryTask】session2.load(worker) 2nd time");
			// 第二次查询的时候，没有SQL语句输出，直接从缓存中获取了数据，这个数据仍然是最新版本的。
			worker = (Worker) session2.load(Worker.class, 1L);
			System.out.println("worker " + worker);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session1 != null) {
				session1.close();
			}
			if (session2 != null) {
				session2.close();
			}
		}
	}
	
	private class UpdateTask implements Runnable {

		@Override
		public void run() {
			Session mySession = null;
			try {
				mySession = sessionFactory.openSession();
				System.out.println("【UpdateTask】session.load(worker) 1st time");
				// 修改了主键为1L的Worker对象name
				Worker worker = (Worker) mySession.load(Worker.class, 1L);
				System.out.println("【UpdateTask】worker " + worker);
				mySession.beginTransaction();
				worker.setName("Hello World");
				// 提交成功后，二级缓存其实能够得到数据更新通知，从而更新相应的数据
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
