/**
 * 
 */
package hibernate.collection.map;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Mapʾ��
 * <p>
 * Map��һ�����򼯺����ͣ��ṩ�˼�ֵ��Ӧ��ϵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class MapTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().put("��ǰ��", new Part("tire1"));
		product.getParts().put("��ǰ��", new Part("tire2"));
		product.getParts().put("�����", new Part("tire3"));
		product.getParts().put("�Һ���", new Part("tire4"));
		session.save(product);
		// ע�������Mapʵ���Ѿ���Hibernate�滻Ϊ������ʵ�֣���������HashMap
		System.out.println("��Map class is��" + product.getParts().getClass());
		session.getTransaction().commit();
		
		Session newSession = sessionFactory.openSession();
		Product p = (Product) newSession.load(Product.class, 1L);
		// �������ֱ�Ӹ���keyֵ��ȡ��Ӧ�Ķ���
		System.out.println(p.getParts().get("��ǰ��"));
		newSession.close();
	}

	@Override
	protected void prepareData() {
	}
}
