/**
 * 
 */
package hibernate.orm.association.one2one.fk2;

/**
 * Customer类
 * <p>
 * 每一个客户都有一个地址。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Customer {

	private Long id;
	private String name;
	private int age;
	
	// 这里存有关联的Address属性
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