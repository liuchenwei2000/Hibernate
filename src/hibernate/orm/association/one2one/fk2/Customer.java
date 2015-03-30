/**
 * 
 */
package hibernate.orm.association.one2one.fk2;

/**
 * Customer��
 * <p>
 * ÿһ���ͻ�����һ����ַ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Customer {

	private Long id;
	private String name;
	private int age;
	
	// ������й�����Address����
	private Address address;

	public Customer() {
		super();
	}

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}