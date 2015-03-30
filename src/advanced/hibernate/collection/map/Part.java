/**
 * 
 */
package hibernate.collection.map;

/**
 * Part��
 * <p>
 * ÿ��Part������һ��Product��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
public class Part {

	private Long id;
	
	private String name;
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPk_product() {
		return pk_product;
	}

	public void setPk_product(String pk_product) {
		this.pk_product = pk_product;
	}

	@Override
	public String toString() {
		return "Part [name=" + name + ", type=" + type + "]";
	}
}