/**
 * 
 */
package hibernate.orm.discriminator;

/**
 * �鼮��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��25��
 */
public class Book extends Item {

	private int pageCount;// ��������

	public Book() {
		super();
	}

	public Book(String name) {
		super(name, 0);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
