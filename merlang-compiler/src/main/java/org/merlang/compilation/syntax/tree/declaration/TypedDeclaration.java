package org.merlang.compilation.syntax.tree.declaration;

import org.merlang.compilation.syntax.tree.declaration.Declaration;
import org.merlang.compilation.syntax.tree.typename.TypeName;

public interface TypedDeclaration extends Declaration {
  TypeName typeName();
}
