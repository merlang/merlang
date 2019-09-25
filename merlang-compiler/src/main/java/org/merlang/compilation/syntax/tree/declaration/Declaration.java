package org.merlang.compilation.syntax.tree.declaration;

import org.merlang.compilation.syntax.tree.Node;
import org.merlang.compilation.syntax.tree.Scoped;
import org.merlang.compilation.syntax.tree.expression.Identifier;

public interface Declaration extends Scoped, Node {
  Identifier name();
  String descriptor();
  boolean isExported();
}
