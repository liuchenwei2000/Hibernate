/**
 * 
 */
package hibernate.cache;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
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
