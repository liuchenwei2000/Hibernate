/**
 * 
 */
package hibernate.dataloading;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Query Cacheʾ��
 * <p>
 * Query Cache�б�����֮ǰ��ѯ����ִ�й���select SQL���Լ��ɴ˲�ѯ�����Ĳ�ѯ�������������ѯ��������ͺ�id����
 * ֮������ѯ�����ʱ��Hibernate�����ȸ��ݲ�ѯ��SQL��Query Cache�м����������SQL����ִ�й���
 * ��ȡ����Ӧ���SQL�Ľ�������ٸ������������еĶ������ͼ���id���ӻ�����ȡ����Ӧ��ʵ�����
 * <p>
 * ��Ҫ�� hibernate.cfg.xml �ļ������� <property name="hibernate.cache.use_query_cache">true</property>
 * <p>
 * Query Cache ֻ���ض�������²������ã�
 * 1����ȫ��ͬ��select SQL�ظ�ִ�С�
 * 2�������β�ѯ֮�䣬��select SQL��Ӧ�Ŀ��û�з������ı䡣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class QueryCacheTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		String hql = "from Car where id <4";
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
			queryAsList(hql, newSession);
			
			/*
			 * ������ list �����������
			 * ��newSession.createQuery(hql).list()��
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// ��β�ѯ�Ѿ�û��SQL�����˵�����������ȫ�ӻ���ȡ������
			queryAsList(hql, newSession);
			
			/*
			 * Query Cache�л����SQL��������������Զ���ڡ�
			 * ��Hibernate���ִ�SQL��Ӧ�Ŀ�����˱䶯��CUD�����������Զ���Query Cache�ж�Ӧ���SQL����ϳ���
			 */
			/* ɾ��һ����¼ */
			newSession.beginTransaction();
			newSession.createSQLQuery("delete from tb_cars where pk_car=1").executeUpdate();
			newSession.getTransaction().commit();
			
			/*
			 * ������ list �����������
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * ��newSession.createQuery(hql).list()��
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// ��β�ѯ����SQL�����˵��Query Cache�Ѿ���֮ǰ�Ľ�����ϳ�������ִ����SQL��ѯ��
			queryAsList(hql, newSession);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void queryAsList(String hql, Session newSession) {
		Query query = newSession.createQuery(hql);
		query.setCacheable(true);// ��Ҫ�� query��cacheable��Ϊtrue
		List<Car> cars = query.list();
		System.out.println("��newSession.createQuery(hql).list()��");
		for (Car car : cars) {
			System.out.println(car);
		}
	}
}
