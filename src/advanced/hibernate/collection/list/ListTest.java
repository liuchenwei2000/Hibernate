/**
 * 
 */
package hibernate.collection.list;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Listʾ��
 * <p>
 * ��ν����������������Hibernate���ݳ־ù����У��Ƿ񱣳����ݼ����еļ�¼����˳��������ֵġ�
 * ���ڱ�����Ϊ���򼯺ϵ����ݣ�Hibernate�ڳ־û������У��Ὣ������Ԫ�����е��Ⱥ�˳��ͬʱ�̻������ݿ���
 * ����ĳ���ض����ֶδ洢˳��ţ����´ζ�ȡ��ʱ��Ҳ�᷵��һ���߱�ͬ������˳������ݼ��ϡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class ListTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		session.beginTransaction();
		Product product = new Product("car");
		product.getParts().add(new Part("tire1"));
		product.getParts().add(new Part("tire2"));
		product.getParts().add(new Part("tire3"));
		product.getParts().add(new Part("tire4"));
		// �����ʱ��Hibernate�����List��Ԫ�ص�˳���Զ�ά��ָ���������
		session.save(product);
		// ע�������Listʵ���Ѿ���Hibernate�滻Ϊ������ʵ�֣��������� ArrayList
		System.out.println("��List class is��" + product.getParts().getClass());
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
