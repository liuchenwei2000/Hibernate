/**
 * 
 */
package hibernate.batch;

import hibernate.lifecycle.Entity;
import hibernate.util.AbstractHibernateTestCase;

/**
 * ��������ʾ��
 * <p>
 * ��hibernate.cfg.xml����Ӷ��������֧�֣�ָ��Hibernateÿ���ύSQL������
 * <property name="hibernate.jdbc.batch_size">20</property>
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��17��
 */
public class BatchInsertTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		long begin = System.currentTimeMillis();
		session.beginTransaction();
		for (int i = 0; i < 10000; i++) {
			session.save(new Entity("Entity" + i));
			// ÿ20��������Ϊһ����Ԫ��ˢ��session������ڲ����棬��ֹ�ڴ����
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		long end = System.currentTimeMillis();
		System.out.println("��Total time��: " + (end - begin)/1000.0 + "s");
	}
	
	@Override
	protected void prepareData() {
	}
}
