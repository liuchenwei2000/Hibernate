/**
 * 
 */
package hibernate.pagination;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��19��
 */
public class Record {

	private Long id;
	private String name;

	public Record() {
		super();
	}

	public Record(String name) {
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
		return "Record [name=" + name + "]";
	}
}
