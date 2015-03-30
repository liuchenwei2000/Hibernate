/**
 * 
 */
package hibernate.orm.association.one2one.pk;

import java.util.Date;

/**
 * Passport类
 * <p>
 * 每一个护照都有唯一的公民与之对应。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Passport {

	private Long id;
	private String serial;
	private Date expiry;
	
	// 这里存有关联的Citizen属性
	private Citizen citizen;
	
	public Passport() {
		super();
	}

	public Passport(String serial, Date expiry) {
		super();
		this.serial = serial;
		this.expiry = expiry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
}
