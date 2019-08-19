package org.merlang.compilation.grammar;

public interface GrammarRule {
  Token scanOrDelegate(CapturingCharStream stream, GrammarRule next) throws GrammarException;
}
