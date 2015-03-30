/**
 * 
 */
package hibernate.orm.id;

import java.io.Serializable;
import java.util.Date;

/**
 * ����ʵ�������Եĸ�������
 * <p>
 * HibernateҪ�󸴺�������ʵ��equals��hashcode����������Ϊ��ͬ����֮��ʶ��ı�־��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
// �������������ʵ��Serializable�ӿ�
public class PersonVO1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2693429279210857661L;
	
	// firstName + lastName �����˸�������
	private String firstName;
	private String lastName;
	
	private Date timestamp;
	private int sex;
	
	public PersonVO1() {
		super();
	}
	
	public PersonVO1(String firstName, String lastName, int sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		PersonVO1 other = (PersonVO1) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
