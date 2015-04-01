/**
 * 
 */
package hibernate.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Bean validation ʹ��ʾ��
 * <p>
 * ���ʾ���еĴ���ȿ����ڳ־ò�ʹ�ã�Ҳ�����ڱ��ֲ�ʹ�á�
 * �ڳ־ò�ʹ�õ�ʱ�򣬿��Ը�Hibernate��ϣ���CRUD����֮ǰ���Դ����Bean����֤��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��21��
 */
public class UserValidation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setId(1L);
		user.setAge(-1);
		user.setDeleted(true);
		user.setLegal(false);
		user.setAccount(-1);
		user.setEmail("xxxxx");
		user.setPhone("");
		user.setDescription("me");
		
		// ����� JavaBean ִ����֤����������֤���
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		if (violations.isEmpty()) {
			System.out.println(user + " is perfect.");
		} else {
			for (ConstraintViolation<User> violation : violations) {
				System.out.println(violation.getMessage());
			}
		}
	}
}
