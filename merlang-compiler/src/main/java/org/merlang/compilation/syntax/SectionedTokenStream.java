package org.merlang.compilation.syntax;

import org.merlang.common.IntStack;
import org.merlang.compilation.SourceRange;
import org.merlang.compilation.grammar.TokenStream;

/**
 * TokenStream decorator that can begin and end a selection.
 *
 * <p>Selections are areas of the source code (basically SourceRanges) and can
 * be nested meaning that you can begin a selection while already having one
 * or more. Selections should be finished by the rule who created it. They are
 * used to record the SourceRange of an ast Node and to create diagnostics.
 */
public final class SectionedTokenStream implements TokenStream {
  private IntStack selections;
  private TokenStream delegate;

  public SourceRange createRangeOfSelectionAndFinish() {
    SourceRange range = createRangeOfSelection();
    finishSelection();
    return range;
  }
  public SourceRange createRangeOfSelection() {
    int structureBegin = selections.peek();
    return SourceRange.of(structureBegin, currentPosition());
  }

  public void beginSelection() {
    selections.push(currentPosition());
  }

  public void finishSelection() {
    selections.pop();
  }

  @Override
  public int currentPosition() {
    return delegate.currentPosition();
  }
}
