package org.merlang.compilation.syntax.tree;

import org.merlang.compilation.syntax.tree.declaration.ProcedureDeclaration;
import org.merlang.compilation.syntax.tree.declaration.ProcedureParameter;
import org.merlang.compilation.syntax.tree.expression.CallArgument;
import org.merlang.compilation.syntax.tree.expression.CallExpression;
import org.merlang.compilation.syntax.tree.expression.Identifier;
import org.merlang.compilation.syntax.tree.expression.literal.StringLiteral;

public interface Visitor{
  default void visit(ProcedureParameter parameter) {
    throw new UnsupportedOperationException();
  }
  default void visit(ProcedureDeclaration declaration) {
    throw new UnsupportedOperationException();
  }
  default void visit(Identifier identifier) {
    throw new UnsupportedOperationException();
  }
  default void visit(CallArgument argument) {
    throw new UnsupportedOperationException();
  }
  default void visit(CallExpression expression) {
    throw new UnsupportedOperationException();
  }
  default void visit(StringLiteral literal) {
    throw new UnsupportedOperationException();
  }
}
