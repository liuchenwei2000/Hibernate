/**
 * 
 */
package hibernate;

import java.util.Date;

/**
 * JavaBean
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
// 尽量不要用final修饰类或者accessor/mutator，这样会导致Hibernate的动态代理机制失效，从而影响性能。
public class PersonVO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date timestamp;
	private int sex;
	
	/**
	 * 需要提供默认构造器
	 */
	public PersonVO() {
		super();
	}
	
	public PersonVO(String firstName, String lastName, int sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}
	
	/**
	 * 数据库自动生成id，所以setter方法可以声明为private。
	 * 但是不能去掉，JavaBean需要提供setter/getter方法供Hibernate使用。
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
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
