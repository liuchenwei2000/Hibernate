/**
 * 
 */
package hibernate.collection.bag;

import java.util.ArrayList;
import java.util.List;

/**
 * Product��
 * <p>
 * ÿ��Product����һ������Part��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class Product {

	private Long id;
	private String name;
	
	// ʹ��һ��List�����
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
		if(parts == null) {
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
