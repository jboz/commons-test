package com.boz.commons.test.dbunit;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface Transactionnal {

  /**
   * Determines whether transactions will complete by default.
   */
  boolean rollback() default true;

}