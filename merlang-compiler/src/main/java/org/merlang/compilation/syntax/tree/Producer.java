package org.merlang.compilation.syntax.tree;

import org.merlang.compilation.syntax.tree.declaration.ProcedureDeclaration;
import org.merlang.compilation.syntax.tree.declaration.ProcedureParameter;
import org.merlang.compilation.syntax.tree.expression.CallArgument;
import org.merlang.compilation.syntax.tree.expression.CallExpression;
import org.merlang.compilation.syntax.tree.expression.Identifier;
import org.merlang.compilation.syntax.tree.expression.literal.StringLiteral;

public interface Producer<InputT, OutputT> {
  default OutputT produce(ProcedureParameter parameter) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(ProcedureParameter parameter, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(ProcedureDeclaration declaration) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(ProcedureDeclaration declaration, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(Identifier identifier) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(Identifier identifier, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(CallArgument argument) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(CallArgument argument, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(CallExpression expression) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(CallExpression expression, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(StringLiteral literal, InputT input) {
    throw new UnsupportedOperationException();
  }
  default OutputT produce(StringLiteral literal) {
    throw new UnsupportedOperationException();
  }
}
