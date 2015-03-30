/**
 * 
 */
package hibernate.orm.blob;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

/**
 * ��Ҫ��ͷ��ͼƬ�ͼ����������ݿ��С�
 * <p>
 * Blob��Clob�ֶε���������:
 * Blob�ֶβ��õ��ֽڴ洢���ʺϱ�����������ݣ�
 * Clob�ֶβ��ö��ֽڴ洢���ʺϱ�������ı����ݡ�
 * Blob��Clob�ڸ������ݿ����������ʵ�֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
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
	
	private Blob image;// ͷ��ͼƬ��BLOB
	private Clob resume;// ������CLOB
	
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
