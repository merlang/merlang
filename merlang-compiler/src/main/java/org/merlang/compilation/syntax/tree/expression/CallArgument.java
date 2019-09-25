package org.merlang.compilation.syntax.tree.expression;

import org.merlang.compilation.syntax.tree.Producer;
import org.merlang.compilation.syntax.tree.Visitor;
import org.merlang.compilation.syntax.tree.expression.literal.Literal;

public class CallArgument implements Expression {
  private Expression value;
  private String label;

  private CallArgument(Expression value, String label) {
    this.value = value;
    this.label = label;
  }

  public boolean isLabeled() {
    return !this.label.isEmpty();
  }

  public String label() {
    return this.label;
  }

  @Override
  public boolean isConstant() {
    return value.isConstant();
  }

  @Override
  public int arity() {
    return value.arity();
  }

  @Override
  public Literal fold() throws ConstantFoldingException {
    return value.fold();
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acceptRecursive(Visitor visitor) {
    visitor.visit(this);
    this.value.acceptRecursive(visitor);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }

  @Override
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }

}
