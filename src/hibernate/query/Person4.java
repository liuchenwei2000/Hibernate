/**
 * 
 */
package hibernate.query;

/**
 * JavaBeanʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
 */
public class Person4 {

	private Long id;
	private String name;
	private int age;

	public Person4() {
		super();
	}

	public Person4(String name, int age) {
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

	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
