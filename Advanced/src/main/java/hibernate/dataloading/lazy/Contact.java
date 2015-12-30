/**
 * 
 */
package hibernate.dataloading.lazy;

/**
 * Contact类
 * <p>
 * 每个联系方式都属于一个Student
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Contact {

	private Long id;
	
	private String address;
	private String phone;
	
	private String pk_student;

	public Contact() {
		super();
	}

	public Contact(String address, String phone) {
		this.address = address;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPk_student() {
		return pk_student;
	}

	public void setPk_student(String pk_student) {
		this.pk_student = pk_student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [address=" + address + ", phone=" + phone + "]";
	}
}
