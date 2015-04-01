/**
 * 
 */
package hibernate.dataloading.lazy;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * ���Ե��ӳټ���ʾ��
 * <p>
 * �����ʵ���ĳ�����ԣ�����CLOB/BLOB����Ƚϴ�����ԣ�Ӧ���ӳټ��ز��ԣ����Բ������ַ�����
 * <li>1����hbm�ļ��У�����ص�property��lazy������Ϊtrue��Ȼ��ʹ���ֽ��빤�̽�ʵ������б��룬ֲ����Ҫ���ֽ��루��Ҫ�Ǵ��������ӳټ��صģ���
 * <li>2������ķ�ʽ�ȽϷѾ���Hibernate�Ƽ�ʹ��Critertia��Projection��ʵ�����Ե��ӳټ��ء�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��17��
 */
public class PropertyLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * ͨ��ָ����ѯ�����ԴӶ���Ӵﵽ�ӳټ������Ե�Ч��
			 */
			List<?> result = newSession
					.createCriteria(Computer.class)
					.setProjection(
							// ����������Ҫȡ��Щ���Ժ���Щ���Եı���
							Projections.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("name"), "name"))
					.add(Restrictions.eq("id", 1L))// �����ǲ�ѯ����
					.setResultTransformer(// �������ý�������ֵת����Bean��ת����
							Transformers.aliasToBean(Computer.class)).list();
			System.out.println(result.get(0));
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
		Computer computer = new Computer("Lenovo");
		computer.setBigdata("this is a big data");
		session.save(computer);
		session.getTransaction().commit();
	}
}
