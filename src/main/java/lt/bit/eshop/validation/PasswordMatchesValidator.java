package lt.bit.eshop.validation;

import lt.bit.eshop.form.UserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(PasswordMatches constraint) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
       final UserModel user = (UserModel) obj;

       boolean isValid = user.getPassword().equals(user.getMatchPassword());

       if (!isValid) {
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate(
                   context.getDefaultConstraintMessageTemplate()
           ).addPropertyNode("matchPassword")
           .addConstraintViolation();
       }

       return isValid;
   }
}
