/**
 * 
 */
package hibernate.cache;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Session Level Cacheʾ��
 * <p>
 * Hibernate���ݻ����Ϊ������Σ�
 * 1���ڲ����棬Ҳ��һ�����棺Session Level Cache���������񼶻��档
 * Session���ڲ�ά����һ��Map�������ͣ������������б��������е��뵱ǰSession����������ݶ���
 * �����Ҫͨ��Session����ĳ�����ݶ���Session���Ȼ����Ҫ���ص����id����Map�в����Ƿ��ѻ�������ݵ�ʵ����
 * �����������״̬Ϊ��Ч���򷵻ش�ʵ����ͬ���ģ����Session�����ݿ��м��������ݣ�Ҳ�Ὣ������Map�л���������
 * <p>
 * �ڲ����������������Hibernate�Զ�ά����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
 */
public class SessionLevelCacheTest extends AbstractHibernateTestCase  {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			// Ϊ�˱�����ţ�����ʹ��һ���µ�sessionʵ��
			newSession = sessionFactory.openSession();
			System.out.println("��session.load(book) 1st time.��");
			/*
			 * ��һ�β�ѯ��ʱ�򣬻���SQL����������������ݿ��ѯ
			 * 
			 * ���潫����������з������ã�
			 * 1��ͨ��������������ʱ
			 * ��������id��ѯ���ݵ�session.load�������Լ�session.iterate��������ѯ������
			 * 2���ӳټ���
			 */
			Book book1 = (Book) newSession.load(Book.class, 1L);
			System.out.println("book1 " + book1);

			System.out.println("��session.load(book) 2nd time.��");
			// �ڶ��β�ѯ��ʱ�򣬾Ͳ�����SQL����������Ϊֱ�Ӵӻ����л�ȡ�����ݣ���û�е������ݿ��ѯ
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
