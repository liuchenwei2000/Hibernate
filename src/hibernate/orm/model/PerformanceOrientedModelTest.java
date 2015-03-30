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
 * �������ܵ�����ϸ�ֽ�ģ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class PerformanceOrientedModelTest extends AbstractHibernateTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() throws SQLException {
		/** ����һ��PersonResumeVO */
		session.beginTransaction();
		PersonResumeVO resumeVO = createPersonResumeVO();
		resumeVO.setResume(session.getLobHelper().createClob("This is a resume."));
		session.save(resumeVO);
		session.getTransaction().commit();

		/** ����Ҫresume��Ϣ�Ĳ�ѯ��ʽ��ʹ��PersonBaseVO */
		// ���PersonResumeVO.hbm.xml�ļ��� polymorphism ����û�����û������ó� implicit
		// ��������ʹ��PersonBaseVO�Ĳ�ѯ��ʽ�Ὣ�����������������ȫ�������
		PersonBaseVO avo = (PersonBaseVO) session.load(PersonBaseVO.class, 1L);
		System.out.println(avo.getFirstName() + " " + avo.getLastName());

		Query query = session.createQuery("from PersonBaseVO where id=1");
		List<PersonBaseVO> vos = query.list();
		for (PersonBaseVO baseVO : vos) {
			System.out.println(baseVO.getFirstName() + " " + baseVO.getLastName());
		}

		/** ��Ҫresume��Ϣ�Ĳ�ѯ��ʽ��ʹ��PersonResumeVO */
		PersonResumeVO rvo = (PersonResumeVO) session.load(
				PersonResumeVO.class, 1L);
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
