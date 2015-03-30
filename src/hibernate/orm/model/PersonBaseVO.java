/**
 * 
 */
package hibernate.orm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Blob��Clob���������ֶ����ͣ����ݿ��ȡ�������۽ϸߡ�
 * ������Ҫ����resume��Ӧ���߼����ԣ������Ĵ����޷����⣬
 * ��������Щ����resume��Ϣ�Ĳ������ԣ�����������ʵ������Ҫ��
 * 
 * �����ڼ��ɲ���ϵ����Ȼ��ֿ��Խ��������⣺
 * ��ԭ����PersonVO�����ֳɰ���������Ϣ��PersonBaseVO�Ͱ���resume��Ϣ��PersonResumeVO��
 * ����һ������Ҫ����resume��Ӧ��ͨ��PersonResumeVO�������ݣ�����Ӧ��ͨ��PersonBaseVO�������ݡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class PersonBaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080669966927557728L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date timestamp;
	private int sex;
	
	public PersonBaseVO() {
		super();
	}
	
	public PersonBaseVO(String firstName, String lastName, int sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
