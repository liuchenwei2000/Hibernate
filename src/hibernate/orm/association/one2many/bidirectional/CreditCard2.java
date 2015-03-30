/**
 * 
 */
package hibernate.orm.association.one2many.bidirectional;

import java.util.Date;

/**
 * CreditCard��
 * <p>
 * ÿһ�����ÿ�������ĳһ���ˡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class CreditCard2 {

	private Long id;
	private String no;
	private Date expiry;
	
	// ������гֿ��˶����Դﵽ˫�����
	private Person2 person;

	public CreditCard2() {
		super();
	}

	public CreditCard2(String no, Date expiry) {
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

	public Person2 getPerson() {
		return person;
	}

	public void setPerson(Person2 person) {
		this.person = person;
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
		CreditCard2 other = (CreditCard2) obj;
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