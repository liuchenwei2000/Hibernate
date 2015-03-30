/**
 * 
 */
package hibernate.orm.association.one2many.unidirectional;

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
public class Person1 {

	private Long id;
	private String name;
	
	// ʹ��һ�����������
	private Set<CreditCard1> creditCard;
	
	public Person1() {
		super();
	}

	public Person1(String name) {
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

	public Set<CreditCard1> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Set<CreditCard1> creditCard) {
		this.creditCard = creditCard;
	}
}
