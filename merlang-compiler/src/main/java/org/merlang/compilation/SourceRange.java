package org.merlang.compilation;

public final class SourceRange {
  private int beginPosition;
  private int endPosition;

  public static SourceRange of(int beginPosition, int endPosition) {
    return new SourceRange();
  }
}
