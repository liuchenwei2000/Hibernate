/**
 * 
 */
package hibernate.orm.model;

/**
 * name�����Ϣ�ĳ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class NameVO {

	// û��id���ԣ���ȫ������ʵ�����PersonVO4
	private String firstName;
	private String lastName;

	public NameVO() {
		super();
	}

	public NameVO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
