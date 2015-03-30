/**
 * 
 */
package hibernate.orm.association.many2many;

import java.util.HashSet;
import java.util.Set;

/**
 * ��ɫ
 * <p>
 * ÿ����ɫ�������ڶ����Ա�顣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��27��
 */
public class Role {

	private Long id;
	private String name;

	private Set<Group> groups;
	
	public Role() {
		super();
	}

	public Role(String name) {
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

	public Set<Group> getGroups() {
		if (groups == null) {
			groups = new HashSet<Group>();
		}
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
