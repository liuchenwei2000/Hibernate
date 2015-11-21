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
 * Blob、Clob字段使用示例
 * <p>
 * Hibernate提供了对Blob(Binary Large Object)、Clob(Character Large Object)类型的内置支持，
 * 以处理需要在库表中保存大型字符串或者二进制数据（图片）的场景。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class BlobClobTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		PersonVO3 vo = createPersonVO4();

		// 通过LobHelper创建Blob、Clob对象
		LobHelper lobHelper = session.getLobHelper();

		Blob image = lobHelper.createBlob(new FileInputStream(
				"files/images/cross.png"), 1024 * 1000);
		vo.setImage(image);

		Clob resume = lobHelper.createClob("This is a big string.");
		vo.setResume(resume);

		/** 将含有Blob、Clob字段的VO写入数据库 */
		session.beginTransaction();
		session.save(vo);
		session.getTransaction().commit();
		session.close();

		/** 从数据库中读取含有Blob、Clob字段的VO */
		// 需要重新打开一个session，否则获取Blob二进制流的时候会出错
		session = sessionFactory.openSession();
		vo = (PersonVO3) session.load(PersonVO3.class, 1L);
		// 获取Blob字段
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

		// 获取Clob字段的字符串形式
		Clob resume2 = vo.getResume();
		String resumeString = resume2.getSubString(1L, (int) resume2.length());
		System.out.println("【resume】" + resumeString);
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
