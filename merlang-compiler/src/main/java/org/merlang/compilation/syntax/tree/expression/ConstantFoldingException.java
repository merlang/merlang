package org.merlang.compilation.syntax.tree.expression;

import org.merlang.compilation.CompileTimeEvaluationException;

public class ConstantFoldingException extends CompileTimeEvaluationException {
  public static ConstantFoldingException create() {
    return new ConstantFoldingException();
  }
}
