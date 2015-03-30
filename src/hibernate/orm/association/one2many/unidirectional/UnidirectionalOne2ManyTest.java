/**
 * 
 */
package hibernate.orm.association.one2many.unidirectional;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ���� one-to-many ����ʾ��
 * <p>
 * ���ÿ����а���һ��pk_person �ֶΣ����ֶ��� tb_persons ���  pk_person �ֶ��������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class UnidirectionalOne2ManyTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		// ����һ��Person�������ÿ���Ϣ
		Person1 person = createPerson();
		person.setCreditCard(createCreditCards());

	   /*
		* �����ǵ��������Ϊ�˱��ֹ�����ϵ��ֻ��ͨ�����ط��Ա��������м������¡�
		* ����������Ĺ����ֶ�Ϊ NOT NULL����Hibernate�������߸��¹�����ϵʱ���ͻ����Լ��Υ����
		* <p>
		* ��������� CreditCard1.hbm.xml �ļ��е� <property name="pk_person" />�滻Ϊ
		* <property name="pk_person" not-null="true" />���򱣴������ʧ�ܡ�
		* 
		* ����ԭ������Ϊsave������Ӧ��sql��估ִ��˳�����£�
		* 1���Ȳ������ط���
		* insert into tb_person_1 (name, pk_person) values (?, ?);
		* 2���ٲ��뱻������(��ʱ��� pk_person ��ֵΪnull)��
		* insert into tb_creditcard_1 (no, expiry, pk_person, pk_card) values (?, ?, ?, ?);
		* 3�����±�����������ֵ
		* update tb_creditcard_1 set pk_person=? where pk_card=?;
		* 
		* ��Ϊ���������ǵ��򣬹�����ϵ�����ط�(Person1)����ά�֣���������(CreditCard1)������
		* ����֪���Լ����ĸ�Person1���������������֪���Լ���pk_personӦ����Ϊʲôֵ��
		* ���ԣ��ڱ���CreditCard1����ʱ��ֻ�����ڹ����ֶ�(���)����һ����ֵ��֮������Person1�������������
		* ���������ֶ�creditcCard.pk_person�������ֵ��������CreditcCard1�������Է����䶯��
		* �������ύʱ��Hibernate�ᷢ����һ�ı䣬��ͨ��Update��佫�䶯������ݱ��浽���ݿ⡣
		* 
		* ��˫��һ�Զ��ϵ���Խ��������⡿
		*/
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();

		// ����Person�����ܻ�ù�����CreditCard������Ϣ������one-to-many��
		Person1 p1 = (Person1) session.load(Person1.class, 1L);
		System.out.println("Person's name is ��" + p1.getName() + "��");
		System.out.println("Credit crads are ��" + p1.getCreditCard() + "��");
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
