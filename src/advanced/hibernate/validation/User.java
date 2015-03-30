/**
 * 
 */
package hibernate.validation;

import java.io.Serializable;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * ʵ�����
 * <p>
 * ��֤�߼���ʵ�����ͨ��annotations���а󶨣������������Ҫ��֤��Щ�߼��Լ�������֤��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3091666633466547089L;
	
	// ����Ϊ null
	@Null
	private Long id;
	
	// ���벻Ϊ null������� message ������BeanΥ����Լ��ʱ�ķ�����Ϣ
	@NotNull(message = "first name cannot be null.")
	private String firstName;
	
	@NotNull(message = "last name cannot be null.")
	private String lastName;
	
	// ����Ϊ true
	@AssertTrue(message = "user is illegal.")
	private boolean isLegal;
	
	// ����Ϊ false
	@AssertFalse(message = "user is deleted.")
	private boolean isDeleted;
	
	// ������һ�����֣���ֵ������ڵ���ָ������Сֵ
	@Min(value = 0, message = "age must be great than 0.")
	// ������һ�����֣���ֵ����С�ڵ���ָ�������ֵ
	@Max(value = 150, message = "age must be less than 150.")
	private int age;
	
	// ������һ�����֣���ֵ������ڵ���ָ������Сֵ
	@DecimalMin(value = "0", message = "account must be great than 0.")
	// ������һ�����֣���ֵ����С�ڵ���ָ�������ֵ
	@DecimalMax(value = "10000000", message = "account must be less than 10000000.")
	private double account;
	
	// ���ϡ����顢�ַ����Ĵ�С������ָ���ķ�Χ��
	@Size(min = 5, max = 100, message = "description must be between 5 and 100")
	private String description;
	
	/** ����� annotation �� hibernate-validator ���е� */
	
	@Email(message = "email is illegal.")
	private String email;
	
	@Length(min = 5, max = 11, message = "phone number must be between 5 and 11")
	private String phone;

	public User() {
		super();
	}
	
	public User(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isLegal() {
		return isLegal;
	}

	public void setLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [name=" + firstName + " " + lastName + "]";
	}
}
