package org.merlang.compilation.syntax;

import org.merlang.compilation.tree.Node;

public interface SyntaxRule<T extends Node> {

  T parseOrDelegate(SectionedTokenStream tokens, SyntaxRule<T> next) throws SyntaxException;
}
