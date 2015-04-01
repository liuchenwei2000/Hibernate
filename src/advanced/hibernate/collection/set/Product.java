/**
 * 
 */
package hibernate.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * Product类
 * <p>
 * 每个Product都有一个或多个Part。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class Product {

	private Long id;
	private String name;
	
	// 使用一个集合来存放
	private Set<Part> parts;
	
	public Product() {
		super();
	}

	public Product(String name) {
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

	public Set<Part> getParts() {
		if (parts == null) {
			parts = new HashSet<Part>();
		}
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", parts=" + parts + "]";
	}
}
