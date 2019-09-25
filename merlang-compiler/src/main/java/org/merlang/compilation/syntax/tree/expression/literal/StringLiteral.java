package org.merlang.compilation.syntax.tree.expression.literal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import org.merlang.compilation.CompileTimeEvaluationException;
import org.merlang.compilation.grammar.Operator;
import org.merlang.compilation.syntax.tree.Producer;
import org.merlang.compilation.syntax.tree.Visitor;

public final class StringLiteral implements Literal {
  private String value;

  private StringLiteral(String value) {
    this.value = value;
  }

  @Override
  public int arity() {
    return 0;
  }

  @Override
  public boolean isConstant() {
    return true;
  }

  @Override
  public Literal fold() {
    return this;
  }

  @Override
  public StringLiteral asStringLiteral() {
    return this;
  }

  @Override
  public IntegerLiteral asIntegerLiteral() throws CompileTimeEvaluationException {
    try {
      return IntegerLiteral.valueOf(new BigInteger(value));
    } catch (NumberFormatException invalidNumber) {
      String errorMessage = String.format("%s is not a valid integer", value);
      throw CompileTimeEvaluationException.withMessage(errorMessage);
    }
  }

  @Override
  public DecimalLiteral asDecimalLiteral() throws CompileTimeEvaluationException {
    try {
      return DecimalLiteral.valueOf(new BigDecimal(value));
    } catch (NumberFormatException invalidNumber) {
      String errorMessage = String.format("%s is not a valid decimal number", value);
      throw CompileTimeEvaluationException.withMessage(errorMessage);
    }
  }

  @Override
  public Literal applyOperator(Operator operator, Literal target)
    throws CompileTimeEvaluationException
  {
    if (operator == Operator.ADD) {
      String combined = value.concat(target.asStringLiteral().toString());
      return StringLiteral.valueOf(combined);
    }
    String errorMessage = String.format("Operator %s is not supported on strings", operator.name());
    throw CompileTimeEvaluationException.withMessage(errorMessage);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acceptRecursive(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }

  @Override
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  public static StringLiteral valueOf(String value) {
    Objects.requireNonNull(value);
    return new StringLiteral(value);
  }
}
