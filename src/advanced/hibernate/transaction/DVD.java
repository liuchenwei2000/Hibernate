/**
 * 
 */
package hibernate.transaction;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class DVD {

	private Long id;
	private String name;
	private Integer version;

	public DVD() {
		super();
	}

	public DVD(String name) {
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
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + "]";
	}
}
