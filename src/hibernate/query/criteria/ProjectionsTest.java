/**
 * 
 */
package hibernate.query.criteria;

import hibernate.query.AbstractQueryTestCase;
import hibernate.query.Person4;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

/**
 * Projectionsʾ��
 * <p>
 * Criteria�߼����ԣ����򡢷��顢ͳ�Ƶȵȡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
 */
public class ProjectionsTest extends AbstractQueryTestCase {

	protected void doTest() {
		testOrder(session);
		testGroup(session);
		testStatistics(session);
	}
	
	/**
	 * �Բ�ѯ�Ľ������������
	 */
	@SuppressWarnings("unchecked")
	private static void testOrder(Session session) {
		System.out.println("��order��");
		Criteria criteria = session.createCriteria(Person4.class);
		criteria.addOrder(Order.desc("age"));// ��age��������
		criteria.addOrder(Order.asc("name"));// ��name��������
		
		List<Person4> result = criteria.list();
		for (Person4 person : result) {
			System.out.println(person);
		}
	}
	
	/**
	 * �Բ�ѯ�Ľ�������з���
	 */
	@SuppressWarnings("unchecked")
	private static void testGroup(Session session) {
		System.out.println("��group��");
		Criteria criteria = session.createCriteria(Person4.class);
		// ��age���з���
		criteria.setProjection(Projections.groupProperty("age"));
		// SQL��select this_.age as y0_ from tb_person4 this_ group by this_.age
		List<Integer> result = criteria.list();
		for (Integer age : result) {
			System.out.println(age);
		}
	}
	
	/**
	 * �Բ�ѯ�Ľ��������ͳ��
	 */
	@SuppressWarnings("unchecked")
	private static void testStatistics(Session session) {
		System.out.println("��statistics��");
		
		Criteria criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.rowCount());// ͳ������
		// SQL��select count(*) as y0_ from tb_person4 this_
		System.out.println("row count:" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.avg("age"));// ͳ��ƽ����
		// SQL��select avg(this_.age) as y0_ from tb_person4 this_
		System.out.println("avg(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.max("age"));// ͳ�����ֵ
		// SQL��select max(this_.age) as y0_ from tb_person4 this_
		System.out.println("max(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.min("age"));// ͳ����Сֵ
		// SQL��select min(this_.age) as y0_ from tb_person4 this_
		System.out.println("min(age):" + criteria.list().get(0));
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(Projections.countDistinct("age"));// ͳ��distinctֵ
		// SQL��select count(distinct this_.age) as y0_ from tb_person4 this_
		System.out.println("countDistinct(age):" + criteria.list().get(0));
		
		// ���ڶ�������ϵ�ͳ�ơ����鹦�ܣ�����ʹ��ProjectionList���
		// ͳ�Ƹ�������ε�����
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("age"));
		projectionList.add(Projections.rowCount());
		
		criteria = session.createCriteria(Person4.class);
		criteria.setProjection(projectionList);
		System.out.println("ͳ�Ƹ�������ε�����:");
		// SQL��select this_.age as y0_, count(*) as y1_ from tb_person4 this_ group by this_.age
		List<Object> result = criteria.list();
		for (Object row : result) {
			Object[] data = (Object[]) row;
			System.out.println("age=" + data[0] + ", count=" + data[1]);
		}
	}
}
