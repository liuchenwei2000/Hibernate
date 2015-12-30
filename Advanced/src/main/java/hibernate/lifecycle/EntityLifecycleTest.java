/**
 * 
 */
package hibernate.lifecycle;

import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 实体对象生命周期 示例
 * <p>
 * 实体对象特指O/R Mapping中的"O"————域对象。
 * <p>
 * 实体对象生命周期中有三种状态：Transient（自由态）、Persistent（持久态）、Detached（游离态）。
 * <li>Transient（自由态）：
 * 即实体对象在内存中的自由存在，它与数据库中的记录无关。
 * <li>Persistent（持久态）：
 * 即实体对象处于由Hibernate框架所管理的状态，这种状态下，实体对象被纳入Hibernate实体容器中加以管理。
 * 处于Persistent状态的对象，其变更将由Hibernate持久化到数据库中。
 * <li>Detached（游离态）：
 * 处于Persistent态的对象，其对应的session实例关闭之后，此对象就处于Detached状态。
 * Session实例可以看作是Persistent对象的宿主，一旦此宿主失效，那么其从属的Persistent对象即进入Detached状态。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class EntityLifecycleTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		// 下面的两个对象是 Transient态，它们与数据库的记录无关
		Entity transientEntity = new Entity("transientEntity");
		Entity persistentEntity = new Entity("persistentEntity");
		
		// 到这里 transientEntity和 persistentEntity 还都是 Transient态
		session.beginTransaction();
		// 处于 Transient态的实体对象，可以通过session.save方法转换为 Persistent态
		session.save(persistentEntity);
		// 此时 persistentEntity 已经由Hibernate纳入了实体管理器，处于 Persistent态，而 transientEntity 仍然是 Transient态
		session.getTransaction().commit();
		// 事务提交之后，数据库表中增加了一条记录
		
		session.beginTransaction();
		transientEntity.setName("transientEntity2");// transientEntity 仍是 Transient态
		persistentEntity.setName("persistentEntity2");// persistentEntity 是 Persistent态
		/* 
		 * 虽然没有显示的调用save方法保存Entity对象，但事务提交之后， Persistent态的对象会被自动持久化到数据库。
		 * 而transientEntity 因为是 Transient态，不受Hibernate管理，所以它的属性变更不影响数据库。
		 */
		session.getTransaction().commit();
		
		// 如果一个实体对象是由Hibernate加载的，那它也处于 Persistent态
		// 在返回对象之前，Hibernate就已经将此对象纳入实体容器中
		Entity loadedEntity = (Entity) session.load(Entity.class, 1L);
		session.close();
		// session关闭之后，loadedEntity 即变成Detached 态
		
		// 重新打开一个session
		Session newSession = sessionFactory.openSession();
		newSession.beginTransaction();
		// Detached态的loadedEntity再次借助newSession由Hibernate加入实体容器，再次进入Persistent态
		newSession.update(loadedEntity);
		loadedEntity.setName("newSession creates");
		newSession.getTransaction().commit();
		
		newSession.beginTransaction();
		// 实体对象从Persistent态转变为Transient态一般由session.delete方法完成
		newSession.delete(loadedEntity);
		newSession.getTransaction().commit();
		// 此时 loadedEntity 变成 Transient 态
		
		newSession.close();
	}
	
	@Override
	protected void prepareData() {
	}
}
