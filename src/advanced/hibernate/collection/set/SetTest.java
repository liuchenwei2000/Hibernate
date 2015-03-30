/**
 * 
 */
package hibernate.collection.set;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Setʾ��
 * <p>
 * ����JDK�Դ���Set��Map��Listʵ�ֲ�����������Hibernate������Щ�ӿ��ṩ���Լ���ʵ�֡�
 * ������˵��Collection��ָHibernate�е�ʵ�ְ汾��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class SetTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		// ֻ�ᱣ��һ��Part��¼������ȫȡ����Part��� equals ʵ�ַ�ʽ
		Product product = new Product("car");
		product.getParts().add(new Part("tire"));
		product.getParts().add(new Part("tire"));
		session.save(product);
		// ע�������Setʵ���Ѿ���Hibernate�滻Ϊ������ʵ�֣���������HashSet
		System.out.println("��Set class is��" + product.getParts().getClass());
		session.getTransaction().commit();
	}

	@Override
	protected void prepareData() {
	}
}
