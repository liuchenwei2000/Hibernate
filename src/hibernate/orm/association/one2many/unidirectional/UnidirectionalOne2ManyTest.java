/**
 * 
 */
package hibernate.orm.association.one2many.unidirectional;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 单向 one-to-many 关联示例
 * <p>
 * 信用卡表中包含一个pk_person 字段，此字段与 tb_persons 表的  pk_person 字段相关联。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class UnidirectionalOne2ManyTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// 保存一个Person及其信用卡信息
		Person1 person = createPerson();
		person.setCreditCard(createCreditCards());

	   /*
		* 由于是单向关联，为了保持关联关系，只能通过主控方对被动方进行级联更新。
		* 如果被动方的关联字段为 NOT NULL，当Hibernate创建或者更新关联关系时，就会出现约束违例。
		* <p>
		* 本例如果把 CreditCard1.hbm.xml 文件中的 <property name="pk_person" />替换为
		* <property name="pk_person" not-null="true" />，则保存操作会失败。
		* 
		* 具体原因是因为save动作对应的sql语句及执行顺序如下：
		* 1，先插入主控方表
		* insert into tb_person_1 (name, pk_person) values (?, ?);
		* 2，再插入被动方表(此时外键 pk_person 的值为null)：
		* insert into tb_creditcard_1 (no, expiry, pk_person, pk_card) values (?, ?, ?, ?);
		* 3，更新被动方表的外键值
		* update tb_creditcard_1 set pk_person=? where pk_card=?;
		* 
		* 因为关联方向是单向，关联关系由主控方(Person1)对象维持，而被动方(CreditCard1)对象本身
		* 并不知道自己与哪个Person1对象相关联，即不知道自己的pk_person应该设为什么值。
		* 所以，在保存CreditCard1对象时，只能先在关联字段(外键)插入一个空值，之后，再由Person1对象将自身的主键
		* 赋给关联字段creditcCard.pk_person，这个赋值操作导致CreditcCard1对象属性发生变动，
		* 在事务提交时，Hibernate会发现这一改变，并通过Update语句将变动后的数据保存到数据库。
		* 
		* 【双向一对多关系可以解决这个问题】
		*/
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();

		// 根据Person对象能获得关联的CreditCard对象信息（单向one-to-many）
		Person1 p1 = (Person1) session.load(Person1.class, 1L);
		System.out.println("Person's name is 【" + p1.getName() + "】");
		System.out.println("Credit crads are 【" + p1.getCreditCard() + "】");
	}

	private static Set<CreditCard1> createCreditCards() {
		Set<CreditCard1> cards = new HashSet<CreditCard1>();
		
		CreditCard1 card1 = new CreditCard1();
		card1.setNo("CCB-2019288627");
		card1.setExpiry(new Date());
		cards.add(card1);
		
		CreditCard1 card2 = new CreditCard1();
		card2.setNo("ICBC-7871232112");
		card2.setExpiry(new Date());
		cards.add(card2);
		
		return cards;
	}

	private static Person1 createPerson() {
		Person1 person = new Person1();
		person.setName("Vic Liu");
		return person;
	}

	@Override
	protected void prepareData() {
	}
}
