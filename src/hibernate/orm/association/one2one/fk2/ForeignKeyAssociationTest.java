/**
 * 
 */
package hibernate.orm.association.one2one.fk2;

import hibernate.util.AbstractHibernateTestCase;

/**
 * Ψһ�������ʾ��
 * <p>
 * ����ʵ����˫��one-to-one��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class ForeignKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		Customer customer =createCustomer();
		Address address = createAddress();
		customer.setAddress(address);
		address.setCustomer(customer);

		// ����Customer�����Address����
		session.beginTransaction();
		session.save(customer);
		session.save(address);
		session.getTransaction().commit();
		
		// ����Customer����Ҳ�ܻ�ù�����Address������Ϣ��˫��one-to-one��
		Customer c2 = (Customer) session.load(Customer.class, 1L);
		System.out.println("Customer name is ��" + c2.getName() + "��");
		System.out.println("Customer's address is ��" + c2.getAddress().getCity() + "��");
		
		// ����Address����Ҳ�ܻ�ù�����Customer������Ϣ��˫��one-to-one��
		Address a2 = (Address) session.load(Address.class, 1L);
		System.out.println("Address is ��" + a2.getCity() + "��");
		System.out.println("Address is of ��" + a2.getCustomer().getName() + "��");
	}

	private static Customer createCustomer() {
		Customer customer = new Customer();
		customer.setName("Tom Hanks");
		customer.setAge(40);
		return customer;
	}

	private static Address createAddress() {
		Address address = new Address();
		address.setCity("Beijing");
		address.setStreet("Red Hill street No.129");
		address.setPhone1("010-12976367");
		address.setPhone2("+8613628373893");
		return address;
	}

	@Override
	protected void prepareData() {
	}
}
