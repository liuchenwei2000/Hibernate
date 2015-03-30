/**
 * 
 */
package hibernate.orm.association.one2one.fk;

/**
 * User��
 * <p>
 * ÿһ���û�������Ψһ���û��顣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class User {

	private Long id;
	private String name;
	private int age;
	
	// ������й�����UserGroup����
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