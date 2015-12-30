/**
 * 
 */
package hibernate.orm.model;

import hibernate.util.AbstractHibernateTestCase;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

/**
 * 面向性能的粒度细分建模
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class PerformanceOrientedModelTest extends AbstractHibernateTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws SQLException {
		/** 保存一个PersonResumeVO */
		session.beginTransaction();
		PersonResumeVO resumeVO = createPersonResumeVO();
		resumeVO.setResume(session.getLobHelper().createClob("This is a resume."));
		session.save(resumeVO);
		session.getTransaction().commit();

		/** 不需要resume信息的查询方式，使用PersonBaseVO */
		// 如果PersonResumeVO.hbm.xml文件中 polymorphism 属性没有设置或者设置成 implicit
		// 下面这种使用PersonBaseVO的查询方式会将自身及所有子类的数据全部查出来
		PersonBaseVO avo = (PersonBaseVO) session.load(PersonBaseVO.class, 1L);
		System.out.println(avo.getFirstName() + " " + avo.getLastName());

		Query query = session.createQuery("from PersonBaseVO where id=1");
		List<PersonBaseVO> vos = query.list();
		for (PersonBaseVO baseVO : vos) {
			System.out.println(baseVO.getFirstName() + " " + baseVO.getLastName());
		}

		/** 需要resume信息的查询方式，使用PersonResumeVO */
		PersonResumeVO rvo = (PersonResumeVO) session.load(PersonResumeVO.class, 1L);
		Clob resume = rvo.getResume();
		System.out.println(rvo.getFirstName() + " " + rvo.getLastName()
				+ "'s resume is "
				+ resume.getSubString(1, (int) resume.length()));

		query = session.createQuery("from PersonResumeVO where id=1");
		List<PersonResumeVO> rvos = query.list();
		for (PersonResumeVO baseVO : rvos) {
			resume = baseVO.getResume();
			System.out.println(baseVO.getFirstName() + " "
					+ baseVO.getLastName() + "'s resume is "
					+ resume.getSubString(1, (int) resume.length()));
		}
	}
	
	private static PersonResumeVO createPersonResumeVO(){
		PersonResumeVO vo = new PersonResumeVO();
		vo.setFirstName("Vic");
		vo.setLastName("Liu");
		vo.setSex(1);
		vo.setTimestamp(new Date());
		return vo;
	}

	@Override
	protected void prepareData() {
	}
}
