/**
 * 
 */
package hibernate.batch;

import hibernate.lifecycle.Entity;
import hibernate.util.AbstractHibernateTestCase;

/**
 * ����ɾ��ʾ��
 * <p>
 * bulk delete/update ������ԭ����ͨ��һ��������SQL���������ݵ�����ɾ��/���²�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��17��
 */
public class BatchDeleteTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		long begin = System.currentTimeMillis();
		session.beginTransaction();
		session.createQuery("delete Entity").executeUpdate();
		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("��Total time��: " + (end - begin)/1000.0 + "s");
		
		// bulk delete/update ֻ���ṩ���������������������һ��ʵ��;�������޷���֤�������ݵ���Ч�Ժ�һ���ԡ�
		// ����Ķ�����Ȼ�ܹ���ѯ�����������ڲ����棩����Ȼ�������ݿ����Ѿ���ɾ���ˡ�
		Entity entity = (Entity) session.load(Entity.class, 1L);
		System.out.println(entity);
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		for (int i = 0; i < 1000; i++) {
			session.save(new Entity("Entity" + i));
		}
		session.getTransaction().commit();
	}
}
