/**
 * 
 */
package hibernate.query.SQL;

/**
 * JavaBean示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class Dog {

	private Long id;
	private String name;
	private int age;
	
	private Long parentid;

	public Dog() {
		super();
	}

	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Dog(String name, int age, Long parentid) {
		this.name = name;
		this.age = age;
		this.parentid = parentid;
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
	
	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}
}
