/**
 * 
 */
package hibernate;

import java.util.Date;

/**
 * JavaBean
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
// ������Ҫ��final���������accessor/mutator�������ᵼ��Hibernate�Ķ�̬�������ʧЧ���Ӷ�Ӱ�����ܡ�
public class PersonVO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date timestamp;
	private int sex;
	
	/**
	 * ��Ҫ�ṩĬ�Ϲ�����
	 */
	public PersonVO() {
		super();
	}
	
	public PersonVO(String firstName, String lastName, int sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}
	
	/**
	 * ���ݿ��Զ�����id������setter������������Ϊprivate��
	 * ���ǲ���ȥ����JavaBean��Ҫ�ṩsetter/getter������Hibernateʹ�á�
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
