/**
 * 
 */
package hibernate.orm.association.one2one.pk;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;

/**
 * 主键关联示例
 * <p>
 * 一对一关联包括两种类型：
 * 
 * 1，主键关联
 * 两张关联表通过主键形成一对一映射关系。
 * 由于采用了主键关联方式，那么通过主键关联的两张表，其关联记录的主键值须保持同步。
 * 这就意味着，只需为一张表设定主键生成器，而另一张表的主键与之共享相同的主键值。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class PrimaryKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		Citizen citizen = createCitizen();
		citizen.setPassport(createPassport());

		// 保存User的时候会同时保存Passport
		session.beginTransaction();
		session.save(citizen);
		session.getTransaction().commit();
		
		// 根据Citizen对象也能获得关联的Passport对象信息
		Citizen c2 = (Citizen) session.load(Citizen.class, 1L);
		System.out.println("Citizen name is 【" + c2.getName() + "】");
		System.out.println("The serial number of Citizen's passport is 【" + c2.getPassport().getSerial() + "】");
	}

	private static Citizen createCitizen() {
		Citizen citizen = new Citizen();
		citizen.setName("Tom Hanks");
		citizen.setAge(40);
		return citizen;
	}

	private static Passport createPassport() {
		Passport passport = new Passport();
		passport.setSerial("CN-000988762");
		passport.setExpiry(new Date());
		return passport;
	}

	@Override
	protected void prepareData() {
	}
}
