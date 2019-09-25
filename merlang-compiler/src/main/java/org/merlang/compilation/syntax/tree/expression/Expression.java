package org.merlang.compilation.syntax.tree.expression;

import org.merlang.compilation.syntax.tree.Node;
import org.merlang.compilation.syntax.tree.expression.literal.Literal;

public interface Expression extends Node {
  int arity();
  Literal fold() throws ConstantFoldingException;
  boolean isConstant();
}