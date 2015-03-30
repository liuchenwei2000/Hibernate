/**
 * 
 */
package hibernate.orm.discriminator;

/**
 * 包含公共属性的基类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Item {

	private Long id;
	private String name;
	private int category;
	
	public Item() {
		super();
	}
	
	public Item(String name, int category) {
		super();
		this.name = name;
		this.category = category;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
