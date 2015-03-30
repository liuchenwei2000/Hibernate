/**
 * 
 */
package hibernate.dataloading;

import org.hibernate.Session;

/**
 * Session.get/load����������������
 * <p>
 * get/load���������Ը���ָ����ʵ�����ID�����ݿ��ȡ���ݣ���������֮��Ӧ��ʵ�����
 * ��������������ġ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class SessionLoadGetTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		Session session1 = null;
		Session session2 = null;
		try {
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();
			
			// ���δ�ܷ��ַ��������ļ�¼��get��������null
			Car car = (Car) session1.get(Car.class, 100L);
			System.out.println("��session1.get(Car.class, 100L)�� = " + car);
			
			try {
				// ���δ�ܷ��ַ��������ļ�¼��load�����׳�һ��ObjectNotFoundException
				car = (Car) session2.load(Car.class, 100L);
				System.out.println("��session2.load(Car.class, 100L)�� = " + car);
			} catch (Exception e) {
				System.out.println("��session2.load(Car.class, 100L)�� = " + e.getMessage());
				e.printStackTrace();
			}
			
			// load�����ɷ���ʵ��Ĵ�����ʵ������get������Զֱ�ӷ���ʵ���ࡣ
			car = (Car) session1.get(Car.class, 1L);
			System.out.println("��session1.get(Car.class, 1L)�� = " + car.getClass().getName());
			
			car = (Car) session2.load(Car.class, 1L);
			System.out.println("��session2.load(Car.class, 1L)�� = " + car.getClass().getName());
			
			// load�������Գ�������ڲ�����Ͷ��������е��������ݣ�
			// ��get������������ڲ������н��в��ң����û�ҵ���Խ���������棬ֱ�ӵ���SQL������ݶ�ȡ��
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
}
