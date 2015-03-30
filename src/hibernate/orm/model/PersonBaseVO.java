/**
 * 
 */
package hibernate.orm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Blob、Clob等重量级字段类型，数据库读取操作代价较高。
 * 对于需要处理resume的应用逻辑而言，这样的代价无法避免，
 * 而对于那些无需resume信息的操作而言，如此性能损耗实属不必要。
 * 
 * 对象在集成层次上的粒度划分可以解决这个问题：
 * 将原本的PersonVO对象拆分成包含基本信息的PersonBaseVO和包含resume信息的PersonResumeVO。
 * 这样一来，需要处理resume的应用通过PersonResumeVO加载数据，其他应用通过PersonBaseVO加载数据。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
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
