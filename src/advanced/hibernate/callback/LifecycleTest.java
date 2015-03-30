/**
 * 
 */
package hibernate.callback;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Lifecycle�ӿ�ʹ�� ʾ��
 * <p>
 * Lifecycle�ӿڶ�����һ����Ȼ�ػص����ƣ��������ֻ���Ҫ��ʵ�������ʵ��Hibernate�Ľӿڣ�
 * Hibernateԭ���ӿڵĽ��룬ʹ��ʵ������ֲ�Դ�󽵵ͣ�ʵ���ϴ�ʱ��ʵ�����Ѿ��������ϸ������ϵ�POJO��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class LifecycleTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		onSave();
		onUpdate();
		onDelete();
	}
	
	private void onSave() {
		try {
			session.beginTransaction();
			session.save(new Photo("photo1", 101));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("��Save failed�� because " + e.getMessage());
		}
	}
	
	private void onUpdate() {
		Session newSession = null;
		try {
			Photo photo = (Photo) session.load(Photo.class, 1L);
			photo.setSize(1000);
			
			newSession = sessionFactory.openSession();
			newSession.beginTransaction();
			newSession.update(photo);
			newSession.getTransaction().commit();
		} catch (Exception e) {
			if (newSession != null && newSession.getTransaction().isActive()) {
				newSession.getTransaction().rollback();
			}
			System.out.println("��Update failed�� because " + e.getMessage());
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}
	
	private void onDelete() {
		try {
			session.clear();
			session.beginTransaction();
			Photo photo = (Photo) session.load(Photo.class, 1L);
			session.delete(photo);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("��Delete failed�� because " + e.getMessage());
		}
	}

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Photo("photo", 11));
		session.getTransaction().commit();
	}
}
