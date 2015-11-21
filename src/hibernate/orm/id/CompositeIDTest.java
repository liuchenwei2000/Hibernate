/**
 * 
 */
package hibernate.orm.id;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;

/**
 * composite-id使用示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class CompositeIDTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		session.beginTransaction();
		session.save(createPersonVO1());
		session.getTransaction().commit();
		
		session.beginTransaction();
		session.save(createPersonVO2());
		session.getTransaction().commit();
	}

	private static PersonVO1 createPersonVO1(){
		PersonVO1 vo = new PersonVO1();
		vo.setFirstName("Vic");
		vo.setLastName("Liu");
		vo.setSex(1);
		vo.setTimestamp(new Date());
		return vo;
	}
	
	private static PersonVO2 createPersonVO2(){
		PersonPK pk = new PersonPK();
		pk.setFirstName("Vic");
		pk.setLastName("Liu");
		
		PersonVO2 vo = new PersonVO2();
		vo.setSex(1);
		vo.setTimestamp(new Date());
		vo.setPk(pk);
		return vo;
	}

	@Override
	protected void prepareData() {
	}
}
