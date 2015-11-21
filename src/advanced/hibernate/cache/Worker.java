/**
 * 
 */
package hibernate.cache;

/**
 * 实体对象
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月6日
 */
public class Worker {

	private Long id;
	private String name;
	private int age;

	public Worker() {
		super();
	}

	public Worker(String name, int age) {
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

	@Override
	public String toString() {
		return "Worker [name=" + name + ", age=" + age + "]";
	}
}
