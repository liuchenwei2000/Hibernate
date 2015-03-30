/**
 * 
 */
package hibernate.orm.model;

import java.sql.Clob;

/**
 * �����������ֶη�װ��һ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class PersonResumeVO extends PersonBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080669966927557728L;
	
	private Clob resume;// ������CLOB
	
	public PersonResumeVO() {
		super();
	}
	
	public PersonResumeVO(String firstName, String lastName, int sex) {
		super(firstName, lastName, sex);
	}

	public Clob getResume() {
		return resume;
	}

	public void setResume(Clob resume) {
		this.resume = resume;
	}
}
