/**
 * 
 */
package hibernate.collection.list;

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
	private Integer serialNumber;
	
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

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPk_product() {
		return pk_product;
	}

	public void setPk_product(String pk_product) {
		this.pk_product = pk_product;
	}

	@Override
	public String toString() {
		return "Part [name=" + name + ", serial number=" + serialNumber + "]";
	}
}