/**
 * 
 */
package hibernate.cache;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Session;

/**
 * SessionFactory Level Cacheʾ��
 * <p>
 * Hibernate���ݻ����Ϊ������Σ�
 * 2���������棺SessionFactory Level Cache������Ӧ�ü����档
 * ������SessionFactory������Sessionʵ���Ṳ��������档
 * Session�ڽ������ݲ�ѯ����ʱ���������������ڲ���һ�������в��ң�
 * ���δ�����У����ڶ��������в��ң���������򽫻������ݷ��ء�
 * <p>
 * Hibernate�Ի�����������õķ�װ��͸�����Ļ������ʹ�����ϲ�ṹ��ʵ����������Է����Ļ���ά��ϸ�ڡ�
 * Hibernate����δ�ṩ��������Ĺ�ҵ��ʵ�֣�����Ϊ�ڶ�ĵ�������������ṩ�˽���ӿڡ�
 * �Ƚϳ��õĵ������������EHCache��OSCache��JCS��JBoss Cache��
 * <p>
 * Hibernate�����ö������棬��Ҫ��hibernate.cfg.xml������ cache.provider_class ������
 * ���⻹��Ҫ��Cacheʵ�ֱ���������ã�����EHCache�������ļ�ehcache.xml��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
 */
public class SessionFactoryLevelCacheTest extends AbstractHibernateTestCase  {

	@Override
	protected void prepareData() {
		session.beginTransaction();
		// �ڱ����ʱ��SessionFactory���Ѿ������ݻ��������ˣ������Ժ�ͬsessionʵ����ѯ��ʱ�򲻻ᷢ��������SQL��ѯ
		session.save(new Worker("Johnny Walker", 20));
		session.getTransaction().commit();
	}
	
	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			// ʹ��һ���µ�sessionʵ����֤
			session1 = sessionFactory.openSession();
			System.out.println("��session1.load(worker) 1st time.��");
			// ��ʱ�����ӡ����ѯSQL��֤�������ǴӶ�������ȡ������
			Worker worker1 = (Worker) session1.load(Worker.class, 1L);
			System.out.println("worker1 " + worker1);

			// ʹ��һ���µ�sessionʵ����֤
			session2 = sessionFactory.openSession();
			System.out.println("��session.load(worker) 2nd time.��");
			// ��ʱ�����ӡ����ѯSQL��֤�������ǴӶ�������ȡ������
			Worker worker2 = (Worker) session2.load(Worker.class, 1L);
			System.out.println("worker2 " + worker1);
			
			// ��Ȼ���ǴӶ�������ȡ���������ݣ����ǲ��� ==
			System.out.println("worker1 == worker2 ? " + (worker1 == worker2));// false
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
}
