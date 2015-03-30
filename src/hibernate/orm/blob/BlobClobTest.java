/**
 * 
 */
package hibernate.orm.blob;

import hibernate.util.AbstractHibernateTestCase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import org.hibernate.LobHelper;

/**
 * Blob��Clob�ֶ�ʹ��ʾ��
 * <p>
 * Hibernate�ṩ�˶�Blob(Binary Large Object)��Clob(Character Large Object)���͵�����֧�֣�
 * �Դ�����Ҫ�ڿ���б�������ַ������߶��������ݣ�ͼƬ���ĳ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��24��
 */
public class BlobClobTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		PersonVO3 vo = createPersonVO4();

		// ͨ��LobHelper����Blob��Clob����
		LobHelper lobHelper = session.getLobHelper();

		Blob image = lobHelper.createBlob(new FileInputStream(
				"files/images/cross.png"), 1024 * 1000);
		vo.setImage(image);

		Clob resume = lobHelper.createClob("This is a big string.");
		vo.setResume(resume);

		/** ������Blob��Clob�ֶε�VOд�����ݿ� */
		session.beginTransaction();
		session.save(vo);
		session.getTransaction().commit();
		session.close();

		/** �����ݿ��ж�ȡ����Blob��Clob�ֶε�VO */
		// ��Ҫ���´�һ��session�������ȡBlob����������ʱ������
		session = sessionFactory.openSession();
		vo = (PersonVO3) session.load(PersonVO3.class, 1L);
		// ��ȡBlob�ֶ�
		Blob image2 = vo.getImage();
		InputStream binaryStream = image2.getBinaryStream();
		FileOutputStream fos = new FileOutputStream(
				"files/images/cross_bak.png");
		byte[] buffer = new byte[1024 * 100];
		int len = 0;
		while ((len = binaryStream.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		binaryStream.close();

		// ��ȡClob�ֶε��ַ�����ʽ
		Clob resume2 = vo.getResume();
		String resumeString = resume2.getSubString(1L, (int) resume2.length());
		System.out.println("��resume��" + resumeString);
	}

	private static PersonVO3 createPersonVO4(){
		PersonVO3 vo = new PersonVO3();
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
