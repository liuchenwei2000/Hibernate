/**
 * 
 */
package hibernate.dataloading.lazy;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class Computer {

	private Long id;
	private String name;
	private String bigdata;

	public Computer() {
		super();
	}

	public Computer(String name) {
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
	
	public String getBigdata() {
		return bigdata;
	}

	public void setBigdata(String bigdata) {
		this.bigdata = bigdata;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", bigdata=" + bigdata + "]";
	}
}
