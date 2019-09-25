package org.merlang.compilation.syntax.tree.expression.literal;

import org.merlang.compilation.grammar.Operator;
import org.merlang.compilation.CompileTimeEvaluationException;
import org.merlang.compilation.syntax.tree.expression.Expression;

public interface Literal extends Expression {
  Literal applyOperator(Operator operator, Literal target) throws CompileTimeEvaluationException;

  StringLiteral asStringLiteral() throws CompileTimeEvaluationException;
  DecimalLiteral asDecimalLiteral() throws CompileTimeEvaluationException;
  IntegerLiteral asIntegerLiteral() throws CompileTimeEvaluationException;
}
