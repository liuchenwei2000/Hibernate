/**
 * 
 */
package hibernate.collection.set;

/**
 * Part类
 * <p>
 * 每个Part都属于一个Product。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
public class Part {

	private Long id;
	
	private String name;
	
	private String pk_product;

	public Part() {
		super();
	}

	public Part(String name) {
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

	public String getPk_product() {
		return pk_product;
	}

	public void setPk_product(String pk_product) {
		this.pk_product = pk_product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((pk_product == null) ? 0 : pk_product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pk_product == null) {
			if (other.pk_product != null)
				return false;
		} else if (!pk_product.equals(other.pk_product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Part [name=" + name + "]";
	}
}
