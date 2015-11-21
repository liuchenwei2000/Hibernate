/**
 * 
 */
package hibernate.orm.association.one2one.pk;

/**
 * Citizen类
 * <p>
 * 每一个公民都有唯一的护照与之对应。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Citizen {

	private Long id;
	private String name;
	private int age;
	
	// 这里存有关联的Passport属性
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
		// 设置passport时也将自身设置到passport对象中
		passport.setCitizen(this);
	}
}
