package org.merlang.compilation.syntax.tree.expression.literal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public final class IntegerLiteral implements Literal {
  private final BigInteger value;

  private IntegerLiteral(BigInteger value) {
    this.value = value;
  }

  public BigInteger value() {
    return this.value;
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
    return StringLiteral.valueOf(value.toString());
  }

  @Override
  public DecimalLiteral asDecimalLiteral() {
    return DecimalLiteral.valueOf(new BigDecimal(value));
  }

  @Override
  public IntegerLiteral asIntegerLiteral() {
    return this;
  }

  public static IntegerLiteral valueOf(BigInteger value) {
    Objects.requireNonNull(value);
    return new IntegerLiteral(value);
  }
}
