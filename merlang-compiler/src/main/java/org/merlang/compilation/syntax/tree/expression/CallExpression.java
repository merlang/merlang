package org.merlang.compilation.syntax.tree.expression;

import com.google.common.collect.ImmutableCollection;

import org.merlang.compilation.syntax.tree.Producer;
import org.merlang.compilation.syntax.tree.Visitor;
import org.merlang.compilation.syntax.tree.expression.literal.Literal;

public final class CallExpression implements Expression {
  private Expression target;
  private ImmutableCollection<CallArgument> arguments;

  private CallExpression(
    Expression target,
    ImmutableCollection<CallArgument> arguments
  ) {
    this.target = target;
    this.arguments = arguments;
  }

  @Override
  public int arity() {
    return this.arguments.size();
  }

  @Override
  public boolean isConstant() {
    return false;
  }

  @Override
  public Literal fold() throws ConstantFoldingException {
    throw ConstantFoldingException.create();
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acceptRecursive(Visitor visitor) {
    visitor.visit(this);
    target.acceptRecursive(visitor);
    for (CallArgument argument : arguments) {
      argument.acceptRecursive(visitor);
    }
  }

  @Override
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }
}
