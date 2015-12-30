/**
 * 
 */
package hibernate.lifecycle;

/**
 * 实体对象
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class Entity {

	private Long id;
	private String name;

	public Entity() {
		super();
	}

	public Entity(String name) {
		super();
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

	@Override
	public String toString() {
		return "Entity [name=" + name + "]";
	}
}
