/**
 * 
 */
package hibernate.template;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class City {

	private Long id;
	private String name;

	public City() {
		super();
	}

	public City(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
}
