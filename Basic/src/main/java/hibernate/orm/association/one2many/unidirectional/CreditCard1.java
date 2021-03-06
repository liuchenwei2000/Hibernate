/**
 * 
 */
package hibernate.orm.association.one2many.unidirectional;

import java.util.Date;

/**
 * CreditCard类
 * <p>
 * 每一张信用卡都属于某一个人。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class CreditCard1 {

	private Long id;
	private String no;
	private Date expiry;
	
	// 这里存有持卡人的主键
	private String pk_person;

	public CreditCard1() {
		super();
	}

	public CreditCard1(String no, Date expiry) {
		this.no = no;
		this.expiry = expiry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getPk_person() {
		return pk_person;
	}

	public void setPk_person(String pk_person) {
		this.pk_person = pk_person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard1 other = (CreditCard1) obj;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [no=" + no + "]";
	}
}
