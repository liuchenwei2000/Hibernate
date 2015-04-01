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
 * Bean validation 使用示例
 * <p>
 * 这个示例中的代码既可以在持久层使用，也可以在表现层使用。
 * 在持久层使用的时候，可以跟Hibernate配合，在CRUD操作之前，对传入的Bean做验证。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月21日
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
		
		// 这里对 JavaBean 执行验证，并反馈验证结果
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
