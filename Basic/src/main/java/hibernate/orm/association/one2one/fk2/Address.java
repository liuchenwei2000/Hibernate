/**
 * 
 */
package hibernate.orm.association.one2one.fk2;

/**
 * Address类
 * <p>
 * 每个地址都从属于一个客户。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Address {

	private Long id;

	private String city;
	private String street;
	private String phone1;
	private String phone2;
	private String phone3;

	// 这里存有关联的Customer属性
	private Customer customer;

	public Address() {
		super();
	}

	public Address(String city, String street, String phone1) {
		this.city = city;
		this.street = street;
		this.phone1 = phone1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
