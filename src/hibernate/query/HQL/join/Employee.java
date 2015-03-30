/**
 * 
 */
package hibernate.query.HQL.join;

import java.util.HashSet;
import java.util.Set;

/**
 * 雇员
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class Employee {

	private Long id;
	private String name;

	private Set<DebitCard> cards;

	public Employee() {
		super();
	}

	public Employee(String name) {
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

	public Set<DebitCard> getCards() {
		if(cards == null) {
			this.cards = new HashSet<DebitCard>();
		}
		return cards;
	}

	public void setCards(Set<DebitCard> cards) {
		this.cards = cards;
	}

	public void addDebitCard(DebitCard card){
		 getCards().add(card);
	}

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
