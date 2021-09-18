package cn.com.nexwise.sdk.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
	
	/**
	 * 参数描述
	 * @return
	 */
    String description() default "";

  boolean required() default false;
  
  String[] validValues() default {};
  
  String validRegexValue() default "";
  
  int maxLength() default 0;
  
  int minLength() default 0;
  
  long max() default 0;
  
  long min() default 0;
  
  boolean notEmpty() default true;
  
  /**
   * The parameter type of the parameter.
   * <p>
   * Valid values are {@code path}, {@code query}, {@code body},
   * {@code header} or {@code form}.
   */
  String paramType() default Parameter.TYPE_BODY;
}
