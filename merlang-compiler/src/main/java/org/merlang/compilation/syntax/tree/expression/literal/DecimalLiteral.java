package org.merlang.compilation.syntax.tree.expression.literal;

import org.merlang.compilation.CompileTimeEvaluationException;
import org.merlang.compilation.syntax.tree.expression.ConstantFoldingException;

import java.math.BigDecimal;
import java.util.Objects;

public class DecimalLiteral implements Literal {
  private final BigDecimal value;

  private DecimalLiteral(BigDecimal value) {
    this.value = value;
  }

  public BigDecimal value() {
    return this.value;
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
    return StringLiteral.valueOf(value.toPlainString());
  }

  @Override
  public IntegerLiteral asIntegerLiteral() {
    return IntegerLiteral.valueOf(value.toBigInteger());
  }

  @Override
  public DecimalLiteral asDecimalLiteral() {
    return this;
  }

  public static DecimalLiteral valueOf(BigDecimal value) {
    Objects.requireNonNull(value);
    return new DecimalLiteral(value);
  }
}
