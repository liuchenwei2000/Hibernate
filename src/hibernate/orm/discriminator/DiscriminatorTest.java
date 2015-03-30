/**
 * 
 */
package hibernate.orm.discriminator;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

/**
 * ����������
 * <p>
 * ����������Ʒ���鼮��DVD����������Ʒ��һЩ�������ԣ�Ҳ�и��Ե��������ԡ�
 * <p>
 * ����������Ŀ����������¼��֣�
 * 1��ÿ����Ʒ��Ӧһ�������ı�
 * ȱ�㣺һ�����������б仯�����ӻ����޸ģ�����ÿ�ű���Ҫ�޸ġ�
 * 2��ÿ����Ʒ��Ӧһ��ֻ�����������Ե��ӱ��������Էŵ�ͬһ���������ӱ�ͨ���������
 * ȱ�㣺ÿ�β�ѯ����Ҫ���ű������������ܱȽϵ��¡�
 * 3��������Ʒ��Ӧһ�ű����мȰ�����������Ҳ�����������ԣ�ͨ��ĳһ������ֶ����ֲ�ͬ����Ʒ��
 * �������ǲ����������ķ���������Ӧ�Ķ���ģ���Բ��ü̳���ϵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class DiscriminatorTest extends AbstractHibernateTestCase {

	@SuppressWarnings("unchecked")
	@Override
	protected void doTest() {
		session.beginTransaction();
		session.save(createBook());
		session.save(createDVD());
		session.getTransaction().commit();

		// ������Hibernate�ڶ�ȡItem��Ӧ�������ʱ��������ƶ��ı���ʶ�����жϣ��Զ�ӳ�䵽��Ӧ���ࡣ
		List<Book> books = session.createQuery("from Book").list();
		for (Book book : books) {
			System.out.println("Book name is ��" + book.getName()
					+ "��, page count is " + book.getPageCount());
		}
		
		List<DVD> dvds = session.createQuery("from DVD").list();
		for (DVD dvd : dvds) {
			System.out.println("DVD name is ��" + dvd.getName()
					+ "��, region code is " + dvd.getRegionCode());
		}
	}
	
	private static Book createBook(){
		Book book = new Book("Hibernate in Action");
		book.setPageCount(878);
		return book;
	}
	
	private static DVD createDVD(){
		DVD dvd = new DVD("William Tell");
		dvd.setRegionCode("100-090-22983");
		return dvd;
	}

	@Override
	protected void prepareData() {
	}
}
