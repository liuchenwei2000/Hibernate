/**
 * 
 */
package hibernate.orm.association.one2one.fk;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Ψһ�������ʾ��
 * <p>
 * �û����а���һ��pk_group�ֶΣ����ֶ���tb_group���pk_group�ֶ��������
 * ����ǵ��͵�Ψһ�������������one-to-one����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class ForeignKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// ����һ��UserGroup
		UserGroup group = createUserGroup();
		session.beginTransaction();
		Long pk_gorup = (Long) session.save(group);
		session.getTransaction().commit();
		
		// ����һ��User
		group.setId(pk_gorup);
		User user = createUser();
		user.setUserGroup(group);

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		// ����User����Ҳ�ܻ�ù�����UserGroup������Ϣ������one-to-one��
		User c2 = (User) session.load(User.class, 1L);
		System.out.println("User name is ��" + c2.getName() + "��");
		System.out.println("User's group is ��" + c2.getUserGroup().getName() + "��");
	}

	private static User createUser() {
		User user = new User();
		user.setName("Tom Hanks");
		user.setAge(40);
		return user;
	}

	private static UserGroup createUserGroup() {
		UserGroup group = new UserGroup();
		group.setName("VIP");
		return group;
	}

	@Override
	protected void prepareData() {
	}
}
