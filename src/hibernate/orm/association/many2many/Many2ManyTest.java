/**
 * 
 */
package hibernate.orm.association.many2many;

import hibernate.util.AbstractHibernateTestCase;

/**
 * many-to-many ����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Many2ManyTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		Group group1 = new Group("Manager");
		Group group2 = new Group("Director");

		Role role1 = new Role("CEO");
		Role role2 = new Role("CTO");

		group1.getRoles().add(role1);
		group1.getRoles().add(role2);

		group2.getRoles().add(role1);
		group2.getRoles().add(role2);

		role1.getGroups().add(group1);
		role1.getGroups().add(group2);

		role2.getGroups().add(group1);
		role2.getGroups().add(group2);

		// ���ڹ�����ϵʱ���ű��໥���ã�����ڱ������״̬ʱ�����˫��ͬʱ����
		session.beginTransaction();
		session.save(group1);
		session.save(group2);
		session.save(role1);
		session.save(role2);
		session.getTransaction().commit();
	}

	@Override
	protected void prepareData() {
	}
}
