/**
 * 
 */
package hibernate.cache;

import org.hibernate.Session;

/**
 * SessionFactory Level Cacheʾ��2
 * <p>
 * ��ʵ֤���������������Զ����»������ݡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
 */
public class SessionFactoryLevelCacheTest2 extends SessionFactoryLevelCacheTest  {

	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			System.out.println("��QueryTask��session1.load(worker) 1st time");
			Worker worker = (Worker) session1.load(Worker.class, 1L);
			System.out.println("worker " + worker);
			
			// ����һ���߳�ȥ���� worker ��Ӧ��¼�ĸ��²���
			new Thread(new UpdateTask()).start();
			
			Thread.sleep(1000);
			// ��ʱ�� worker �ĸ��²����Ѿ������
			
			session2 = sessionFactory.openSession();
			System.out.println("��QueryTask��session2.load(worker) 2nd time");
			// �ڶ��β�ѯ��ʱ��û��SQL��������ֱ�Ӵӻ����л�ȡ�����ݣ����������Ȼ�����°汾�ġ�
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
				System.out.println("��UpdateTask��session.load(worker) 1st time");
				// �޸�������Ϊ1L��Worker����name
				Worker worker = (Worker) mySession.load(Worker.class, 1L);
				System.out.println("��UpdateTask��worker " + worker);
				mySession.beginTransaction();
				worker.setName("Hello World");
				// �ύ�ɹ��󣬶���������ʵ�ܹ��õ����ݸ���֪ͨ���Ӷ�������Ӧ������
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
