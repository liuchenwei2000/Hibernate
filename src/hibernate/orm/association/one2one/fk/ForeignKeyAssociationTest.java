/**
 * 
 */
package hibernate.orm.association.one2one.fk;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 唯一外键关联示例
 * <p>
 * 用户表中包含一个pk_group字段，此字段与tb_group表的pk_group字段相关联。
 * 这就是典型的唯一外键关联（单向one-to-one）。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class ForeignKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// 保存一个UserGroup
		UserGroup group = createUserGroup();
		session.beginTransaction();
		Long pk_gorup = (Long) session.save(group);
		session.getTransaction().commit();
		
		// 保存一个User
		group.setId(pk_gorup);
		User user = createUser();
		user.setUserGroup(group);

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		// 根据User对象也能获得关联的UserGroup对象信息（单向one-to-one）
		User c2 = (User) session.load(User.class, 1L);
		System.out.println("User name is 【" + c2.getName() + "】");
		System.out.println("User's group is 【" + c2.getUserGroup().getName() + "】");
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
