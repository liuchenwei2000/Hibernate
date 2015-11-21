/**
 * 
 */
package hibernate.template;

import org.hibernate.Session;

/**
 * Hibernate操作回调接口
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月20日
 */
public interface HibernateCallback {

	/**
	 * 执行操作
	 */
	public void execute(Session session);
}
