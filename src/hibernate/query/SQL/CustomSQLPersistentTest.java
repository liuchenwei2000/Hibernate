/**
 * 
 */
package hibernate.query.SQL;

import hibernate.util.AbstractHibernateTestCase;

/**
 * �Զ���SQL�־û�ʾ��
 * <p>
 * ���Զ�ʵ���insert��update��delete�������ж��壬��������ȫ����Hibernate�ṩ���Զ��������ӿڡ�
 * �����ļ����������� Dog.hbm.xml��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class CustomSQLPersistentTest extends AbstractHibernateTestCase {

	protected void doTest() {
		insert();
		update();
		delete();
	}
	
	private void insert() {
		System.out.println("��Custom Insert��");
		session.beginTransaction();
		session.save(new Dog("Lulu", 4));
		session.save(new Dog("Kaf", 2, 1L));
		session.getTransaction().commit();
	}
	
	private void update() {
		System.out.println("��Custom Update��");
		session.beginTransaction();
		Dog dog = (Dog) session.load(Dog.class, 2L);
		dog.setName("new name");
		session.update(dog);
		session.getTransaction().commit();
	}
	
	private void delete() {
		System.out.println("��Custom Delete��");
		session.beginTransaction();
		Dog dog = (Dog) session.get(Dog.class, 1L);
		session.delete(dog);
		session.getTransaction().commit();
	}

	protected void prepareData() {
	}
}
