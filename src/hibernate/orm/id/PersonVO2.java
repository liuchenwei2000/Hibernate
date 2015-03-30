/**
 * 
 */
package hibernate.orm.id;

import java.io.Serializable;
import java.util.Date;

/**
 * 基于主键类的复合主键
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
//复合主键类必须实现Serializable接口
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
