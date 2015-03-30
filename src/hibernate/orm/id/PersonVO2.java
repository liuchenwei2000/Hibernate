/**
 * 
 */
package hibernate.orm.id;

import java.io.Serializable;
import java.util.Date;

/**
 * ����������ĸ�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
//�������������ʵ��Serializable�ӿ�
public class PersonVO2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8084575803139489272L;

	private PersonPK pk;
	
	private Date timestamp;
	private int sex;
	
	public PersonVO2() {
		super();
	}
	
	public PersonPK getPk() {
		return pk;
	}

	public void setPk(PersonPK pk) {
		this.pk = pk;
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
