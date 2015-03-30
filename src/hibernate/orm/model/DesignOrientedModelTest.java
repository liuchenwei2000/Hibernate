/**
 * 
 */
package hibernate.orm.model;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;

/**
 * ������Ƶ�����ϸ�ֽ�ģ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class DesignOrientedModelTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		session.beginTransaction();
		session.save(createPersonVO4());
		session.getTransaction().commit();

		PersonVO4 avo = (PersonVO4) session.load(PersonVO4.class, 1L);
		NameVO name = avo.getName();
		System.out.println(name.getFirstName() + " " + name.getLastName());
	}
	
	private static PersonVO4 createPersonVO4(){
		NameVO name = new NameVO();
		name.setFirstName("Vic");
		name.setLastName("Liu");
		
		PersonVO4 vo = new PersonVO4();
		vo.setSex(1);
		vo.setTimestamp(new Date());
		vo.setName(name);
		return vo;
	}

	@Override
	protected void prepareData() {
	}
}
