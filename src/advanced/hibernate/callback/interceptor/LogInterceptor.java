/**
 * 
 */
package hibernate.callback.interceptor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * 日志拦截器
 * <p>
 * Interceptor实际上覆盖了Lifecycle接口的功能，且具备更少的代码入侵性。
 * 与Lifecycle相同，Interceptor的方法中不可通过session实例进行持久化操作。
 * <p>
 * 虽然可以在各个业务逻辑单元中编码实现，但通过Interceptor的方式更能体现集中管理，
 * 避免了业务逻辑单元各自编码的缺漏，提高了功能复用性。
 * <p>
 * 本例将会把对账户的更新操作全部记录在案。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月18日
 */
// 可以直接实现Interceptor接口，也可以继承空实现类EmptyInterceptor
public class LogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 2905579262842200063L;
	
	private List<Account> accountUpdated = new LinkedList<Account>();

	/**
	 * session.flush方法进行脏数据检查时调用
	 * 
	 * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if(entity instanceof Account){
			accountUpdated.add((Account) entity);
		}
		return false;
	}

	/**
	 * session.flush方法执行之后调用
	 * 
	 * @see org.hibernate.EmptyInterceptor#postFlush(java.util.Iterator)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void postFlush(Iterator entities) {
		if (!accountUpdated.isEmpty()) {
			Iterator<Account> iterator = accountUpdated.listIterator();
			while (iterator.hasNext()) {
				Account account = (Account) iterator.next();
				System.out.println("【AUDIT LOG】account " + account.getNo()
						+ " is updated, amount=" + account.getAmount());
			}
		}
	}
}
