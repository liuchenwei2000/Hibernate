/**
 * 
 */
package hibernate.dataloading.lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * Student��
 * <p>
 * ÿ��ѧ������һ��������ϵ��ʽ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Student {

	private Long id;
	private String name;
	
	// ʹ��һ�����������
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
		if(contacts == null) {
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
