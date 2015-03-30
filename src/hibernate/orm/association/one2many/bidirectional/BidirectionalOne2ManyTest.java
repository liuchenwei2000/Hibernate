/**
 * 
 */
package hibernate.orm.association.one2many.bidirectional;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ˫�� one-to-many ����ʾ��
 * <p>
 * ���ÿ����а���һ�� pk_person �ֶΣ����ֶ��� tb_persons ���  pk_person �ֶ��������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class BidirectionalOne2ManyTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// ����һ��Person�������ÿ���Ϣ
		Person2 person = createPerson();
		Set<CreditCard2> creditCards = createCreditCards();
		// ������CreditCard2����ά��������ϵ�����Ա��������Լ�ȥά�������
		for (CreditCard2 creditCard : creditCards) {
			creditCard.setPerson(person);
		}
		person.setCreditCard(creditCards);

		session.beginTransaction();
		/*
		 * ����ִ��SQLֻ��insert��û��update����������ܣ�
		 * insert into tb_person_2 (name, pk_person) values (?, ?) 
		 * insert into tb_creditcard_2 (no, expiry, pk_person, pk_card) values (?, ?, ?, ?)
		 */
		session.save(person);
		session.getTransaction().commit();

		// ����CreditCard�����ܻ�ù�����Person������Ϣ��˫��one-to-many��
		CreditCard2 c1 = (CreditCard2) session.load(CreditCard2.class, 1L);
		System.out.println("Credit card is ��" + c1.getNo() + "��");
		System.out.println("Card holder is ��" + c1.getPerson().getName() + "��");
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
