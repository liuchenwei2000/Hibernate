/**
 * 
 */
package hibernate.callback;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.Session;
import org.hibernate.classic.Lifecycle;

/**
 * 实体对象，实现了Lifecycle接口
 * <p>
 * Hibernate通过Lifecycle接口制定了实体对象CRUD过程中的回调机制。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
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
	 * 保存前触发
	 * 
	 * @see org.hibernate.classic.Lifecycle#onSave(org.hibernate.Session)
	 */
	@Override
	public boolean onSave(Session s) throws CallbackException {
		// 如果返回true则意味着需要中止执行对应的操作过程，如果抛出CallbackException操作也会被中止。
		if(size > 100) {
			throw new CallbackException("size is too large.");
		}
		return false;
	}

	/**
	 * 更新前触发
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
	 * 删除前触发
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
	 * 实体对象被加载时触发
	 * 
	 * @see org.hibernate.classic.Lifecycle#onLoad(org.hibernate.Session, java.io.Serializable)
	 */
	@Override
	public void onLoad(Session s, Serializable id) {
		System.out.println("【haha, I'm loaded】");
	}
}
