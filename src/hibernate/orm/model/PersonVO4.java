/**
 * 
 */
package hibernate.orm.model;

import java.util.Date;

/**
 * Ϊ�������Ҫ����PersonVO����Ϣ����ϸ�֣���name��ص���Ϣ������ȡ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class PersonVO4 {

	private Long id;
	private NameVO name;
	private Date timestamp;
	private int sex;
	
	public PersonVO4() {
		super();
	}
	
	public PersonVO4(String firstName, String lastName, int sex) {
		this.name = new NameVO(firstName, lastName);
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public NameVO getName() {
		return name;
	}

	public void setName(NameVO name) {
		this.name = name;
	}
}
