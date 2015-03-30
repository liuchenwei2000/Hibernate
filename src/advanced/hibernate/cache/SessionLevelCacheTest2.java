/**
 * 
 */
package hibernate.cache;

import org.hibernate.Session;

/**
 * Session Level Cacheʾ��2
 * <p>
 * ��ʵ֤����һ�����沢�����Զ����»������ݡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
 */
public class SessionLevelCacheTest2 extends SessionLevelCacheTest  {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			System.out.println("��QueryTask��session.load(book) 1st time");
			Book book1 = (Book) newSession.load(Book.class, 1L);
			System.out.println("book1 " + book1);
			
			// ����һ���߳�ȥ���� book1 ��Ӧ��¼�ĸ��²���
			new Thread(new UpdateTask()).start();
			
			Thread.sleep(1000);
			// ��ʱ�� book1 �ĸ��²����Ѿ������
			
			System.out.println("��QueryTask��session.load(book) 2nd time");
			// �ڶ��β�ѯ��ʱ��û��SQL��������ֱ�Ӵӻ����л�ȡ�����ݣ���ʵ�����������Ѿ�������
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
				System.out.println("��UpdateTask��session.load(book) 1st time");
				// �޸�������Ϊ1L��Book����name
				Book book1 = (Book) mySession.load(Book.class, 1L);
				System.out.println("��UpdateTask��book1 " + book1);
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
