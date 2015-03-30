/**
 * 
 */
package hibernate.orm.association.one2one.pk;

import hibernate.util.AbstractHibernateTestCase;

import java.util.Date;

/**
 * ��������ʾ��
 * <p>
 * һ��һ���������������ͣ�
 * 
 * 1����������
 * ���Ź�����ͨ�������γ�һ��һӳ���ϵ��
 * ���ڲ���������������ʽ����ôͨ���������������ű��������¼������ֵ�뱣��ͬ����
 * �����ζ�ţ�ֻ��Ϊһ�ű��趨����������������һ�ű��������֮������ͬ������ֵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class PrimaryKeyAssociationTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() {
		Citizen citizen = createCitizen();
		citizen.setPassport(createPassport());

		// ����User��ʱ���ͬʱ����Passport
		session.beginTransaction();
		session.save(citizen);
		session.getTransaction().commit();
		
		// ����Citizen����Ҳ�ܻ�ù�����Passport������Ϣ
		Citizen c2 = (Citizen) session.load(Citizen.class, 1L);
		System.out.println("Citizen name is ��" + c2.getName() + "��");
		System.out.println("The serial number of Citizen's passport is ��" + c2.getPassport().getSerial() + "��");
	}

	private static Citizen createCitizen() {
		Citizen citizen = new Citizen();
		citizen.setName("Tom Hanks");
		citizen.setAge(40);
		return citizen;
	}

	private static Passport createPassport() {
		Passport passport = new Passport();
		passport.setSerial("CN-000988762");
		passport.setExpiry(new Date());
		return passport;
	}

	@Override
	protected void prepareData() {
	}
}
