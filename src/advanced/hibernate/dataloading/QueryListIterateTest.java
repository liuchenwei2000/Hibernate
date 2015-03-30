/**
 * 
 */
package hibernate.dataloading;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

/**
 * ��ѯʱQuery.list/iterate����������������CriteriaҲһ����
 * <p>
 * list/iterate����ʵ������ͬ�Ĺ��ܡ����������ݲ�ѯ���������ݿ��ȡ���������ļ�¼�������ض�Ӧ��ʵ�弯��
 * ���˷��صļ������Ͳ�ͬ�����ǻ�������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class QueryListIterateTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		String hql = "from Car where id <4";
		doTest1(hql);
		doTest2(hql);
		doTest3(hql);
		doTest4(hql);
	}

	/**
	 * list/iterate��������ʹ�ö�����session����ͬ���Ĳ�ѯ
	 */
	@SuppressWarnings("unchecked")
	private void doTest1(String hql) {
		System.out.println("����������list/iterate��������ʹ�ö�����session����ͬ���Ĳ�ѯ����������");
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();
			
			/*
			 * ������ list �����������
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��session1.createQuery(hql).list()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// list ����ͨ��һ��select SQLʵ���˲�ѯ����
			List<Car> cars = session1.createQuery(hql).list();
			System.out.println("��session1.createQuery(hql).list()��");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * ������ iterate �����������
			 * Hibernate: select car0_.pk_car as col_0_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��session2.createQuery(hql).iterate()��
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=BMW]
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=Benz]
			 * Hibernate: select car0_.pk_car as pk_car1_0_0_, car0_.name as name2_0_0_ from tb_cars car0_ where car0_.pk_car=?
			 * Car [name=Porsche]
			 */
			// iterate ����ִ����4��select SQL����һ�λ�ȡ���з���������¼��id��֮���ٸ��ݸ���id�����ݿ��ж�ȡ��Ӧ�ļ�¼
			Iterator<Car> iterator = session2.createQuery(hql).iterate();
			System.out.println("��session2.createQuery(hql).iterate()��");
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session1 != null) {
				session1.close();
			}
			if(session2 != null) {
				session2.close();
			}
		}
	}

	/**
	 * list/iterate����ʹ��ͬһ��session����ͬ���Ĳ�ѯ
	 */
	@SuppressWarnings("unchecked")
	private void doTest2(String hql) {
		System.out.println("����������list/iterate����ʹ��ͬһ��session����ͬ���Ĳ�ѯ����������");
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			/*
			 * ������ list �����������
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��newSession.createQuery(hql).list()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// list��������ȡ����������һ�����棬Ϊ֮���iterate�����ṩ���ֳɵĿ������ݡ�
			List<Car> cars = newSession.createQuery(hql).list();
			System.out.println("��newSession.createQuery(hql).list()��");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * ������ iterate �����������
			 * Hibernate: select car0_.pk_car as col_0_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��newSession.createQuery(hql).iterate()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			/*
			 * iterate ����ִֻ����1��select SQL��Ȼ���һ�������л�ȡ��Ӧ���ݡ�
			 * 
			 * ����doTest1()�Ĳ��������Hibernate������ƣ�
			 * list������ִ��select SQL�����ݿ��л�����з��������ļ�¼��������Ӧ��ʵ����󣬶��󹹽����֮�󣬾ͽ������뻺�档
			 * ֮��iterate����ִ��ʱ�������Ȼ�ִ��һ��select SQL�Ի�����з��ϲ�ѯ����������id��
			 * �漴iterate���������ڻ����и���id���Ҷ�Ӧ��ʵ������Ƿ���ڣ�����session.load��������
			 * ����������Ѿ����ڶ�Ӧ�����ݣ���ֱ���Դ����ݶ�����Ϊ��ѯ�����
			 * ���û�ҵ�����ִ����Ӧ��select ����Ի�ö�Ӧ�Ŀ���¼��iterate�������ִ�������ݿ��ȡ���������������ݶ���Ҳ�Ὣ�����뻺�棩��
			 */
			Iterator<Car> iterator = newSession.createQuery(hql).iterate();
			System.out.println("��newSession.createQuery(hql).iterate()��");
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}

	/**
	 * list����ʹ��ͬһ��sessionִ������ͬ���Ĳ�ѯ
	 */
	@SuppressWarnings("unchecked")
	private void doTest3(String hql) {
		System.out.println("����������list����ʹ��ͬһ��sessionִ������ͬ���Ĳ�ѯ����������");
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			/*
			 * ������ list �����������
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��newSession.createQuery(hql).list()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			List<Car> cars = newSession.createQuery(hql).list();
			System.out.println("��newSession.createQuery(hql).list()��");
			for (Car car : cars) {
				System.out.println(car);
			}
			
			/*
			 * ������ list �����������
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��newSession.createQuery(hql).list()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 * 
			 * ����list�������ظ�ִ�в�û�м���SQL��ִ����������������ƺ�û����Ч��
			 * 
			 * ʹ��list���в�ѯʱ����ʹ�������Ѿ�����һЩ����������ʵ�������ڣ�Ҳ�޷���֤��Щ���ݾ��ǿ�������з������������ݣ�
			 * ��������Ҫִ��һ��select SQL�Ա�֤��ѯ����������ԣ�iterate����ͨ�����Ȳ�ѯ���з���������¼��id����֤��ѯ����������ԣ���
			 * 
			 * list����ʵ�����޷����û��棬���Ի���ֻд��������iterate��������Գ�ַ��ӻ�����������ơ�
			 * ���Ŀ������ֻ�����߶�ȡ��Խ�ΪƵ����ͨ�����ֻ��ƿ��Դ����������ϵ���ġ�
			 */
			cars = newSession.createQuery(hql).list();
			System.out.println("��newSession.createQuery(hql).list()��");
			for (Car car : cars) {
				System.out.println(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}
	
	/**
	 * ���ڴ�ʹ�ò���Ƚ�list��iterate����
	 * <p>
	 * ������Ҫ�Ժ������ݽ��в�����list������һ�λ�����еļ�¼����������ڴ棬�⽫����������ڴ����ģ��ܿ��ܴ���OutOfMemoryError��
	 * ��ʱ�Ľ������֮һ�ǽ��iterate������evict���������Լ�¼���д������ڴ����ı����ڿ��Խ��ܵķ�Χ�ڡ�
	 */
	@SuppressWarnings("unchecked")
	private void doTest4(String hql) {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			Iterator<Car> iterator = newSession.createQuery(hql).iterate();
			while (iterator.hasNext()) {
				Car car = iterator.next();
				// �������һ�������Ƴ�
				newSession.evict(car);
				// ������Ӷ��������Ƴ�
				sessionFactory.getCache().evictEntity(Car.class, car.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}
}
