/**
 * 
 */
package hibernate.orm.association.one2one.fk;

/**
 * UserGroup类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class UserGroup {

	private Long id;
	private String name;
	
	public UserGroup() {
		super();
	}

	public UserGroup(String name) {
		super();
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
}
