/**
 * 
 */
package hibernate.orm.association.many2many;

import java.util.HashSet;
import java.util.Set;

/**
 * ��Ա��
 * <p>
 * ÿ����Ա����԰��������ɫ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��27��
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
