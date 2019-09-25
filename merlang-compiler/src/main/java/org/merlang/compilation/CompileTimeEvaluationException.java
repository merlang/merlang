package org.merlang.compilation;

public class CompileTimeEvaluationException extends Exception {
  public static CompileTimeEvaluationException withMessage(String message) {
    return new CompileTimeEvaluationException();
  }
}
