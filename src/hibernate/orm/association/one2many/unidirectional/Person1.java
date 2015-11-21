/**
 * 
 */
package hibernate.orm.association.one2many.unidirectional;

import java.util.Set;

/**
 * Person类
 * <p>
 * 每个人都有0张或多张信用卡。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Person1 {

	private Long id;
	private String name;
	
	// 使用一个集合来存放
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
