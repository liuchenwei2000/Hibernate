/**
 * 
 */
package hibernate.orm.discriminator;

/**
 * DVD��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class DVD extends Item {

	private String regionCode;// ��������

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
