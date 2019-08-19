package org.merlang.compilation.syntax;

import com.google.common.base.Preconditions;

import org.merlang.compilation.SourceRange;
import org.merlang.compilation.diagnostic.DiagnosticEntry;
import org.merlang.compilation.diagnostic.DiagnosticException;

/**
 * Thrown by a SyntaxRule in response to an invalid syntax.
 */
public final class SyntaxException extends DiagnosticException {
  private SourceRange invalidRange;

  private SyntaxException(
    String message, SourceRange invalidRange, DiagnosticEntry diagnosticEntry
  ) {
    super(diagnosticEntry, message);
    this.invalidRange = invalidRange;
  }

  public SourceRange invalidRange() {
    return this.invalidRange;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String message;
    private SourceRange invalidRange;
    private DiagnosticEntry diagnosticEntry;

    private Builder() {}

    public Builder withMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder withInvalidRange(SourceRange range) {
      this.invalidRange = range;
      return this;
    }

    public Builder withDiagnosticEntry(DiagnosticEntry entry) {
      this.diagnosticEntry = entry;
      return this;
    }

    public SyntaxException create() {
      Preconditions.checkNotNull(message);
      Preconditions.checkNotNull(invalidRange);
      Preconditions.checkNotNull(diagnosticEntry);
      return new SyntaxException(message, invalidRange, diagnosticEntry);
    }
  }

}
