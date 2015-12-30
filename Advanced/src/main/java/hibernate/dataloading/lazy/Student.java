/**
 * 
 */
package hibernate.dataloading.lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * Student类
 * <p>
 * 每个学生都有一个或多个联系方式。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Student {

	private Long id;
	private String name;
	
	// 使用一个集合来存放
	private Set<Contact> contacts;
	
	public Student() {
		super();
	}

	public Student(String name) {
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

	public Set<Contact> getContacts() {
		if (contacts == null) {
			contacts = new HashSet<Contact>();
		}
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", contacts=" + contacts + "]";
	}
}
