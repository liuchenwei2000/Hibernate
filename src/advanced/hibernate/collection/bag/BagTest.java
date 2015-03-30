/**
 * 
 */
package hibernate.collection.bag;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Bagʾ��
 * <p>
 * Bag������Hibernate�Զ���ļ������ͣ�ʵ����һ����������ظ�Ԫ�ص�Set��
 * ���ĵײ��ǽ���һ��Listʵ�֣���������List���������ԣ�Ҳ����˵��Bag��Ԫ�ص�����˳�򽫲��ᱻ�־û���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class BagTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().add(new Part("tire1"));
		product.getParts().add(new Part("tire2"));
		product.getParts().add(new Part("tire3"));
		product.getParts().add(new Part("tire4"));
		session.save(product);
		session.getTransaction().commit();
		
		Session newSession = sessionFactory.openSession();
		// ��ѯ������Ȼ������
		Product p = (Product) newSession.load(Product.class, 1L);
		System.out.println(p);
		newSession.close();
	}

	@Override
	protected void prepareData() {
	}
}
