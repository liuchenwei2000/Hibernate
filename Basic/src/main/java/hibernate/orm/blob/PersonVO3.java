/**
 * 
 */
package hibernate.orm.blob;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

/**
 * 需要将头像图片和简历存入数据库中。
 * <p>
 * Blob和Clob字段的区别在于:
 * Blob字段采用单字节存储，适合保存二进制数据；
 * Clob字段采用多字节存储，适合保存大型文本数据。
 * Blob和Clob在各种数据库中有其独立实现。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class PersonVO3 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080669966927557728L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date timestamp;
	private int sex;
	
	private Blob image;// 头像图片，BLOB
	private Clob resume;// 简历，CLOB
	
	public PersonVO3() {
		super();
	}
	
	public PersonVO3(String firstName, String lastName, int sex) {
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

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Clob getResume() {
		return resume;
	}

	public void setResume(Clob resume) {
		this.resume = resume;
	}
}
