/**
 * 
 */
package hibernate.orm.model;

/**
 * name相关信息的抽象
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class NameVO {

	// 没有id属性，完全从属于实体对象PersonVO4
	private String firstName;
	private String lastName;

	public NameVO() {
		super();
	}

	public NameVO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
}
