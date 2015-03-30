/**
 * 
 */
package hibernate.orm.association.one2one.fk;

/**
 * User类
 * <p>
 * 每一个用户都属于唯一的用户组。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class User {

	private Long id;
	private String name;
	private int age;
	
	// 这里存有关联的UserGroup属性
	private UserGroup userGroup;

	public User() {
		super();
	}

	public User(String name, int age) {
		super();
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

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}