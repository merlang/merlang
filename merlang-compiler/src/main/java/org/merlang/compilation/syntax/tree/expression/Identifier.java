package org.merlang.compilation.syntax.tree.expression;

import org.merlang.compilation.syntax.tree.*;
import org.merlang.compilation.syntax.tree.expression.literal.Literal;

import java.util.Objects;

public final class Identifier implements Scoped, Expression {
  private String value;
  private Scope scope;

  private Identifier(String value, Scope scope) {
    this.value = value;
    this.scope = scope;
  }

  @Override
  public Scope scope() {
    return this.scope;
  }

  @Override
  public int arity() {
    return 0;
  }

  @Override
  public boolean isConstant() {
    // TODO: Lookup identifier to check whether it references a constant
    return false;
  }

  @Override
  public Literal fold() {
    return null;
  }

  @Override
  public String toString() {
    return this.value;
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
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof Identifier)) {
      return false;
    }
    return equals((Identifier) object);
  }

  private boolean equals(Identifier identifier) {
    return identifier.value.equals(value)
        && identifier.scope.equals(scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, scope);
  }

  public static Identifier create(String value, Scope scope) {
    Objects.requireNonNull(value);
    Objects.requireNonNull(scope);
    return new Identifier(value, scope);
  }
}
