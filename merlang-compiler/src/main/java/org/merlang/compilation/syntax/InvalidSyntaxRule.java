package org.merlang.compilation.syntax;

import org.merlang.compilation.SourceRange;
import org.merlang.compilation.syntax.tree.Node;

/**
 * Last element in the chain of SyntaxRules, which reports invalid syntax.

 * <p>After every possible rule has been applied to a stream of tokens and
 * none of them have been matched, this rule is delegated to report an invalid
 * syntax in the SectionedTokenStream's current selection.
 *
 * @param <T> Type of the ast Node, which is not important for this exact
 *           implementation but required because of the delegating rule.
 */
public final class InvalidSyntaxRule<T extends Node> implements SyntaxRule<T> {

  /**
   * Reports an error of the current selections and finishes the selection.
   *
   * @param stream Stream containing a selection of the range that has failed
   *               to be parsed. Not modified by this method.
   * @param next Unused instance of a next rule. Since this class is the last
   *             rule, it will not use this argument. Instead of passing null
   *             you can pass the instance of the class.
   * @return No value will ever be returned by this method, it always throws.
   * @throws SyntaxException Containing the stream's current selection.
   */
  @Override
  public T parseOrDelegate(
    SectionedTokenStream stream,
    SyntaxRule<T> next
  ) throws SyntaxException {

    SourceRange invalidRange = stream.createRangeOfSelectionAndFinish();
    throw SyntaxException.newBuilder()
      .withMessage("Failed parsing the structure")
      .withInvalidRange(invalidRange)
      .create();
  }
}
