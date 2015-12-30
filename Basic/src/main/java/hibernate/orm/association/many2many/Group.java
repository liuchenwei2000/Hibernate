/**
 * 
 */
package hibernate.orm.association.many2many;

import java.util.HashSet;
import java.util.Set;

/**
 * 人员组
 * <p>
 * 每个人员组可以包含多个角色。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月27日
 */
public class Group {

	private Long id;
	private String name;
	
	private Set<Role> roles;

	public Group() {
		super();
	}

	public Group(String name) {
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
	
	public Set<Role> getRoles() {
		if (roles == null) {
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
