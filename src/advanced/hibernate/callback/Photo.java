/**
 * 
 */
package hibernate.callback;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.Session;
import org.hibernate.classic.Lifecycle;

/**
 * ʵ�����ʵ����Lifecycle�ӿ�
 * <p>
 * Hibernateͨ��Lifecycle�ӿ��ƶ���ʵ�����CRUD�����еĻص����ơ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class Photo implements Lifecycle{

	private Long id;
	private String name;
	private int size;

	public Photo() {
		super();
	}

	public Photo(String name, int size) {
		this.name = name;
		this.size = size;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Photo [name=" + name + ", size=" + size + "]";
	}

	/**
	 * ����ǰ����
	 * 
	 * @see org.hibernate.classic.Lifecycle#onSave(org.hibernate.Session)
	 */
	@Override
	public boolean onSave(Session s) throws CallbackException {
		// �������true����ζ����Ҫ��ִֹ�ж�Ӧ�Ĳ������̣�����׳�CallbackException����Ҳ�ᱻ��ֹ��
		if(size > 100) {
			throw new CallbackException("size is too large.");
		}
		return false;
	}

	/**
	 * ����ǰ����
	 * 
	 * @see org.hibernate.classic.Lifecycle#onUpdate(org.hibernate.Session)
	 */
	@Override
	public boolean onUpdate(Session s) throws CallbackException {
		if(size > 100) {
			throw new CallbackException("size is too large.");
		}
		return false;
	}

	/**
	 * ɾ��ǰ����
	 * 
	 * @see org.hibernate.classic.Lifecycle#onDelete(org.hibernate.Session)
	 */
	@Override
	public boolean onDelete(Session s) throws CallbackException {
		boolean isInUse = true;
		if(isInUse) {
			throw new CallbackException("photo is in use.");
		}
		return false;
	}

	/**
	 * ʵ����󱻼���ʱ����
	 * 
	 * @see org.hibernate.classic.Lifecycle#onLoad(org.hibernate.Session, java.io.Serializable)
	 */
	@Override
	public void onLoad(Session s, Serializable id) {
		System.out.println("��haha, I'm loaded��");
	}
}
