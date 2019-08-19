package org.merlang.compilation;

import com.google.common.base.Preconditions;

public final class CompilationException extends Exception {
  private CompilationException(String message) {
    super(message);
  }

  private CompilationException(Throwable cause) {
    super(cause);
  }

  public static CompilationException withMessage(String message) {
    Preconditions.checkNotNull(message);
    return new CompilationException(message);
  }

  public static CompilationException causedBy(Throwable cause) {
    Preconditions.checkNotNull(cause);
    return new CompilationException(cause);
  }
}
