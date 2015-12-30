/**
 * 
 */
package hibernate.callback;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Lifecycle 接口使用 示例
 * <p>
 * Lifecycle 接口定义了一种自然的回调机制，但是这种机制要求实体类必须实现Hibernate的接口，
 * Hibernate 原生接口的介入，使得实体类移植性大大降低（实际上此时的实体类已经不再是严格意义上的POJO）
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
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
			System.out.println("【Save failed】 because " + e.getMessage());
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
			System.out.println("【Update failed】 because " + e.getMessage());
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
			System.out.println("【Delete failed】 because " + e.getMessage());
		}
	}

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Photo("photo", 11));
		session.getTransaction().commit();
	}
}
