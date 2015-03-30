/**
 * 
 */
package hibernate.orm.association.one2many.bidirectional;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 双向 one-to-many 关联示例
 * <p>
 * 信用卡表中包含一个 pk_person 字段，此字段与 tb_persons 表的  pk_person 字段相关联。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class BidirectionalOne2ManyTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// 保存一个Person及其信用卡信息
		Person2 person = createPerson();
		Set<CreditCard2> creditCards = createCreditCards();
		// 现在由CreditCard2对象维护关联关系，所以必须由它自己去维护外键。
		for (CreditCard2 creditCard : creditCards) {
			creditCard.setPerson(person);
		}
		person.setCreditCard(creditCards);

		session.beginTransaction();
		/*
		 * 具体执行SQL只有insert而没有update，提高了性能：
		 * insert into tb_person_2 (name, pk_person) values (?, ?) 
		 * insert into tb_creditcard_2 (no, expiry, pk_person, pk_card) values (?, ?, ?, ?)
		 */
		session.save(person);
		session.getTransaction().commit();

		// 根据CreditCard对象能获得关联的Person对象信息（双向one-to-many）
		CreditCard2 c1 = (CreditCard2) session.load(CreditCard2.class, 1L);
		System.out.println("Credit card is 【" + c1.getNo() + "】");
		System.out.println("Card holder is 【" + c1.getPerson().getName() + "】");
	}

	private static Set<CreditCard2> createCreditCards() {
		Set<CreditCard2> cards = new HashSet<CreditCard2>();
		
		CreditCard2 card1 = new CreditCard2();
		card1.setNo("CCB-2019288627");
		card1.setExpiry(new Date());
		cards.add(card1);
		
		CreditCard2 card2 = new CreditCard2();
		card2.setNo("ICBC-7871232112");
		card2.setExpiry(new Date());
		cards.add(card2);
		
		return cards;
	}

	private static Person2 createPerson() {
		Person2 person = new Person2();
		person.setName("Vic Liu");
		return person;
	}

	@Override
	protected void prepareData() {
	}
}
