/**
 * 
 */
package hibernate.lifecycle;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * ʵ������������� ʾ��
 * <p>
 * ʵ�������ָO/R Mapping�е�"O"�������������
 * <p>
 * ʵ���������������������״̬��Transient������̬����Persistent���־�̬����Detached������̬����
 * <li>Transient������̬����
 * ��ʵ��������ڴ��е����ɴ��ڣ��������ݿ��еļ�¼�޹ء�
 * <li>Persistent���־�̬����
 * ��ʵ���������Hibernate����������״̬������״̬�£�ʵ���������Hibernateʵ�������м��Թ���
 * ����Persistent״̬�Ķ�����������Hibernate�־û������ݿ��С�
 * <li>Detached������̬����
 * ����Persistent̬�Ķ������Ӧ��sessionʵ���ر�֮�󣬴˶���ʹ���Detached״̬��
 * Sessionʵ�����Կ�����Persistent�����������һ��������ʧЧ����ô�������Persistent���󼴽���Detached״̬��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class EntityLifecycleTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		// ��������������� Transient̬�����������ݿ�ļ�¼�޹�
		Entity transientEntity = new Entity("transientEntity");
		Entity persistentEntity = new Entity("persistentEntity");
		
		// ������ transientEntity�� persistentEntity ������ Transient̬
		session.beginTransaction();
		// ���� Transient̬��ʵ����󣬿���ͨ��session.save����ת��Ϊ Persistent̬
		session.save(persistentEntity);
		// ��ʱ persistentEntity �Ѿ���Hibernate������ʵ������������� Persistent̬���� transientEntity ��Ȼ�� Transient̬
		session.getTransaction().commit();
		// �����ύ֮�����ݿ����������һ����¼
		
		session.beginTransaction();
		transientEntity.setName("transientEntity2");// transientEntity ���� Transient̬
		persistentEntity.setName("persistentEntity2");// persistentEntity �� Persistent̬
		/* 
		 * ��Ȼû����ʾ�ĵ���save��������Entity���󣬵������ύ֮�� Persistent̬�Ķ���ᱻ�Զ��־û������ݿ⡣
		 * ��transientEntity ��Ϊ�� Transient̬������Hibernate���������������Ա����Ӱ�����ݿ⡣
		 */
		session.getTransaction().commit();
		
		// ���һ��ʵ���������Hibernate���صģ�����Ҳ���� Persistent̬
		// �ڷ��ض���֮ǰ��Hibernate���Ѿ����˶�������ʵ��������
		Entity loadedEntity = (Entity) session.load(Entity.class, 1L);
		session.close();
		// session�ر�֮��loadedEntity �����Detached ̬
		
		// ���´�һ��session
		Session newSession = sessionFactory.openSession();
		newSession.beginTransaction();
		// Detached̬��loadedEntity�ٴν���newSession��Hibernate����ʵ���������ٴν���Persistent̬
		newSession.update(loadedEntity);
		loadedEntity.setName("newSession creates");
		newSession.getTransaction().commit();
		
		newSession.beginTransaction();
		// ʵ������Persistent̬ת��ΪTransient̬һ����session.delete�������
		newSession.delete(loadedEntity);
		newSession.getTransaction().commit();
		// ��ʱ loadedEntity ��� Transient ̬
		
		newSession.close();
	}
	
	@Override
	protected void prepareData() {
	}
}
