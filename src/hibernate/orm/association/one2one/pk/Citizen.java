/**
 * 
 */
package hibernate.orm.association.one2one.pk;

/**
 * Citizen��
 * <p>
 * ÿһ��������Ψһ�Ļ�����֮��Ӧ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Citizen {

	private Long id;
	private String name;
	private int age;
	
	// ������й�����Passport����
	private Passport passport;

	public Citizen() {
		super();
	}

	public Citizen(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
		// ����passportʱҲ���������õ�passport������
		passport.setCitizen(this);
	}
}