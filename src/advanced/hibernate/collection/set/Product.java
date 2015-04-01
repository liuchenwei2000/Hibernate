/**
 * 
 */
package hibernate.collection.set;

import java.util.HashSet;
import java.util.Set;

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
	
	// ʹ��һ�����������
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
