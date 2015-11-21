/**
 * 
 */
package hibernate.collection.bag;

import java.util.ArrayList;
import java.util.List;

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
	
	// 使用一个List来存放
	private List<Part> parts;
	
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

	public List<Part> getParts() {
		if (parts == null) {
			parts = new ArrayList<Part>();
		}
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", parts=" + parts + "]";
	}
}
