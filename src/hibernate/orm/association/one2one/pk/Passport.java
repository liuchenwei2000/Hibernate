/**
 * 
 */
package hibernate.orm.association.one2one.pk;

import java.util.Date;

/**
 * Passport��
 * <p>
 * ÿһ�����ն���Ψһ�Ĺ�����֮��Ӧ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Passport {

	private Long id;
	private String serial;
	private Date expiry;
	
	// ������й�����Citizen����
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
