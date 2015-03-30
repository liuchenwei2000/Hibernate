/**
 * 
 */
package hibernate.dataloading.lazy;

import org.hibernate.Session;

import hibernate.dataloading.Car;
import hibernate.util.AbstractHibernateTestCase;

/**
 * ʵ�������ӳټ���ʾ��
 * <p>
 * ��Hibernate4�У��ӳټ�����Ĭ�����õġ�
 * �����õ���Carʵ�����Ĭ���ӳټ��صģ���Computerʵ�岻���ӳټ��أ������Ӧ��hbm�ļ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��17��
 */
public class EntityLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * ����Car�����������£�
			 * Car class name is : hibernate.dataloading.Car_$$_jvst494_0
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=BMW]
			 * 
			 * hibernate.dataloading.Car_$$_jvst494_0��һ��������󣬶�̬������ơ�
			 * ��ͨ��һ���м����ʵ���������ӳټ��ع��ܣ�ֻ�е���������ʵ�����ȡֵ����ʱ��Hibernate�Ż�ִ�����ݿ��ѯ������
			 */
			Car car = (Car) newSession.load(Car.class, 1L);
			System.out.println("Car class name is : " + car.getClass().getName());
			System.out.println(car);
			
			/*
			 * ����Computer�����������£�
			 * Hibernate: select computer0_.pk_computer as pk_compu1_1_0_, computer0_.name as name2_1_0_ from tb_computers computer0_ where computer0_.pk_computer=?
			 * Computer class name is : hibernate.dataloading.lazy.Computer
			 * Computer [name=Lenovo]
			 * 
			 * �ɼ����ȷ����SQL��ѯ��������ʵ�������û�б�ʹ�ã����ص�ʵ�������������hibernate.dataloading.lazy.Computer
			 */
			Computer computer = (Computer) newSession.load(Computer.class, 1L);
			System.out.println("Computer class name is : " + computer.getClass().getName());
			System.out.println(computer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Computer("Lenovo"));
		session.save(new Car("BMW"));
		session.getTransaction().commit();
	}
}
