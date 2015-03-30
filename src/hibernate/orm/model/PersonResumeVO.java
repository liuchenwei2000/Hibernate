/**
 * 
 */
package hibernate.orm.model;

import java.sql.Clob;

/**
 * 将重量级的字段封装到一个对象。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class PersonResumeVO extends PersonBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080669966927557728L;
	
	private Clob resume;// 简历，CLOB
	
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
