/**
 * 
 */
package hibernate.orm.association.one2one.fk2;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 唯一外键关联示例
 * <p>
 * 这里实现了双向one-to-one。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class ForeignKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		Customer customer =createCustomer();
		Address address = createAddress();
		customer.setAddress(address);
		address.setCustomer(customer);

		// 保存Customer对象和Address对象
		session.beginTransaction();
		session.save(customer);
		session.save(address);
		session.getTransaction().commit();
		
		// 根据Customer对象也能获得关联的Address对象信息（双向one-to-one）
		Customer c2 = (Customer) session.load(Customer.class, 1L);
		System.out.println("Customer name is 【" + c2.getName() + "】");
		System.out.println("Customer's address is 【" + c2.getAddress().getCity() + "】");
		
		// 根据Address对象也能获得关联的Customer对象信息（双向one-to-one）
		Address a2 = (Address) session.load(Address.class, 1L);
		System.out.println("Address is 【" + a2.getCity() + "】");
		System.out.println("Address is of 【" + a2.getCustomer().getName() + "】");
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
