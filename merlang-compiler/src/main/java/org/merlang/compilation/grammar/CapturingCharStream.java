package org.merlang.compilation.grammar;

import org.merlang.compilation.CharStream;
import org.merlang.compilation.SourceRange;

public final class CapturingCharStream implements CharStream {
  private CharStream delegate;
  private StringBuilder buffer;
  private int captureBeginPosition;

  public void resetCapture() {
    buffer.setLength(0);
  }

  public Capture createCapture() {
    return new Capture(
      buffer.toString(),
      SourceRange.of(captureBeginPosition, currentPosition())
    );
  }

  @Override
  public int currentPosition() {
    return delegate.currentPosition();
  }

  public static final class Capture {
    private String value;
    private SourceRange sourceRange;

    private Capture(String value, SourceRange range) {
      this.value = value;
      this.sourceRange = range;
    }

    public String value() {
      return this.value;
    }

    public SourceRange sourceRange() {
      return this.sourceRange;
    }
  }
}
