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
 * ��־������
 * <p>
 * Interceptorʵ���ϸ�����Lifecycle�ӿڵĹ��ܣ��Ҿ߱����ٵĴ��������ԡ�
 * ��Lifecycle��ͬ��Interceptor�ķ����в���ͨ��sessionʵ�����г־û�������
 * <p>
 * ��Ȼ�����ڸ���ҵ���߼���Ԫ�б���ʵ�֣���ͨ��Interceptor�ķ�ʽ�������ּ��й���
 * ������ҵ���߼���Ԫ���Ա����ȱ©������˹��ܸ����ԡ�
 * <p>
 * ��������Ѷ��˻��ĸ��²���ȫ����¼�ڰ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��18��
 */
// ����ֱ��ʵ��Interceptor�ӿڣ�Ҳ���Լ̳п�ʵ����EmptyInterceptor
public class LogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 2905579262842200063L;
	
	private List<Account> accountUpdated = new LinkedList<Account>();

	/**
	 * session.flush�������������ݼ��ʱ����
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
	 * session.flush����ִ��֮�����
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
				System.out.println("��AUDIT LOG��account " + account.getNo()
						+ " is updated, amount=" + account.getAmount());
			}
		}
	}
}
