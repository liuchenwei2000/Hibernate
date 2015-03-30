/**
 * 
 */
package hibernate.orm.discriminator;

/**
 * DVD类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class DVD extends Item {

	private String regionCode;// 特有属性

	public DVD() {
		super();
	}

	public DVD(String name) {
		super(name, 1);
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
}
