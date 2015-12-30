/**
 * 
 */
package hibernate.template;

/**
 * CRUD异常
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月21日
 */
public class CRUDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3186443127275341371L;

	public CRUDException() {
		super();
	}

	public CRUDException(String message, Throwable cause) {
		super(message, cause);
	}

	public CRUDException(String message) {
		super(message);
	}

	public CRUDException(Throwable cause) {
		super(cause);
	}
}
