/**
 * 
 */
package hibernate.dataloading;

import hibernate.util.AbstractHibernateTestCase;

/**
 * ���ݼ��ز�����������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public abstract class AbstractDataLoadingTestCase extends AbstractHibernateTestCase {

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Car("BMW"));
		session.save(new Car("Benz"));
		session.save(new Car("Porsche"));
		session.save(new Car("Ferrari"));
		session.getTransaction().commit();
	}
}
