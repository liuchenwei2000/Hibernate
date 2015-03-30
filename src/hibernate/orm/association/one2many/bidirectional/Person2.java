/**
 * 
 */
package hibernate.orm.association.one2many.bidirectional;

import java.util.Set;

/**
 * Person��
 * <p>
 * ÿ���˶���0�Ż�������ÿ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Person2 {

	private Long id;
	private String name;
	
	private Set<CreditCard2> creditCard;
	
	public Person2() {
		super();
	}

	public Person2(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CreditCard2> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Set<CreditCard2> creditCard) {
		this.creditCard = creditCard;
	}
}
